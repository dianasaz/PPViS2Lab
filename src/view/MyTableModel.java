package view;

import control.APIForTournament;
import model.Tournament;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private List<Tournament> tournaments;

    public MyTableModel(APIForTournament api) {
        this.tournaments = api.getListOfParticipantOnScreen();

    }

    @Override
    public int getRowCount() {
        return tournaments.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

       /* if (rowIndex == 0) {
            switch (columnIndex) {
                case 0:
                    return "Name of tournament";
                case 1:
                    return "Kind of sport";
                case 2:
                    return "Date";
                case 3:
                    return "Name of winner";
                case 4:
                    return "Prize";
                case 5:
                    return "Prize of winner";
            }
        } else {*/
            Tournament n = tournaments.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return n.getNameOfTournament();
                case 1:
                    return n.getKindOfSport();
                case 2:
                    return n.getDate();
                case 3:
                    return n.getWinner();
                case 4:
                    return n.getPrize();
                case 5:
                    return n.getPrizeOfWinner();
                default:
                    return null;
            }
       // }
     //   return null;
    }
}
