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

public class RemoveButton implements ActionListener{
    private APIForTournament apiForTournament;
    private Table table;
    public RemoveButton(APIForTournament apiForTournament, Table table) {
        this.apiForTournament = apiForTournament;
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Dialog dialog = new Dialog(6, "", apiForTournament);
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
                        try {
                            if (dialog.getDate() != null) {
                                list = apiForTournament.findBy(new ByDateSpecification(dialog.getDate()));
                                break;
                            }
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }

                    if (list.size() == 0){
                        JOptionPane.showMessageDialog(null, "Nothing satisfies conditions you put", "Error", JOptionPane.PLAIN_MESSAGE);
                    }

                    for (Tournament tournament : list) {
                        try {
                            apiForTournament.deleteParticipant(tournament);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    if (list.size() == 1) JOptionPane.showMessageDialog(null, list.size() + " was removed", "result", JOptionPane.PLAIN_MESSAGE);
                    if (list.size() > 1) JOptionPane.showMessageDialog(null, list.size() + " were removed", "result", JOptionPane.PLAIN_MESSAGE);

                    table.updateInformation();
                }

            });
        }
    }

