package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import formes.Forme;
import formes.Rectangle;
import formes.Cercle;
import formes.Triangle;
import outils.Dessin;
import outils.Gomme;

public class ZoneDessin extends JPanel {
    private List<Forme> formes;
    private List<Dessin> dessins;
    private Color couleurActuelle;
    private boolean modeDessin;
    private String modeForme;
    private boolean modeSelection;
    private Forme formeEnCours;
    private Forme formeSelectionnee;
    private boolean enDeplacement;
    private boolean redimensionnement;
    private Gomme gomme; // Référence à la classe Gomme

    public ZoneDessin() {
        formes = new ArrayList<>();
        dessins = new ArrayList<>();
        couleurActuelle = Color.BLACK;
        modeDessin = false;
        modeForme = null;
        modeSelection = false;
        formeEnCours = null;
        formeSelectionnee = null;
        setBackground(Color.WHITE);

        gomme = new Gomme(this); // Initialisation de l'objet Gomme

        MouseAdapter mouseAdapter = new MouseAdapter() {
            private int startX, startY;

            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();

                if (modeDessin) {
                    // Dessin libre
                    Dessin dessinEnCours = new Dessin(couleurActuelle);
                    dessinEnCours.ajouterPoint(e.getPoint());
                    dessins.add(dessinEnCours);
                    repaint();
                } else if (modeForme != null) {
                    // Création d'une forme
                    switch (modeForme) {
                        case "Rectangle" -> formeEnCours = new Rectangle(couleurActuelle, startX, startY);
                        case "Cercle" -> formeEnCours = new Cercle(couleurActuelle, startX, startY);
                        case "Triangle" -> formeEnCours = new Triangle(couleurActuelle, startX, startY);
                    }
                    formes.add(formeEnCours);
                } else if (modeSelection) {
                    // Sélection d'une forme ou redimensionnement
                    formeSelectionnee = null;
                    redimensionnement = false; // Démarrer sans redimensionnement
                    for (Forme forme : formes) {
                        if (forme.contientPoint(startX, startY)) {
                            formeSelectionnee = forme;
                            enDeplacement = true; // Permettre le déplacement après sélection
                            break;
                        } else if (forme.contientPoignee(startX, startY)) {
                            formeSelectionnee = forme;
                            redimensionnement = true; // Commencer le redimensionnement
                            break;
                        }
                    }
                    // Si une autre forme était sélectionnée avant, réinitialisez les poignées
                    if (formeSelectionnee != null) {
                        formeSelectionnee.dessinerPoignees((Graphics2D) getGraphics());
                    }
                } else if (modeGomme()) {
                    // Effacer sous le curseur
                    gomme.gestionGomme(e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int currentX = e.getX();
                int currentY = e.getY();

                if (modeDessin) {
                    // Continuer le dessin libre
                    Dessin dernierDessin = dessins.get(dessins.size() - 1);
                    dernierDessin.ajouterPoint(e.getPoint());
                    repaint();
                } else if (modeGomme()) {
                    // Mode gomme : effacer le dessin libre ou une forme sous le curseur
                    gomme.gestionGomme(e);
                } else if (formeEnCours != null) {
                    // Redimensionner la forme en cours
                    int largeur = currentX - startX;
                    int hauteur = currentY - startY;
                    formeEnCours.redimensionner(Math.abs(largeur), Math.abs(hauteur));
                    formeEnCours.x = Math.min(startX, currentX);
                    formeEnCours.y = Math.min(startY, currentY);
                    repaint();
                } else if (modeSelection && formeSelectionnee != null && enDeplacement) {
                    // Déplacer la forme sélectionnée
                    formeSelectionnee.deplacer(currentX - startX, currentY - startY);
                    startX = currentX;
                    startY = currentY;
                    repaint();
                } else if (redimensionnement && formeSelectionnee != null) {
                    // Redimensionner la forme sélectionnée
                    int newWidth = currentX - formeSelectionnee.x;
                    int newHeight = currentY - formeSelectionnee.y;
                    formeSelectionnee.redimensionner(newWidth, newHeight);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (formeEnCours != null) {
                    formeEnCours = null; // Fin de la création d'une forme
                }
                enDeplacement = false; // Fin du déplacement
                redimensionnement = false; // Fin du redimensionnement
            }
        };

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        setFocusable(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE && formeSelectionnee != null) {
                    // Supprimer la forme sélectionnée
                    formes.remove(formeSelectionnee);
                    formeSelectionnee = null;
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessin des formes
        for (Forme forme : formes) {
            forme.dessiner(g2d);
            if (forme == formeSelectionnee) { // Afficher les poignées uniquement pour la forme sélectionnée
                forme.dessinerPoignees(g2d);
            }
        }

        // Dessin libre
        for (Dessin dessin : dessins) {
            dessin.dessiner(g2d);
        }

    }

    public void activerModeDessin(boolean mode) {
        modeDessin = mode;
        modeForme = null; // Désactive les formes
        modeSelection = false; // Désactive le mode sélection
        formeSelectionnee = null; // Désélectionner toute forme
        gomme.activerModeGomme(false); // Désactive la gomme
    }

    public void activerModeGomme(boolean mode) {
        gomme.activerModeGomme(mode);
        modeDessin = false;
        modeForme = null;
        modeSelection = false;
        formeSelectionnee = null;
    }

    public void activerModeForme(String forme) {
        modeForme = forme;
        modeDessin = false; // Désactive le dessin libre
        modeSelection = false; // Désactive le mode sélection
        formeSelectionnee = null; // Désélectionner toute forme
        gomme.activerModeGomme(false); // Désactive la gomme
    }

    public void activerModeSelection() {
        modeSelection = true;
        modeDessin = false; // Désactive le dessin libre
        modeForme = null; // Désactive les formes
        formeSelectionnee = null; // Désélectionner toute forme
        gomme.activerModeGomme(false); // Désactive la gomme
    }

    public void definirCouleurActuelle(Color couleur) {
        couleurActuelle = couleur;
    }

    public boolean estEnModeDessin() {
        return modeDessin;
    }

    // Méthode pour gérer le mode gomme
    private boolean modeGomme() {
        return gomme.isModeGomme(); // Vérifie si le mode gomme est actif
    }

    // Méthode pour effacer sous le curseur
    public void effacerSousCurseur(int px, int py) {
        // Effacer les dessins libres
        dessins.removeIf(dessin -> dessin.contientPoint(px, py));

        // Effacer les formes
        formes.removeIf(forme -> forme.contientPoint(px, py));
    }


    public void sauvegarderImage(File fichier, String format) throws IOException {
        // Créer une image à partir de la zone de dessin
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        // Dessiner tout le contenu de la ZoneDessin sur l'image
        paint(g2d);
        g2d.dispose();

        // Enregistrer l'image dans un fichier
        ImageIO.write(image, format, fichier);
    }

}
