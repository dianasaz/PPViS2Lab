package control;

import com.sun.jnlp.ApiDialog;
import model.Tournament;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class xmlParserTest {

    @Test
    public void test(){
        APIForTournament api = new APIForTournament();
        XmlParser.load(api, "G:\\УНИВЕР\\laba2\\toParse.xml");

        APIForTournament apiForTournament = new APIForTournament();

        Assert.assertEquals(api.getListOfParticipants(), apiForTournament.getListOfParticipants());
    }

}