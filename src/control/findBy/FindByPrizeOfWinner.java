package control.findBy;

import model.Note;

public class FindByPrizeOfWinner implements FindByInDiapason {
    @Override
    public boolean compareBy(Note note, double high, double low) {
        if (note.getPrizeOfWinner() > high && note.getPrizeOfWinner() < low)
            return true;
        else return false;
    }
}
