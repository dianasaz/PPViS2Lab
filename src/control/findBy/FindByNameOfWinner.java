package control.findBy;

import model.FullName;
import model.Tournament;

public class FindByNameOfWinner implements FindBy {
    @Override
    public boolean compareBy(Tournament tournament, Object ob) {
        FullName nameOfWinner = (FullName) ob;
        return (boolean) nameOfWinner.equals(tournament.getWinner());
    }
}
