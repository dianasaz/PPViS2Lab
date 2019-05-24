package view;

import control.APIForTournament;
import model.Sport;
import model.Tournament;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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

        updateInformation();
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
                        if (dialog.getTextName().length() == 0 || dialog.getNameOfWinner().length() == 0 || dialog.getPrize().length() == 0 || dialog.checkSport.getSelectedItem() == Sport.NOTHING) {
                            JOptionPane.showMessageDialog(null, "Check information you put", "Error", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            Tournament tournament = null;
                            try {
                                tournament = new Tournament(dialog.getTextName(), (Sport) dialog.checkSport.getSelectedItem(), dialog.getNameOfWinner(), Integer.valueOf(dialog.getPrize()), dialog.getDate());
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            apiForTournament.addParticipant(tournament);
                            if (pages < apiForTournament.getListOfParticipants().size()){
                                pages++;
                            }
                            updateInformation();
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
                new RemoveButton(apiForTournament, Table.this);
            }
        });

        fiveTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(5);
                updateInformation();
            }
        });

        tenTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(10);
                updateInformation();
            }
        });

        twentyTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apiForTournament.setCountOfParticipantsOnOnePage(20);
                updateInformation();
            }
        });

        firstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFirstPage();
                updateInformation();
            }
        });

        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLastPage();
                updateInformation();
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
                updateInformation();
            }
        });

        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage();
                updateInformation();
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

    private void panelSettingsMethod(JPanel panel){
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    private int getPages(){
        return pages;
    }

    private void setFirstPage(){
        page = 1;
        updateInformation();
    }

    private void setLastPage(){
        page = getPages();
        updateInformation();
    }

    private void previousPage(){
        if (pages > 1){
            page--;
        }
        updateInformation();
    }

    private void nextPage(){
        if (page != pages){
            page++;
        }
        updateInformation();
    }


    public void updateInformation(){
        int temp = apiForTournament.getListOfParticipants().size() / apiForTournament.getCountOfParticipantsOnOnePage();
        if (apiForTournament.getListOfParticipants().size() % apiForTournament.getCountOfParticipantsOnOnePage() == 0)
            pages = temp;
        else
            pages = ++temp;
        if (pages == 0)
            pages++;

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


        currentPage.setText(page + " of " + pages);
        model.fireTableDataChanged();
    }

}
