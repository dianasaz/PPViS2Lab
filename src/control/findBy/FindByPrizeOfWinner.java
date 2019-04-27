package control.findBy;

import model.Tournament;

public class FindByPrizeOfWinner implements FindByInDiapason {
    @Override
    public boolean compareBy(Tournament tournament, double high, double low) {
        if (tournament.getPrizeOfWinner() > high && tournament.getPrizeOfWinner() < low)
            return true;
        else return false;
    }
}
