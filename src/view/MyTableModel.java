package view;

import control.APIForTournament;
import model.Tournament;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MyTableModel extends AbstractTableModel {
    private String header[] = new String[]{"Tournament", "Sport", "Date", "Winner", "Prize", "Prize of winner"};
    private static final int TOURNAMENT = 0;
    private static final int SPORT = 1;
    private static final int DATE = 2;
    private static final int WINNER = 3;
    private static final int PRIZE = 4;
    private static final int WINNER_PRIZE = 5;
    private List<Tournament> tournaments;

    public MyTableModel(APIForTournament api) {
        this.tournaments = api.getListOfParticipantOnScreen();
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public int getRowCount() {
        return tournaments.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            Tournament n = tournaments.get(rowIndex);
            switch (columnIndex) {
                case TOURNAMENT:
                    return n.getNameOfTournament();
                case SPORT:
                    return n.getKindOfSport();
                case DATE:
                    return n.getDate();
                case WINNER:
                    return n.getWinner();
                case PRIZE:
                    return n.getPrize();
                case WINNER_PRIZE:
                    return n.getPrizeOfWinner();
                default:
                    return null;
            }
       // }
     //   return null;
    }
}
