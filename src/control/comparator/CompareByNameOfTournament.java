package control.comparator;

import model.Note;

import java.util.Comparator;

public class CompareByNameOfTournament implements Comparator<Note>{
        public int compare(Note one, Note two) {
            return one.getNameOfTournament().compareTo(two.getNameOfTournament());
        }
    }
