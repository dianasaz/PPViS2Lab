package control.comparator;

import model.Tournament;

import java.util.Comparator;

public class CompareByPrize implements Comparator<Tournament>{
    public int compare(Tournament one, Tournament two) {
        return Double.compare(one.getPrize(), two.getPrize());
    }
}

