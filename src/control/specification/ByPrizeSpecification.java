package control.specification;

import model.Tournament;

public class ByPrizeSpecification implements Specification<Tournament> {
    private int searchPrize;

    public ByPrizeSpecification(int prize){
        this.searchPrize = prize;
    }
    @Override
    public boolean match(Tournament bean) {
        return searchPrize == bean.getPrize();
    }
}
