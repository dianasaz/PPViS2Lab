package control.findBy;

import model.Note;

public class FindByNameOfWinner implements FindBy {
    @Override
    public boolean compareBy(Note note, Object ob) {
        String nameOfWinner = (String) ob;
        return nameOfWinner.equalsIgnoreCase(note.getWinner());
    }
}
