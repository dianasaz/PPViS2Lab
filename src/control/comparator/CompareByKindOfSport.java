package control.comparator;

import model.Note;

import java.util.Comparator;

public class CompareByKindOfSport implements Comparator<Note>{
    public int compare(Note one, Note two) {
        return one.getKindOfSport().compareTo(two.getKindOfSport());
    }
}

