package view;

import control.APIForTournament;
import control.specification.ByDateSpecification;
import control.specification.ByNameOfTournamentSpecification;
import control.specification.ByNameOfWinnerSpecification;
import control.specification.ByPrizeOfWinnerSpecification;
import control.specification.ByPrizeSpecification;
import control.specification.BySportSpecification;
import model.Sport;
import model.Tournament;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SearchButton implements ActionListener {
    private APIForTournament apiForTournament;

    public SearchButton(APIForTournament api) {

        this.apiForTournament = api;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        APIForTournament forTournament = new APIForTournament();
        Dialog dialog = new Dialog(6, "Search", forTournament);
        dialog.button.setText("search");
        dialog.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                forTournament.deleteAll();

                List<Tournament> list = new ArrayList<>();

                if (dialog.getTextName().length() != 0) {
                    list = apiForTournament.findBy(new ByNameOfTournamentSpecification(dialog.getTextName()));
                }
                if (list.size() == 0) {
                    if (dialog.checkSport.getSelectedItem() != Sport.NOTHING) {
                        list = apiForTournament.findBy(new BySportSpecification((Sport) dialog.checkSport.getSelectedItem()));
                    } else if (dialog.getNameOfWinner().length() != 0) {
                        list = apiForTournament.findBy(new ByNameOfWinnerSpecification(dialog.getNameOfWinner()));
                    }
                }
                if (list.size() == 0) {
                    if (dialog.getPrizeOfWinner().length() != 0) {
                        String diapason = dialog.getPrizeOfWinner();
                        String[] strings = diapason.split(" ");
                        list = apiForTournament.findBy(new ByPrizeOfWinnerSpecification(Double.valueOf(strings[0]), Double.valueOf(strings[1])));
                    }
                }
                if (list.size() == 0) {
                    if (dialog.getPrize().length() != 0) {
                        list = apiForTournament.findBy(new ByPrizeSpecification(Integer.valueOf(dialog.getPrize())));
                    }
                }
                if (list.size() == 0) {
                    try {
                        if (dialog.getDate() != null) {
                            list = apiForTournament.findBy(new ByDateSpecification(dialog.getDate()));
                        }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }

                if (list.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Nothing satisfies conditions you put", "Error", JOptionPane.PLAIN_MESSAGE);
                } else {
                    for (Tournament tournament : list) {
                        forTournament.addParticipant(tournament);
                    }

                }

                dialog.countAfterSearch.setText("Result of search: " + forTournament.getListOfParticipants().size());
                dialog.getTable().updateInformation();

            }
        });
    }
}
