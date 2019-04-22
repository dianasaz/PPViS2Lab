package control.findBy;

import model.Note;

public class findByKindOfSport implements FindBy {
    @Override
    public boolean compareBy(Note note, Object ob) {
        String kindOfSport = (String) ob;
        return kindOfSport.equalsIgnoreCase(note.getKindOfSport());
    }
}
