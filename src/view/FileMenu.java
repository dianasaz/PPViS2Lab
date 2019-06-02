package view;

import control.APIForTournament;
import control.XmlParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileMenu {
    private APIForTournament api;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem load;
    private JMenuItem save;

    public FileMenu (APIForTournament apiForTournament, Table table){
        this.api = apiForTournament;
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File file = getChosenFile("Открыть файл");
                if (file != null) {
                    XmlParser.load(api, file);
                    table.updateInformation();
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                XmlParser.save(api, getChosenFile("Сохранить файл"));
            }
        });

        menu.add(load);
        menu.add(save);

        menuBar.add(menu);
    }

    private File getChosenFile(String string){
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, string);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            return file;
        }
        return null;
    }

    public JMenuBar getJMenuBar(){
        return menuBar;
    }
}
