package control.findBy;

import model.Tournament;

public class FindByNameOfTournament implements FindBy{

    @Override
    public boolean compareBy(Tournament tournament, Object ob) {
        String nameOfTournament = (String) ob;
        return nameOfTournament.equalsIgnoreCase(tournament.getNameOfTournament());
    }
}
