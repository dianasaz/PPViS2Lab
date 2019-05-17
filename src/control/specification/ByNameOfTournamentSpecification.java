package control.specification;

import model.Tournament;

public class ByNameOfTournamentSpecification implements Specification<Tournament> {
    private String searchName;

    public ByNameOfTournamentSpecification(String searchName){
        this.searchName = searchName;
    }

    @Override
    public boolean match(Tournament bean) {
        return this.searchName.equals(bean.getNameOfTournament());
    }
}
