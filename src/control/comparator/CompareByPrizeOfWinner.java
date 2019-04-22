package control.comparator;

import model.Note;

import java.util.Comparator;

public class CompareByPrizeOfWinner implements Comparator<Note>{
    public int compare(Note one, Note two) {
        return Double.compare(one.getPrizeOfWinner(), two.getPrizeOfWinner());
    }
}
