package view;

import control.APIForTournament;
import control.specification.ByNameOfTournamentSpecification;
import control.specification.ByNameOfWinnerSpecification;
import control.specification.ByPrizeOfWinnerSpecification;
import control.specification.ByPrizeSpecification;
import control.specification.BySportSpecification;
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
    private JButton previousPage;
    private JButton nextPage;
    private JLabel currentPage;

    private JButton fiveTournamentsOnPage;
    private JButton tenTournamentsOnPage;
    private JButton twentyTournamentsOnPage;

    private JTable table;
    private MyTableModel model;

   // private JScrollPane scrollPane;

    private int page = 1;
//    private List<Tournament> listOfParticipantOnScreen;
 //   private int countOfParticipantsOnOnePage;
    private int pages;
    private APIForTournament apiForTournament;
   // private APIForTournament api;

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
    //    scrollPane = new JScrollPane(table);

        page = 1;
        pages = 1;

        apiForTournament.addParticipant(new Tournament("Games", Sport.FOOTBALL, "Vasya Pupkin", 600, 21));
        apiForTournament.addParticipant(new Tournament("Olympic games", Sport.FOOTBALL, "Petya Kozlov", 200, 12));
        apiForTournament.addParticipant(new Tournament("European games", Sport.FOOTBALL, "Vlad Ponchikod", 400, 14));

        update();
        table.setSize(500,500);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));

        addToTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Dialog dialog = new Dialog(5);
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
                            Tournament tournament = new Tournament(dialog.getName(), (Sport) dialog.checkSport.getSelectedItem(), dialog.getNameOfWinner(), Integer.valueOf(dialog.textPrize.getText()), Integer.valueOf(dialog.textDate.getText()));
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
                Dialog dialog = new Dialog(6);
                dialog.checkSport.addItem(Sport.NOTHING);
                dialog.button.setText("search");
                dialog.button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        List<Tournament> list = new ArrayList<>();
                        if (dialog.textName.getText() != null || dialog.textName.getText() != "") {
                            list = apiForTournament.findBy(new ByNameOfTournamentSpecification(dialog.textName.getText()));
                            logger.log(Level.INFO, "Find by name");
                        }/* else if (dialog.textDate.getText() != "") {
                            list = apiForTournament.findBy(new B(dialog.textNameOfWinner.getText()));
                            logger.log(Level.INFO, "Find by date");
                        }*/
                        if (list.size() == 0) {
                            if (dialog.checkSport.getSelectedItem() != Sport.NOTHING) {
                                list = apiForTournament.findBy(new BySportSpecification((Sport) dialog.checkSport.getSelectedItem()));
                                logger.log(Level.INFO, "Find by sport");
                            } else if (dialog.textNameOfWinner.getText() != null || dialog.textNameOfWinner.getText() != "") {
                                list = apiForTournament.findBy(new ByNameOfWinnerSpecification(dialog.textNameOfWinner.getText()));
                                logger.log(Level.INFO, "Find by name of winner");
                            }
                        }
                        if (list.size() == 0) {
                            if (dialog.prizeOfWinner.getText() != null || dialog.prizeOfWinner.getText() != "") {
                                String diapason = dialog.prizeOfWinner.getText();
                                String[] strings = diapason.split(" ");
                                list = apiForTournament.findBy(new ByPrizeOfWinnerSpecification(Double.valueOf(strings[0]), Double.valueOf(strings[1])));
                                logger.log(Level.INFO, "Find by prize of winner");
                            } else if (dialog.textPrize.getText() != null || dialog.textPrize.getText() != "") {
                                list = apiForTournament.findBy(new ByPrizeSpecification(Integer.valueOf(dialog.textPrize.getText())));
                                logger.log(Level.INFO, "Find by prize");
                            }
                        }

                            JFrame frameOne = new JFrame();
                            frameOne.setTitle("result of search");
                            frameOne.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            frameOne.setSize(800, 500);

                            JLabel countAfterSearch = new JLabel("Result of search: " + String.valueOf(apiForTournament.getCountAfterSearch()));

                            Container containerOne = frameOne.getContentPane();
                            containerOne.setLayout(new FlowLayout(FlowLayout.CENTER));

                            APIForTournament searchTournament = new APIForTournament();
                            for (Tournament tournament : list) {
                                searchTournament.addParticipant(tournament);
                            }
                            TableBase tableBase = new TableBase(searchTournament);

                            containerOne.add(tableBase.getPanel());
                            containerOne.add(countAfterSearch);
                            frameOne.setLocationRelativeTo(null);
                            frameOne.setVisible(true);
                        }
                });
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
                        if (dialog.getName() == null| dialog.getNameOfWinner() == null | dialog.getPrize().getText() == null | dialog.getDate() == null) {
                            try {
                                throw new InvalidDataException("Check information");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            if (dialog.textName != null){
                            List<Tournament> list= apiForTournament.findBy(new ByNameOfTournamentSpecification(dialog.textName.getText()));
                            for (Tournament tournament: list){
                                try {
                                    apiForTournament.deleteParticipant(tournament);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                            update();
                        }
                        }
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
                currentPage.setText("1");
                model.fireTableDataChanged();
            }
        });

        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLastPage();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(getPages()));
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPage();
               // update();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(page));
            }
        });

        previousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousPage();
              //  update();
                model.fireTableDataChanged();
                currentPage.setText(String.valueOf(page));
            }
        });

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
     //   mainPanel.add(scrollPane);
        mainPanel.add(table);
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

    public int getPage(){
        return page;
    }

    //public int getNextPage(){
     //   return currentPage++;
    //}

    //public int getPrevPage(){
   //     return currentPage--;
   // }

    public void setCurrentPage(int page){
        this.page = page;
    }

 //   public int getCountOfParticipantsOnOnePage(){
     //   return countOfParticipantsOnOnePage;
 //   }
