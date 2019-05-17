package view;

import control.APIForTournament;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class TableBase {
    private JFrame frame;
    private JTable table;
   // private Container container;
    JPanel panel;
    MyTableModel model;

    public TableBase(APIForTournament apiForTournament){
        panel = new JPanel();
        panelSettingsMethod(panel);

        model = new MyTableModel(apiForTournament);
        table = new JTable(model);

        table.setSize(500,500);
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(table);

        apiForTournament.setCountOfParticipantsOnOnePage(apiForTournament.getListOfParticipants().size());
    }

    public void panelSettingsMethod(JPanel panel){
        //  panel.setPreferredSize(new Dimension(200,120));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
    }

    public JPanel getPanel(){
        return panel;
    }
}
