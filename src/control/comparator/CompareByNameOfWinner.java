package control.comparator;

import model.Note;

import java.util.Comparator;

public class CompareByNameOfWinner implements Comparator<Note>{
    public int compare(Note one, Note two) {
        return one.getWinner().compareTo(two.getWinner());
    }
}

