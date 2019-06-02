package control;


import model.Sport;
import model.SportsFactory;
import model.Tournament;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlParser {
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("d.MM.yyyy");
    private static Logger logger = Logger.getLogger(XmlParser.class.getName());

    public static APIForTournament load(APIForTournament apiForTournament, File file) {
        SAXParserFactory factory;
        factory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        DefaultHandler handler = new DefaultHandler() {
            List<Tournament> tournaments;
            Tournament tournament;

            String elementName;
            String name;
            Sport sport;
            String winner;
            int prize;
            Date date;

            @Override
            public void startDocument() throws SAXException {
                tournaments = new ArrayList<>();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                elementName = qName;
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String data = new String(ch, start, length);

                data = data.replace("\n", "").trim();
                if (!data.isEmpty()) {
                    switch (elementName) {
                        case "Name":
                            name = data;
                            break;
                        case "Sport":
                            sport = SportsFactory.setSport(data);
                            break;
                        case "Prize":
                            prize = Integer.valueOf(data);
                            break;
                        case "Winner":
                            winner = data;
                            break;
                        case "Date":
                            try {
                                date = dateFormat.parse(data);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            break;
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("Tournament")) {
                    tournament = new Tournament(name, sport, winner, prize, date);
                    apiForTournament.addParticipant(tournament);
                }
            }

            @Override
            public void endDocument() {
                    logger.log(Level.INFO, "file was loaded");
            }
        };

        try {
            saxParser.parse(file, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiForTournament;
    }

    public static void save(APIForTournament apiForTournament, File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("Tournaments");
            document.appendChild(rootElement);

            for (Tournament tournament : apiForTournament.getListOfParticipants()) {

                Element tournamett = document.createElement("Tournament");
                rootElement.appendChild(tournamett);

                Element name = document.createElement("Name");
                name.setTextContent(tournament.getNameOfTournament());
                tournamett.appendChild(name);

                Element sport = document.createElement("Sport");
                sport.setTextContent(String.valueOf(tournament.getKindOfSport()));
                tournamett.appendChild(sport);

                Element winner = document.createElement("Winner");
                winner.setTextContent(tournament.getWinner());
                tournamett.appendChild(winner);

                Element prize = document.createElement("Prize");
                prize.setTextContent(String.valueOf(tournament.getPrize()));
                tournamett.appendChild(prize);

                Element date = document.createElement("Date");
                date.setTextContent(String.valueOf(tournament.getDateOfTournament()));
                tournamett.appendChild(date);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);

            transformer.transform(domSource, streamResult);
            logger.log(Level.INFO, "file was saved");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }

    }
}