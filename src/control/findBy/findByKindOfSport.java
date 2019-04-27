package control.findBy;

import model.Tournament;

public class findByKindOfSport implements FindBy {
    @Override
    public boolean compareBy(Tournament tournament, Object ob) {
        String kindOfSport = (String) ob;
        return kindOfSport.equalsIgnoreCase(tournament.getKindOfSport());
    }
}
