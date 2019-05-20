package view;

import control.APIForTournament;
import exception.InvalidDataException;

import javax.swing.*;
import java.awt.*;

public class MainApi {
    private Table table;
    private APIForTournament apiForTournament;
    private Container container;
    private JFrame frame;

    public MainApi() throws InvalidDataException {
        frame = new JFrame();
        frame.setTitle("Lab1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        container = frame.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        apiForTournament = new APIForTournament();
        table = new Table(apiForTournament);
        container.add(table.getPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String... args) throws InvalidDataException {
        MainApi mainApi = new MainApi();

       // Tournament tournament = new Tournament("Tohsak", Sport.FOOTBALL, new FullName("ndknwk ekwndnek dnjw"), 600);
        //apiForTournament.addParticipant(tournament);
    }
}
