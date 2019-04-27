package control.comparator;

import model.Tournament;

import java.util.Comparator;

public class CompareByPrizeOfWinner implements Comparator<Tournament>{
    public int compare(Tournament one, Tournament two) {
        return Double.compare(one.getPrizeOfWinner(), two.getPrizeOfWinner());
    }
}
