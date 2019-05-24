package view;

import model.Sport;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.text.ParseException;
import java.util.Date;

public class Dialog extends JDialog {
    private JDialog dialog;
    JTextField textName;
    DatePicker textDate;
    JComboBox<Sport> checkSport;
    JTextField textNameOfWinner;
    JTextField prizeOfWinner;
    JTextField textPrize;
    private JPanel fieldsPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JFrame frame;
    JButton button;

    public Dialog(int fields) {
        frame = new JFrame();
        dialog = new JDialog();
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fieldsPanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();
        panelSettingsMethod(mainPanel);
        panelSettingsMethod(buttonPanel);
        panelSettingsMethod(fieldsPanel);
        textName = new JTextField();
        textDate = new DatePicker();
        checkSport = new JComboBox();
        checkSport.addItem(Sport.NOTHING);
        checkSport.addItem(Sport.FOOTBALL);
        checkSport.addItem(Sport.HOCKEY);
        checkSport.addItem(Sport.GANDBALL);
        checkSport.addItem(Sport.SWIMMING);
        checkSport.addItem(Sport.PINGPONG);
        checkSport.addItem(Sport.TENNIS);

        textNameOfWinner = new JTextField();
        textPrize = new JTextField();

        button = new JButton();

        fieldsPanel.add(textName);
        fieldsPanel.add(textNameOfWinner);
        fieldsPanel.add(textDate.getDatePicker());
        fieldsPanel.add(textPrize);
        fieldsPanel.add(checkSport);
        buttonPanel.add(button);

        mainPanel.add(fieldsPanel);
        mainPanel.add(buttonPanel);

        fieldsPanel.setLayout(new GridLayout(6, 2, 15, 15));

        if (fields == 5) {
            String header[] = new String[]{"name", "SPORT", "Name of Winner", "Prize", "Date"};
            Component[] components = {textName, checkSport, textNameOfWinner, textPrize, textDate.getDatePicker()};
            for (int i = 0; i < header.length; i++) {
                fieldsPanel.add(new JLabel(header[i]));
                fieldsPanel.add(components[i]);
            }
        }
        if (fields == 6) {
            prizeOfWinner = new JTextField();
            fieldsPanel.add(prizeOfWinner);
            String header[] = new String[]{"name", "SPORT", "Name of Winner", "Prize", "Date", "winner prize"};
            Component[] components = {textName, checkSport, textNameOfWinner, textPrize, textDate.getDatePicker(), prizeOfWinner};
            for (int i = 0; i < header.length; i++) {
                fieldsPanel.add(new JLabel(header[i]));
                fieldsPanel.add(components[i]);
            }
        }
        frame.getContentPane().add(mainPanel);
        frame.setSize(360, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        dialog.pack();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    }

    public void panelSettingsMethod(JPanel panel) {
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)));
    }

    public String getTextName() {
        return textName.getText();
    }

    public String getPrize() {
        return textPrize.getText();
    }

    public Date getDate() throws ParseException {
        return textDate.getDate();
    }

    public String getNameOfWinner() {
        return textNameOfWinner.getText();
    }

    public String getPrizeOfWinner() {
        return prizeOfWinner.getText();
    }

}
