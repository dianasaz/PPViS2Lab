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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SearchButton {
    private APIForTournament apiForTournament;

    public SearchButton(APIForTournament api) {
        this.apiForTournament = api;
        Dialog dialog = new Dialog(6);
        dialog.button.setText("search");
        dialog.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Tournament> list = new ArrayList<>();

                if (dialog.getTextName().length() != 0) {
                    list = apiForTournament.findBy(new ByNameOfTournamentSpecification(dialog.getTextName()));
                    // logger.log(Level.INFO, "Find by name");
                }
                if (list.size() == 0) {
                    if (dialog.checkSport.getSelectedItem() != Sport.NOTHING) {
                        list = apiForTournament.findBy(new BySportSpecification((Sport) dialog.checkSport.getSelectedItem()));
                        //  logger.log(Level.INFO, "Find by sport");
                    } else if (dialog.getNameOfWinner().length() != 0) {
                        list = apiForTournament.findBy(new ByNameOfWinnerSpecification(dialog.getNameOfWinner()));
                        // logger.log(Level.INFO, "Find by name of winner");
                    }
                }
                if (list.size() == 0) {
                    if (dialog.getPrizeOfWinner().length() != 0) {
                        String diapason = dialog.getPrizeOfWinner();
                        String[] strings = diapason.split(" ");
                        list = apiForTournament.findBy(new ByPrizeOfWinnerSpecification(Double.valueOf(strings[0]), Double.valueOf(strings[1])));
                        // logger.log(Level.INFO, "Find by prize of winner");
                    }
                }
                if (list.size() == 0) {
                    if (dialog.getPrize().length() != 0) {
                        list = apiForTournament.findBy(new ByPrizeSpecification(Integer.valueOf(dialog.getPrize())));
                        //  logger.log(Level.INFO, "Find by prize");
                    }
                }
                if (list.size() == 0){
                    try {
                        if ( dialog.getDate() != null) {
                           list = apiForTournament.findBy(new ByDateSpecification(dialog.getDate()));
                           // logger.log(Level.INFO, "Find by date");
                       }
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }

                if (list.size() == 0){
                    JOptionPane.showMessageDialog(null, "Nothing satisfies conditions you put", "Error", JOptionPane.PLAIN_MESSAGE);
                } else {

                    JFrame frameOne = new JFrame();
                    frameOne.setTitle("result of search");
                    frameOne.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    frameOne.setSize(800, 500);

                    JLabel countAfterSearch = new JLabel("Result of search: " + list.size());

                    Container containerOne = frameOne.getContentPane();
                    containerOne.setLayout(new FlowLayout(FlowLayout.CENTER));

                    APIForTournament searchTournament = new APIForTournament();
                    for (Tournament tournament : list) {
                        searchTournament.addParticipant(tournament);
                    }
                    TableBase tableBase = new TableBase(searchTournament);

                    JPanel mainPanel = new JPanel();
                    mainPanel.add(countAfterSearch);
                    mainPanel.add(tableBase.getPanel());
                    containerOne.add(mainPanel);
                    mainPanel.setLayout((new BoxLayout(mainPanel, BoxLayout.Y_AXIS)));
                    frameOne.setLocationRelativeTo(null);
                    frameOne.setVisible(true);
                }
            }

        });
    }
}
