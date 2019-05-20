package control;

import control.specification.Specification;
import model.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIForTournament {

    private static Logger logger = Logger.getLogger(APIForTournament.class.getName());
    private List<Tournament> listOfParticipants;
    private List<Tournament> listOfParticipantOnScreen;
 //   private int currentPage = 1;
    private int countOfParticipantsOnOnePage;
 //   private int pages;

    private int countAfterSearch;

    public APIForTournament() {
        listOfParticipants = new ArrayList<>();
        listOfParticipantOnScreen = new ArrayList<>();
        countOfParticipantsOnOnePage = 5;
      //  pages = 1;

     //   addParticipant(new Tournament("Tohsak", Sport.FOOTBALL, "ndknwk ekwndnek dnjw", 600, 138));
     //   addParticipant(new Tournament("Tohfwdsssak", Sport.FOOTBALL, "ndknwkdds ekwndnek dnjw", 200, 12));
     //   addParticipant(new Tournament("sssak", Sport.FOOTBALL, "nk dnjw", 23, 12));
    }
/*
    public APIForTournament(List<Tournament> list) {
        if (listOfParticipants != null) {
            for (Tournament tournament : list) {
                listOfParticipants.add(tournament);
            }
        }
      //  this.listOfParticipants = listOfParticipants;
        listOfParticipantOnScreen = new ArrayList<>();
        countOfParticipantsOnOnePage = 5;
        pages = 1;
        logger.log(Level.INFO, listOfParticipants.toString());
    }
*/
    public void addParticipant(Tournament tournament){
        listOfParticipants.add(tournament);
       // update();
    }

    public void deleteParticipant(Tournament tournament) throws Exception {
        if (listOfParticipants.contains(tournament)) {
            listOfParticipants.remove(tournament);
        } else throw new Exception("No such Participant");
        //update();
    }

    public List<Tournament> findBy(Specification specification){
        List<Tournament> result = new ArrayList<>();
        for (Tournament tournament: listOfParticipants){
            if (specification.match(tournament)) {
                result.add(tournament);
                countAfterSearch++;
                logger.log(Level.INFO, "find" + tournament);
            }
        }
        return result;
    }

    public int getCountAfterSearch(){
        return countAfterSearch;
    }

    public void sort(Comparator comp){
        listOfParticipants.sort(comp);
    }
/*
    public int getCurrentPage(){
        return currentPage;
    }

    public int getNextPage(){
        return currentPage++;
    }

    public int getPrevPage(){
        return currentPage--;
    }

    public void setCurrentPage(int page){
        this.currentPage = page;
    }
*/
    public int getCountOfParticipantsOnOnePage(){
        return countOfParticipantsOnOnePage;
    }

    public void setCountOfParticipantsOnOnePage(int count){
        if (count > 0) {
            this.countOfParticipantsOnOnePage = count;
        }
      //  update();
    }

    public List<Tournament> getListOfParticipantOnScreen(){
        return listOfParticipantOnScreen;
    }
/*
    public int getPages(){
        return pages;
    }

    public void setPages(){
        if (listOfParticipants.size() % countOfParticipantsOnOnePage == 0)
            pages = listOfParticipants.size() / countOfParticipantsOnOnePage;
        else pages = listOfParticipants.size() / countOfParticipantsOnOnePage + 1;
    }

    public void setFirstPage(){
        currentPage = 1;
        updateInformationOnScreen();
    }

    public void setLastPage(){
        currentPage = getPages();
        updateInformationOnScreen();
    }

    public void previousPage(){
        if (pages > 1){
            currentPage--;
        }
        updateInformationOnScreen();
    }

    public void nextPage(){
        if (currentPage != pages){
            currentPage++;
        }
        updateInformationOnScreen();
    }

    public void setListOfParticipants(List<Tournament> tournaments) {
        this.listOfParticipants = tournaments;
        currentPage = 1;
        update();
    }
*/
    public List<Tournament> getListOfParticipants() {
        return listOfParticipants;
    }
/*
    private void updateInformationOnScreen() {
        int start = (currentPage - 1) * countOfParticipantsOnOnePage;
        int finish;
        if (listOfParticipants.size() >= currentPage * countOfParticipantsOnOnePage) {
            finish = currentPage * countOfParticipantsOnOnePage;
        } else {
            finish = listOfParticipants.size();
        }
        listOfParticipantOnScreen.clear();
        for (int i = start; i < finish; i++)
            listOfParticipantOnScreen.add(listOfParticipants.get(i));
    }

    private void updateAllPages(){
        int temp = listOfParticipants.size() / countOfParticipantsOnOnePage;
        if (listOfParticipants.size() % countOfParticipantsOnOnePage == 0)
            pages = temp;
        else
            pages = ++temp;
        if (pages == 0)
            pages++;
    }

    public void update(){
        updateAllPages();
        updateInformationOnScreen();
    }
*/
    @Override
    public String toString() {
        return "APIForTournament{" +
                "listOfParticipants=" + listOfParticipants +
                '}';
    }
}
