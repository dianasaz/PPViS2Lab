package control.specification;

import model.Tournament;

public class ByPrizeOfWinnerSpecification implements Specification<Tournament> {
    private double maxPrize;
    private double minPrize;

    public ByPrizeOfWinnerSpecification(double minPrize, double maxPrize){
        this.maxPrize = maxPrize;
        this.minPrize = minPrize;
    }
    @Override
    public boolean match(Tournament bean) {
        if (bean.getPrizeOfWinner() > minPrize && bean.getPrizeOfWinner() < maxPrize)
            return true;
        else return false;
    }
}
