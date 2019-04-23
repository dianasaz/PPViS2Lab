package view;

import control.APIForNote;
import model.Note;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private List<Note> notes;

    public MyTableModel(APIForNote api) {
        this.notes = api.getListOfParticipantOnScreen();
    }

    @Override
    public int getRowCount() {
        return notes.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Note n = notes.get(rowIndex);
        switch (columnIndex){
            case 0:
                return n.getNameOfTournament();
            case 1:
                return n.getKindOfSport();
           // case 2:
             //   return n.getWinner();
            case 3:
                return n.getWinner();
            case 4:
                return n.getPrize();
            case 5:
                return n.getPrizeOfWinner();
            default:
                return null;
        }
    }
}