/*
    public void setCountOfParticipantsOnOnePage(int count){
        if (count > 0) {
            this.countOfParticipantsOnOnePage = count;
        }
        update();
    }

    //public void setListOfParticipantOnScreen(){}

    public List<Tournament> getListOfParticipantOnScreen(){
        return listOfParticipantOnScreen;
    }
*/
    public int getPages(){
        return pages;
    }

    public void setPages(){
        if (apiForTournament.getListOfParticipants().size() %  apiForTournament.getCountOfParticipantsOnOnePage() == 0)
            pages = apiForTournament.getListOfParticipants().size() / apiForTournament.getCountOfParticipantsOnOnePage();
        else pages = apiForTournament.getListOfParticipants().size() / apiForTournament.getCountOfParticipantsOnOnePage() + 1;

    }

    public void setFirstPage(){
        page = 1;
        updateInformationOnScreen();
    }

    public void setLastPage(){
        page = getPages();
        updateInformationOnScreen();
    }

    public void previousPage(){
        if (pages > 1){
            page--;
        }
        updateInformationOnScreen();
    }

    public void nextPage(){
        if (page != pages){
            page++;
        }
        updateInformationOnScreen();
    }
/*
    public void setListOfParticipants(List<Tournament> tournaments) {
        apiForTournament.getListOfParticipants() = tournaments;
        currentPage = 1;
        update();
    }

    public List<Tournament> getListOfParticipants() {
        return listOfParticipants;
    }
*/
    private void updateInformationOnScreen() {
        int start = (page - 1) * apiForTournament.getCountOfParticipantsOnOnePage();
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
        model.fireTableDataChanged();
    }
/*
    public void setListOfParticipantOnScreen(List<Tournament> listOfParticipantOnScreen) {
        apiForTournament.setListOfParticipantOnScreen() = listOfParticipantOnScreen;
    }*/
}
