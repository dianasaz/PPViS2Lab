package control.findBy;

import model.Sports;
import model.Tournament;

public class findByKindOfSport implements FindBy {
    @Override
    public boolean compareBy(Tournament tournament, Object ob) {
        Sports kindOfSport = (Sports) ob;
        return (boolean) kindOfSport.equals(tournament.getKindOfSport());
    }
}
