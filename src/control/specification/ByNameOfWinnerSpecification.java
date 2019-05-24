package control.specification;

import model.Tournament;

public class ByNameOfWinnerSpecification implements Specification<Tournament> {
    private String searchName;
    String[] strings;

    public ByNameOfWinnerSpecification(String name) {
        this.searchName = name;
    }

    @Override
    public boolean match(Tournament bean) {
        strings = bean.getWinner().split(" ");
        for (int i = 0; i < strings.length; i++) {
            if (searchName.equalsIgnoreCase(strings[i]))
                return true;
        }
        return false;
    }
}
