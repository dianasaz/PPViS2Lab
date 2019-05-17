package control.specification;

import model.Tournament;

public class ByPrizeOfWinnerSpecification implements Specification<Tournament> {
    private int searchPrizeOfWinner;

    public ByPrizeOfWinnerSpecification(int prizeOfWinner){
        this.searchPrizeOfWinner = prizeOfWinner;
    }
    @Override
    public boolean match(Tournament bean) {
        return searchPrizeOfWinner == bean.getPrizeOfWinner();
    }
}
