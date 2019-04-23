package control;

import control.findBy.FindBy;
import model.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class APIForNote {
    private List<Note> listOfParticipants;
    private List<Note> listOfParticipantOnScreen;
    private int currentPage;
    private int countOfParticipantsOnOnePage;
    private int pages;


    private int countAfterSearch;

    public APIForNote() {
        listOfParticipants = new ArrayList<>();
        listOfParticipantOnScreen = new ArrayList<>();
        countOfParticipantsOnOnePage = 2;
        pages = 1;
        currentPage = 1;
    }

    public void addParticipant(Note note){
        listOfParticipants.add(note);
        update();
    }

    public void deleteParticipant(Note note) throws Exception {
        if (listOfParticipants.contains(note)) {
            listOfParticipants.remove(note);
        } else throw new Exception("No such Participant");
        update();
    }

    public ArrayList<Note> findBy(FindBy find, Object ob){
        ArrayList<Note> resultOfSearch = new ArrayList<>();
        for (Note note : listOfParticipants){
            if (find.compareBy(note, ob))
                resultOfSearch.add(note);
        }
        countAfterSearch = resultOfSearch.size();
        return resultOfSearch;
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

    public List<Note> getListOfParticipantOnScreen(){
        return listOfParticipantOnScreen;
    }

    public int getPages(){
        return pages;
    }

    public void setPages(int pages){
        this.pages = pages;
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

    public void setListOfParticipants(List<Note> notes) {
        this.listOfParticipants = notes;
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
