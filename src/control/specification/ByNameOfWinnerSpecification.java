package control.specification;

import model.Tournament;

public class ByNameOfWinnerSpecification implements Specification<Tournament> {
    private String searchName;

    public ByNameOfWinnerSpecification(String name){
        this.searchName = name;
    }
    @Override
    public boolean match(Tournament bean) {
        return searchName.equalsIgnoreCase(bean.getWinner());
    }
}
