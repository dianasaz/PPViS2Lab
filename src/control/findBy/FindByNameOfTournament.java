package control.findBy;

import model.Note;

public class FindByNameOfTournament implements FindBy{

    @Override
    public boolean compareBy(Note note, Object ob) {
        String nameOfTournament = (String) ob;
        return nameOfTournament.equalsIgnoreCase(note.getNameOfTournament());
    }
}
