package control.comparator;

import model.Tournament;

import java.util.Comparator;

public class CompareByNameOfTournament implements Comparator<Tournament>{
        public int compare(Tournament one, Tournament two) {
            return one.getNameOfTournament().compareTo(two.getNameOfTournament());
        }
    }
