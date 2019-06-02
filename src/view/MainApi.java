package view;

import control.APIForTournament;

import javax.swing.*;
import java.awt.*;

public class MainApi {
    private Table table;
    private APIForTournament apiForTournament;
    private Container container;
    private JFrame frame;
    private FileMenu fileMenu;

    public MainApi() throws InvalidDataException {
        frame = new JFrame();
        frame.setTitle("Lab1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 500);

        container = frame.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        apiForTournament = new APIForTournament();
        table = new Table(apiForTournament, 1);
        fileMenu = new FileMenu(apiForTournament, table);
        frame.setJMenuBar(fileMenu.getJMenuBar());
        container.add(table.getPanel());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String... args) throws InvalidDataException {
        new MainApi();
    }
}
