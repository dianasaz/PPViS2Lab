package control.comparator;

import model.Tournament;

import java.util.Comparator;

public class CompareByKindOfSport implements Comparator<Tournament>{
    public int compare(Tournament one, Tournament two) {
        return one.getKindOfSport().compareTo(two.getKindOfSport());
    }
}

