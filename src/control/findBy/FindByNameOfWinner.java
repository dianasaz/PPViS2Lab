package control.findBy;

import model.Tournament;

public class FindByNameOfWinner implements FindBy {
    @Override
    public boolean compareBy(Tournament tournament, Object ob) {
        String nameOfWinner = (String) ob;
        return nameOfWinner.equalsIgnoreCase(tournament.getWinner());
    }
}
