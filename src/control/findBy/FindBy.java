package control.findBy;

import model.Note;

public interface FindBy {
    public boolean compareBy(Note note, Object ob);
}
