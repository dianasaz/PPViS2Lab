package control;

import control.findBy.FindBy;
import model.FullName;
import model.Sports;
import model.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class APIForTournament {
    private List<Tournament> listOfParticipants;
    private List<Tournament> listOfParticipantOnScreen;
    private int currentPage;
    private int countOfParticipantsOnOnePage;
    private int pages;

    private int countAfterSearch;

    public APIForTournament() {
        listOfParticipants = new ArrayList<>();
        listOfParticipantOnScreen = new ArrayList<>();
        countOfParticipantsOnOnePage = 2;
        pages = 1;
        currentPage = 1;
        Tournament tournament = new Tournament("Tohsak", Sports.FOOTBALL, new FullName("ndknwk ekwndnek dnjw"), 600);
        Tournament tournament2 = new Tournament("Tohfwdsssak", Sports.FOOTBALL, new FullName("ndknwkdds ekwndnek dnjw"), 200);
        addParticipant(tournament);
        addParticipant(tournament2);
    }

    public void addParticipant(Tournament tournament){
        listOfParticipants.add(tournament);
        update();
    }

    public void deleteParticipant(Tournament tournament) throws Exception {
        if (listOfParticipants.contains(tournament)) {
            listOfParticipants.remove(tournament);
        } else throw new Exception("No such Participant");
        update();
    }

    public ArrayList<Tournament> findBy(FindBy find, Object ob){
        ArrayList<Tournament> resultOfSearch = new ArrayList<>();
        for (Tournament tournament : listOfParticipants){
            if (find.compareBy(tournament, ob))
                resultOfSearch.add(tournament);
        }
        countAfterSearch = resultOfSearch.size();
        return resultOfSearch;
    }

    public int getCountAfterSearch(){
        return countAfterSearch;
    }

    public void sort(Comparator comp){
        listOfParticipants.sort(comp);
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(int page){
        this.currentPage = page;
    }

    public int getCountOfParticipantsOnOnePage(){
        return countOfParticipantsOnOnePage;
    }

    public void setCountOfParticipantsOnOnePage(int count){
        if (count > 0) {
            this.countOfParticipantsOnOnePage = count;
        }
        update();
    }

    public void setListOfParticipantOnScreen(){}

    public List<Tournament> getListOfParticipantOnScreen(){
        return listOfParticipantOnScreen;
    }

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
}
