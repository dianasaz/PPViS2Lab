package control;


import model.Sport;
import model.SportsFactory;
import model.Tournament;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
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

    public static void load(APIForTournament apiForTournament, String line) {
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
                    if (qName.equals("Tournament")){

                        try {
                            name = atts.getValue("name").trim();
                            date = dateFormat.parse(atts.getValue("date"));
                            winner = atts.getValue("winner");
                            prize = Integer.valueOf(atts.getValue("prize"));
                            sport = SportsFactory.setSport(atts.getValue("sport"));

                            tournament = new Tournament(name, sport, winner, prize, date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException{
                    tournaments.add(tournament);
                }
                @Override
                public void endDocument(){
                    if (tournaments.size() != 0){
                        for (Tournament t: tournaments){
                            apiForTournament.addParticipant(t);
                        }
                        logger.log(Level.INFO, "file was loaded");
                    }
                }
            };
        try {
            saxParser.parse(line, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}