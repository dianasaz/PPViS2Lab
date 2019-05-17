package view;

import control.APIForTournament;
import control.specification.ByNameOfTournamentSpecification;
import control.specification.ByNameOfWinnerSpecification;
import exception.InvalidDataException;
import model.Sport;
import model.Tournament;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Table {
    private JPanel mainPanel;
    private JPanel panelButtons;
    private JPanel pagesPanel;
    private JPanel pagePanel;

    private JButton addToTableBtn;
    private JButton buttonSearch;
    private JButton buttonRemove;

    private JButton firstPage;
    private JButton lastPage;
    private JButton previusPage;
    private JButton nextPage;
    private JLabel currentPage;

    private JButton fiveTournamentsOnPage;
    private JButton tenTournamentsOnPage;
    private JButton twentyTournamentsOnPage;

    private JTable table;
    private MyTableModel model;
   // private APIForTournament api;

    private static Logger logger = Logger.getLogger(Table.class.getName());

    public Table(APIForTournament api) throws InvalidDataException {
        mainPanel = new JPanel();
        panelButtons = new JPanel();
        pagePanel = new JPanel();
        pagesPanel = new JPanel();
        panelSettingsMethod(mainPanel);
        //panelSettingsMethod(panel);

        addToTableBtn = new JButton("Добавить в таблицу");
        buttonSearch = new JButton("Поиск");
        buttonRemove = new JButton("Удалить");

        firstPage = new JButton("1");
        lastPage = new JButton("last");
        previusPage = new JButton("<<");
        nextPage = new JButton(">>");
        currentPage = new JLabel(String.valueOf(api.getCurrentPage()));

        fiveTournamentsOnPage = new JButton("5");
        tenTournamentsOnPage = new JButton("10");
        twentyTournamentsOnPage = new JButton("20");

        model = new MyTableModel(api);
        table = new JTable(model);

        api.addParticipant(new Tournament("Tohsak", Sport.FOOTBALL, "ndknwk ekwndnek dnjw", 600, 138));
        api.addParticipant(new Tournament("Tohfwdsssak", Sport.FOOTBALL, "ndknwkdds ekwndnek dnjw", 200, 12));
        api.addParticipant(new Tournament("sssak", Sport.FOOTBALL, "nk dnjw", 23, 12));
       // panel.setLocation(100, 100);
        table.setSize(500,500);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));

        addToTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Dialog dialog = new Dialog();
                dialog.button.setText("add");
                dialog.button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (dialog.getName() == null | dialog.getNameOfWinner() == null | dialog.getPrize().getText() == null | dialog.getDate() == null) {
                            try {
                                throw new InvalidDataException("Check information");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            Tournament tournament = new Tournament(dialog.getName(), (Sport) dialog.checkSport.getSelectedItem(), dialog.getNameOfWinner(), 890, 90);
                            api.addParticipant(tournament);
                            model.fireTableDataChanged();
                            logger.log(Level.INFO, tournament+ " was added");
                        }
                    }
                });
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Dialog dialog = new Dialog();
                dialog.button.setText("search");
                dialog.button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Tournament> list = new ArrayList<>();
                        if (dialog.textName.getText() != null) {
                            list = api.findBy(new ByNameOfTournamentSpecification(dialog.textName.getText()));
                        } else if (dialog.textNameOfWinner.getText() != null) {
                            list = api.findBy(new ByNameOfWinnerSpecification(dialog.textNameOfWinner.getText()));
                        }
                            JFrame frameOne = new JFrame();
                            frameOne.setTitle("result of search");
                            frameOne.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            frameOne.setSize(800, 500);

                            Container containerOne = frameOne.getContentPane();
                            containerOne.setLayout(new FlowLayout(FlowLayout.CENTER));

                            APIForTournament apiForTournament = new APIForTournament();
                            for (Tournament tournament: list){
                                apiForTournament.addParticipant(tournament);
                            }
                            TableBase tableBase = new TableBase(apiForTournament);

                            containerOne.add(tableBase.getPanel());
                            frameOne.setLocationRelativeTo(null);
                            frameOne.setVisible(true);
                            //model.fireTableDataChanged();
                        }
                });
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Dialog dialog = new Dialog();
                dialog.button.setText("remove");
                dialog.button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (dialog.getName() == null| dialog.getNameOfWinner() == null | dialog.getPrize().getText() == null | dialog.getDate() == null) {
                            try {
                                throw new InvalidDataException("Check information");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            if (dialog.textName != null){
                            List<Tournament> list= api.findBy(new ByNameOfTournamentSpecification(dialog.textName.getText()));
                            for (Tournament tournament: list){
                                try {
                                    api.deleteParticipant(tournament);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            model.fireTableDataChanged();
                        }
                        }
                    }
                });
            }
        });

        fiveTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(5);
                model.fireTableDataChanged();
            }
        });

        tenTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(10);
                model.fireTableDataChanged();
            }
        });

        twentyTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(20);
                model.fireTableDataChanged();
            }
        });

        firstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setFirstPage();
                currentPage.setText("1");
                model.fireTableDataChanged();
            }
        });

        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setLastPage();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(api.getPages()));
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.nextPage();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(api.getNextPage()));
            }
        });

        previusPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.previousPage();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(api.getPrevPage()));
            }
        });

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
        mainPanel.add(table);
        panelButtons.add(addToTableBtn);
        panelButtons.add(buttonSearch);
        panelButtons.add(buttonRemove);
        pagesPanel.add(previusPage);
        pagesPanel.add(firstPage);
        pagesPanel.add(lastPage);
        pagesPanel.add(nextPage);
        pagesPanel.add(fiveTournamentsOnPage);
        pagesPanel.add(tenTournamentsOnPage);
        pagesPanel.add(twentyTournamentsOnPage);
        pagePanel.add(currentPage);
        mainPanel.add(pagePanel);
        mainPanel.add(pagesPanel);
        mainPanel.add(panelButtons);
    }

    public void panelSettingsMethod(JPanel panel){
       //  panel.setPreferredSize(new Dimension(200,120));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
    }

    private boolean notNull(int row, int col){
        if (model.getValueAt(row, col) != null){
            return true;
        }else{
            return false;
        }
    }


    public JPanel getPanel() {
        return mainPanel;
    }

    public JPanel getPanelButtons(){
        return panelButtons;
    }
}
