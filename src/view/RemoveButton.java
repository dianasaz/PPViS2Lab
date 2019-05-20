package view;

import control.APIForTournament;
import control.specification.ByNameOfTournamentSpecification;
import control.specification.ByNameOfWinnerSpecification;
import control.specification.ByPrizeOfWinnerSpecification;
import control.specification.ByPrizeSpecification;
import control.specification.BySportSpecification;
import javafx.scene.control.Tab;
import model.Sport;
import model.Tournament;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RemoveButton {
    private APIForTournament apiForTournament;
    private JTable table;

    public RemoveButton(APIForTournament apiForTournament){
       // this.apiForTournament = api;
       // this.table = table;

        Dialog dialog = new Dialog(6);
        dialog.button.setText("remove");
        dialog.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Tournament> list = new ArrayList<>();
                boolean flag = true;
                while (flag) {
                    if (dialog.getTextName().length() != 0) {
                        list = apiForTournament.findBy(new ByNameOfTournamentSpecification(dialog.textName.getText()));
                        break;
                    }
                    if (dialog.checkSport.getSelectedItem() != Sport.NOTHING) {
                        list = apiForTournament.findBy(new BySportSpecification((Sport) dialog.checkSport.getSelectedItem()));
                        break;
                    }
                    if (dialog.getNameOfWinner().length() != 0) {
                        list = apiForTournament.findBy(new ByNameOfWinnerSpecification(dialog.getNameOfWinner()));
                        break;
                    }
                    if (dialog.getPrizeOfWinner().length() != 0) {
                        String diapason = dialog.getPrizeOfWinner();
                        String[] strings = diapason.split(" ");
                        list = apiForTournament.findBy(new ByPrizeOfWinnerSpecification(Double.valueOf(strings[0]), Double.valueOf(strings[1])));
                        break;
                    }
                    if (dialog.getPrize().length() != 0) {
                        list = apiForTournament.findBy(new ByPrizeSpecification(Integer.valueOf(dialog.getPrize())));
                        break;
                    }
                }

                for (Tournament tournament: list){
                    try {
                        apiForTournament.deleteParticipant(tournament);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });
    }
}
