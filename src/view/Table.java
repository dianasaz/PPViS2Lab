package view;

import control.APIForTournament;
import control.specification.ByNameOfTournamentSpecification;
import control.specification.ByNameOfWinnerSpecification;
import control.specification.ByPrizeOfWinnerSpecification;
import control.specification.ByPrizeSpecification;
import control.specification.BySportSpecification;
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
    private JButton previousPage;
    private JButton nextPage;
    private JLabel currentPage;

    private JButton fiveTournamentsOnPage;
    private JButton tenTournamentsOnPage;
    private JButton twentyTournamentsOnPage;

    private JTable table;
    private MyTableModel model;

    private JScrollPane scrollPane;

    private int page = 1;
    private int pages;
    private APIForTournament apiForTournament;

    private static Logger logger = Logger.getLogger(Table.class.getName());

    public Table(APIForTournament api) throws InvalidDataException {
        this.apiForTournament = api;
        mainPanel = new JPanel();
        panelButtons = new JPanel();
        pagePanel = new JPanel();
        pagesPanel = new JPanel();
        panelSettingsMethod(mainPanel);

        addToTableBtn = new JButton("Добавить в таблицу");
        buttonSearch = new JButton("Поиск");
        buttonRemove = new JButton("Удалить");

        firstPage = new JButton("1");
        lastPage = new JButton("last");
        previousPage = new JButton("<<");
        nextPage = new JButton(">>");
        currentPage = new JLabel(String.valueOf(page));

        fiveTournamentsOnPage = new JButton("5");
        tenTournamentsOnPage = new JButton("10");
        twentyTournamentsOnPage = new JButton("20");

        model = new MyTableModel(apiForTournament);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        page = 1;
        pages = 1;

        apiForTournament.addParticipant(new Tournament("Games", Sport.FOOTBALL, "Vasya Pupkin", 600, 21));
        apiForTournament.addParticipant(new Tournament("Olympic games", Sport.FOOTBALL, "Petya Kozlov", 200, 12));
        apiForTournament.addParticipant(new Tournament("European games", Sport.FOOTBALL, "Vlad Ponchikod", 400, 14));

        update();
        table.setSize(new Dimension(600, 600));
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));

        addToTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Dialog dialog = new Dialog(5);
                dialog.button.setText("add");
                dialog.button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (dialog.getTextName().length() == 0 || dialog.getNameOfWinner().length() == 0 || dialog.getPrize().length() == 0 || dialog.getDate().length() == 0 || dialog.checkSport.getSelectedItem() == Sport.NOTHING) {
                            JOptionPane.showMessageDialog(null, "Check information you put", "Error", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            Tournament tournament = new Tournament(dialog.getTextName(), (Sport) dialog.checkSport.getSelectedItem(), dialog.getNameOfWinner(), Integer.valueOf(dialog.getPrize()), Integer.valueOf(dialog.getDate()));
                            apiForTournament.addParticipant(tournament);
                            if (pages < apiForTournament.getListOfParticipants().size()){
                                pages++;
                            }
                            update();
                            logger.log(Level.INFO, tournament+ " was added");
                        }
                    }
                });
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new SearchButton(apiForTournament);
            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

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
                            update();

                        }

                });
            }
        });

        fiveTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(5);
                update();
            }
        });

        tenTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(10);
                update();
            }
        });

        twentyTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(20);
                update();
            }
        });

        firstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstPage();
                update();
            }
        });

        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLastPage();
                update();
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
                update();
            }
        });

        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage();
                update();
            }
        });

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(scrollPane);
        panelButtons.add(addToTableBtn);
        panelButtons.add(buttonSearch);
        panelButtons.add(buttonRemove);
        pagesPanel.add(previousPage);
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

    public int getPages(){
        return pages;
    }

    public void setFirstPage(){
        page = 1;
        update();
    }

    public void setLastPage(){
        page = getPages();
        update();
    }

    public void previousPage(){
        if (pages > 1){
            page--;
        }
        update();
    }

    public void nextPage(){
        if (page != pages){
            page++;
        }
        update();
    }

    private void updateInformationOnScreen() {
        int start = (page-1) * apiForTournament.getCountOfParticipantsOnOnePage();
        int finish;
        if (apiForTournament.getListOfParticipants().size() >= page * apiForTournament.getCountOfParticipantsOnOnePage()) {
            finish = page * apiForTournament.getCountOfParticipantsOnOnePage();
        } else {
            finish = apiForTournament.getListOfParticipants().size();
        }
        apiForTournament.getListOfParticipantOnScreen().clear();
        for (int i = start; i < finish; i++)
            apiForTournament.getListOfParticipantOnScreen().add(apiForTournament.getListOfParticipants().get(i));
    }

    private void updateAllPages(){
        int temp = apiForTournament.getListOfParticipants().size() / apiForTournament.getCountOfParticipantsOnOnePage();
        if (apiForTournament.getListOfParticipants().size() % apiForTournament.getCountOfParticipantsOnOnePage() == 0)
            pages = temp;
        else
            pages = ++temp;
        if (pages == 0)
            pages++;
    }

    public void update(){
        updateAllPages();
        updateInformationOnScreen();
        currentPage.setText(page + " of " + pages);
        model.fireTableDataChanged();
    }

}
