package control.findBy;

import model.Tournament;

public class FindByPrize implements FindByInDiapason {
    @Override
    public boolean compareBy(Tournament tournament, double high, double low) {
        if (tournament.getPrize() < high && tournament.getPrize() > low)
            return true;
        else return false;
    }
}