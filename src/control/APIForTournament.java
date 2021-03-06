package control;

import control.specification.Specification;
import model.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIForTournament {

    private static Logger logger = Logger.getLogger(APIForTournament.class.getName());
    private List<Tournament> listOfParticipants;
    private List<Tournament> listOfParticipantOnScreen;
    private int countOfParticipantsOnOnePage;


    public APIForTournament() {
        listOfParticipants = new ArrayList<>();
        listOfParticipantOnScreen = new ArrayList<>();
        countOfParticipantsOnOnePage = 5;
    }

    public void addParticipant(Tournament tournament) {
        listOfParticipants.add(tournament);
    }

    public void deleteParticipant(Tournament tournament) throws Exception {
        if (listOfParticipants.contains(tournament)) {
            listOfParticipants.remove(tournament);
        } else throw new Exception("No such Participant");
    }

    public void deleteAll(){
        listOfParticipants.clear();
    }

    public List<Tournament> findBy(Specification specification) {
        List<Tournament> result = new ArrayList<>();
        for (Tournament tournament : listOfParticipants) {
            if (specification.match(tournament)) {
                result.add(tournament);
                logger.log(Level.INFO, "find" + tournament);
            }
        }
        return result;
    }

    public int getCountOfParticipantsOnOnePage() {
        return countOfParticipantsOnOnePage;
    }

    public void setCountOfParticipantsOnOnePage(int count) {
        if (count > 0) {
            this.countOfParticipantsOnOnePage = count;
        }
    }

    public List<Tournament> getListOfParticipantOnScreen() {
        return listOfParticipantOnScreen;
    }

    public List<Tournament> getListOfParticipants() {
        return listOfParticipants;
    }

    @Override
    public String toString() {
        return "APIForTournament{" +
                "listOfParticipants=" + listOfParticipants +
                '}';
    }
}
