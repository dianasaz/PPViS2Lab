package view;

import exception.InvalidDataException;
import model.Sport;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;

public class Dialog extends JDialog {
    private JDialog dialog;
     JTextField textName;
     JTextField textDate;
     JComboBox<Sport> checkSport;
     JTextField textNameOfWinner;
     JTextField textPrize;
    private JPanel contents;
    private JFrame frame;
    JButton button;

    public Dialog(){
        frame = new JFrame();
        dialog = new JDialog();
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(400, 400);
        contents = new JPanel();
        panelSettingsMethod(contents);
        textName = new JTextField("  Tournament  ");
        textDate = new JTextField("  Date of tournament  ");
        checkSport = new JComboBox();
        checkSport.addItem(Sport.FOOTBALL);
        checkSport.addItem(Sport.HOCKEY);
        checkSport.addItem(Sport.GANDBALL);
        checkSport.addItem(Sport.SWIMMING);
        checkSport.addItem(Sport.PINGPONG);
        checkSport.addItem(Sport.TENNIS);

        textNameOfWinner = new JTextField("  winner  ");
        textPrize = new JTextField("   prize   ");
        button = new JButton();

        contents.add(textName);
        contents.add(textNameOfWinner);
        contents.add(textDate);
        contents.add(textPrize);
        contents.add(checkSport);
        contents.add(button);


        frame.getContentPane().add(contents);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        String header[] = new String[]{"name", "SPORT", "Name of Winner", "Prize", "Date"};
        Component[] components = {textName, checkSport, textNameOfWinner, textPrize, textDate};
        for(int i = 0; i < header.length; i++) {
           contents.add(new JLabel(header[i]));
           contents.add(components[i]);
        }

        contents.setLayout(new FlowLayout(FlowLayout.CENTER));

    }


    public void panelSettingsMethod(JPanel panel){
        // panel.setPreferredSize(new Dimension(200,120));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
    }

    public String getName(){
        return textName.getText();
    }

    public JTextField getPrize(){
        return textPrize;
    }

    public JTextField getDate(){
        return textDate;
    }

    public String getNameOfWinner(){
        return textNameOfWinner.getText();
    }



}
