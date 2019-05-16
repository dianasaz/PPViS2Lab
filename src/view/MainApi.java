package view;

import control.APIForTournament;
import model.FullName;
import model.Sports;
import model.Tournament;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class MainApi {
    private Table table;
    private APIForTournament apiForTournament;
    private Container container;
    private JFrame frame;

    public MainApi(){
        frame = new JFrame();
        frame.setTitle("Lab1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container = frame.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        apiForTournament = new APIForTournament();
        table = new Table(apiForTournament);
        container.add(table.getPanel());
        frame.setVisible(true);
    }

    public static void main(String... args){
        MainApi mainApi = new MainApi();

       // Tournament tournament = new Tournament("Tohsak", Sports.FOOTBALL, new FullName("ndknwk ekwndnek dnjw"), 600);
        //apiForTournament.addParticipant(tournament);
    }
}
