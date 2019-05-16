package control.comparator;

import model.Tournament;

import java.util.Comparator;

public class CompareByNameOfWinner implements Comparator<Tournament>{
    public int compare(Tournament one, Tournament two) {
        return one.getWinner().toString().compareTo(two.getWinner().toString());
    }
}

