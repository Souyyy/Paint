import ui.Menu;
import ui.ZoneDessin;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);

        // Zone de dessin
        ZoneDessin zoneDessin = new ZoneDessin();
        frame.add(zoneDessin, BorderLayout.CENTER);

        // Panneau pour les outils
        JPanel panneauOutils = new JPanel();
        panneauOutils.setLayout(new FlowLayout(FlowLayout.LEFT));
        frame.add(panneauOutils, BorderLayout.NORTH);

        // Menu
        Menu menu = new Menu(zoneDessin, panneauOutils);
        frame.setJMenuBar(menu.createMenuBar());

        // Affichage de la fenêtre
        frame.setVisible(true);

    }

}
