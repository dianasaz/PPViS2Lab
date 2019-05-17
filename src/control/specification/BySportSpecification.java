package control.specification;

import model.Sport;
import model.Tournament;

public class BySportSpecification implements Specification<Tournament> {
    private Sport searchSport;

    public BySportSpecification(Sport sport){
        this.searchSport = sport;
    }

    @Override
    public boolean match(Tournament bean) {
        return searchSport.equals(bean.getKindOfSport());
    }
}
