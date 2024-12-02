package ui;

import javax.swing.*;

import outils.Sauvegarde;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Menu {

    private ZoneDessin zoneDessin; // Référence à la zone de dessin
    private JPanel panneauOutils; // Panneau des outils

    public Menu(ZoneDessin zoneDessin, JPanel panneauOutils) {
        this.zoneDessin = zoneDessin;
        this.panneauOutils = panneauOutils;
    }

    public JMenuBar createMenuBar() {
        // Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu Fichier
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem itemNouveau = new JMenuItem("Nouveau");
        JMenuItem itemQuitter = new JMenuItem("Quitter");

        // Action pour "Nouveau"
        itemNouveau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajouter les outils
                afficherPanneauOutils(); 
            }
        });

        // Action pour "Quitter"
        itemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Quitter l'application
                System.exit(0); 
            }
        });
        menuFichier.add(itemNouveau);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);

        menuBar.add(menuFichier);

        return menuBar;
    }

    // Méthode pour afficher le panneau d'outils
    private void afficherPanneauOutils() {
        panneauOutils.removeAll();

        // Ajouter les boutons des outils
        JButton boutonSauvegarder = new JButton("Sauvegarder");
        boutonSauvegarder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir box de dialogue pour choisir emplacement et le format
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choisir l'emplacement de sauvegarde");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images PNG", "png"));
                fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images JPG", "jpg"));

                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String extension = fileChooser.getFileFilter().getDescription().contains("PNG") ? "png" : "jpg";

                    // Appeler la metryhode de sauvegarde
                    try {
                        Sauvegarde sauvegarde = new Sauvegarde(zoneDessin);
                        sauvegarde.sauvegarderImage(file, extension);
                        JOptionPane.showMessageDialog(null, "Image sauvegardée avec succès !");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde de l'image.");
                    }
                }
            }
        });
        panneauOutils.add(boutonSauvegarder);


        
        panneauOutils.add(new JButton("Ouvrir"));

        // Bouton "Sélection"
        JButton boutonSelection = new JButton("Sélection");
        boutonSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.activerModeSelection();
            }
        });
        panneauOutils.add(boutonSelection);

        // Bouton "Dessin"
        JButton boutonDessin = new JButton("Dessin");
        boutonDessin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean modeDessin = !zoneDessin.estEnModeDessin();
                zoneDessin.activerModeDessin(modeDessin);
            }
        });
        panneauOutils.add(boutonDessin);

        // Bouton "Gomme"
        JButton gommeButton = new JButton("Gomme");
        gommeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.activerModeGomme(true); // Activer la gomme
            }
        });
        panneauOutils.add(gommeButton);

        // Bouton "Rectangle"
        JButton rectangleButton = new JButton("Rectangle");
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.activerModeForme("Rectangle");
            }
        });
        panneauOutils.add(rectangleButton);

        // Bouton "Cercle"
        JButton cercleButton = new JButton("Cercle");
        cercleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.activerModeForme("Cercle");
            }
        });
        panneauOutils.add(cercleButton);

        // Bouton "Triangle"
        JButton triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.activerModeForme("Triangle");
            }
        });
        panneauOutils.add(triangleButton);

        // Couleurs basiques
        JButton redButton = new JButton();
        JButton greenButton = new JButton();
        JButton blueButton = new JButton();
        JButton blackButton = new JButton();
        JButton whiteButton = new JButton();
        JButton yellowButton = new JButton();
        JButton pinkButton = new JButton();
        JButton grayButton = new JButton();
        JButton orangeButton = new JButton();
        JButton cyanButton = new JButton();

        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        blackButton.setBackground(Color.BLACK);
        whiteButton.setBackground(Color.WHITE);
        yellowButton.setBackground(Color.YELLOW);
        pinkButton.setBackground(Color.PINK);
        grayButton.setBackground(Color.GRAY);
        orangeButton.setBackground(Color.ORANGE);
        cyanButton.setBackground(Color.CYAN);

        // Définir la taille des boutons pour qu'ils aient la même taille
        Dimension buttonSize = new Dimension(20, 20);
        redButton.setPreferredSize(buttonSize);
        greenButton.setPreferredSize(buttonSize);
        blueButton.setPreferredSize(buttonSize);
        blackButton.setPreferredSize(buttonSize);
        whiteButton.setPreferredSize(buttonSize);
        yellowButton.setPreferredSize(buttonSize);
        pinkButton.setPreferredSize(buttonSize);
        grayButton.setPreferredSize(buttonSize);
        orangeButton.setPreferredSize(buttonSize);
        cyanButton.setPreferredSize(buttonSize);

        // Ajout des actions pour chaque bouton de couleur
        // Bouton "Rouge"
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.RED);
            }
        });

        // Bouton "Vert"
        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.GREEN);
            }
        });

        // Bouton "Bleu"
        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.BLUE);
            }
        });

        // Bouton "Noir"
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.BLACK);
            }
        });

        // Bouton "Blanc"
        whiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.WHITE);
            }
        });

        // Bouton "Jaune"
        yellowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.YELLOW);
            }
        });

        // Bouton "Rose"
        pinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.PINK);
            }
        });

        // Bouton "Gris"
        grayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.GRAY);
            }
        });

        // Bouton "Orange"
        orangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.ORANGE);
            }
        });

        // Bouton "Cyan"
        cyanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoneDessin.definirCouleurActuelle(Color.CYAN);
            }
        });

        // Ajouter les boutons de couleur au panneau
        panneauOutils.add(redButton);
        panneauOutils.add(greenButton);
        panneauOutils.add(blueButton);
        panneauOutils.add(blackButton);
        panneauOutils.add(whiteButton);
        panneauOutils.add(yellowButton);
        panneauOutils.add(pinkButton);
        panneauOutils.add(grayButton);
        panneauOutils.add(orangeButton);
        panneauOutils.add(cyanButton);

        panneauOutils.revalidate();
        panneauOutils.repaint();
    }
}
