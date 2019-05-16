package view;

import control.APIForTournament;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Table {
    private JPanel panel;

    private JButton addToTableBtn;
    private JButton buttonSearch;
    private JButton buttonRemove;

    private JButton firstPage;
    private JButton lastPage;
    private JButton previusPage;
    private JButton nextPage;
  //  private JButton currentPage;

    private JButton fiveTournamentsOnPage;
    private JButton tenTournamentsOnPage;
    private JButton twentyTournamentsOnPage;

    private JTable table;
    private MyTableModel model;
   // private APIForTournament api;

    public Table(APIForTournament api){
        panel = new JPanel();
        panelSettingsMethod(panel);

        addToTableBtn = new JButton("Добавить в таблицу");
        buttonSearch = new JButton("Поиск");
        buttonRemove = new JButton("Удалить");

        firstPage = new JButton("1");
        lastPage = new JButton("last");
        previusPage = new JButton("<<");
        nextPage = new JButton(">>");
       // currentPage = new JButton(api.getCurrentPage());

        fiveTournamentsOnPage = new JButton("5");
        tenTournamentsOnPage = new JButton("10");
        twentyTournamentsOnPage = new JButton("20");

        model = new MyTableModel(api);
        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(250, 100));

        addToTableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        fiveTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(5);
            }
        });

        tenTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(10);
            }
        });

        twentyTournamentsOnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setCountOfParticipantsOnOnePage(20);
            }
        });

        firstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setFirstPage();
            }
        });

        lastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.setLastPage();
            }
        });

        nextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.nextPage();
            }
        });

        previusPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                api.previousPage();
            }
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(table);
        panel.add(addToTableBtn);
        panel.add(buttonSearch);
        panel.add(buttonRemove);
        panel.add(firstPage);
        panel.add(previusPage);
        panel.add(lastPage);
        panel.add(nextPage);
        panel.add(fiveTournamentsOnPage);
        panel.add(tenTournamentsOnPage);
        panel.add(twentyTournamentsOnPage);
 //       panel.add(currentPage);
    }

    public void panelSettingsMethod(JPanel panel){
        // panel.setPreferredSize(new Dimension(200,120));
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
        return panel;
    }
}
