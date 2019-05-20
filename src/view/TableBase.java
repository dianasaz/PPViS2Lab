package view;

import control.APIForTournament;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TableBase {
    private JTable table;
    JPanel panel;
    MyTableModel model;
    private int page = 1;
    private int pages;
    private APIForTournament api;

    public TableBase(APIForTournament apiForTournament){
        this.api = apiForTournament;
        panel = new JPanel();
        panelSettingsMethod(panel);

        model = new MyTableModel(apiForTournament);
        table = new JTable(model);

        table.setSize(400,400);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));


        apiForTournament.setCountOfParticipantsOnOnePage(api.getListOfParticipants().size());

        update();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(table);

    }

    public void panelSettingsMethod(JPanel panel){
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
    }

    public JPanel getPanel(){
        return panel;
    }


    private void updateInformationOnScreen() {
        int start = (page - 1) * api.getCountOfParticipantsOnOnePage();
        int finish;
        if (api.getListOfParticipants().size() >= page * api.getCountOfParticipantsOnOnePage()) {
            finish = page * api.getCountOfParticipantsOnOnePage();
        } else {
            finish = api.getListOfParticipants().size();
        }
        api.getListOfParticipantOnScreen().clear();
        for (int i = start; i < finish; i++)
            api.getListOfParticipantOnScreen().add(api.getListOfParticipants().get(i));
    }

    private void updateAllPages(){
        int temp = api.getListOfParticipants().size() / api.getCountOfParticipantsOnOnePage();
        if (api.getListOfParticipants().size() % api.getCountOfParticipantsOnOnePage() == 0)
            pages = temp;
        else
            pages = ++temp;
        if (pages == 0)
            pages++;
    }

    public void update(){
        updateAllPages();
        updateInformationOnScreen();
    }
}
