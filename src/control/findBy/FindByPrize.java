package control.findBy;

import model.Note;

public class FindByPrize implements FindByInDiapason {
    @Override
    public boolean compareBy(Note note, double high, double low) {
        if (note.getPrize() < high && note.getPrize() > low)
            return true;
        else return false;
    }
}