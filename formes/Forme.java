package formes;

import java.awt.*;

public abstract class Forme {
    protected Color couleur;
    public int x; // Position initiale
    public int y;
    protected int largeur, hauteur; // Dimensions
    protected static final int TAILLE_POIGNET = 8; // Taille des poignées de redimensionnement

    public Forme(Color couleur, int x, int y) {
        this.couleur = couleur;
        this.x = x;
        this.y = y;
    }

    public abstract void dessiner(Graphics2D g);

    public abstract boolean contientPoint(int px, int py);

    // Méthodes de déplacement et de redimensionnement
    public void deplacer(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void redimensionner(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    // Dessine les poignées de redimensionnement (petits carrés autour de la forme)
    public void dessinerPoignees(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(x - TAILLE_POIGNET / 2, y - TAILLE_POIGNET / 2, TAILLE_POIGNET, TAILLE_POIGNET); // Coin supérieur gauche
        g.fillRect(x + largeur - TAILLE_POIGNET / 2, y - TAILLE_POIGNET / 2, TAILLE_POIGNET, TAILLE_POIGNET); // Coin supérieur droit
        g.fillRect(x - TAILLE_POIGNET / 2, y + hauteur - TAILLE_POIGNET / 2, TAILLE_POIGNET, TAILLE_POIGNET); // Coin inférieur gauche
        g.fillRect(x + largeur - TAILLE_POIGNET / 2, y + hauteur - TAILLE_POIGNET / 2, TAILLE_POIGNET, TAILLE_POIGNET); // Coin inférieur droit
    }

    // Vérifie si un point est dans une des poignées
    public boolean contientPoignee(int px, int py) {
        return (px >= x - TAILLE_POIGNET / 2 && px <= x + TAILLE_POIGNET / 2) && 
               (py >= y - TAILLE_POIGNET / 2 && py <= y + TAILLE_POIGNET / 2) ||
               (px >= x + largeur - TAILLE_POIGNET / 2 && px <= x + largeur + TAILLE_POIGNET / 2) &&
               (py >= y - TAILLE_POIGNET / 2 && py <= y + TAILLE_POIGNET / 2) ||
               (px >= x - TAILLE_POIGNET / 2 && px <= x + TAILLE_POIGNET / 2) && 
               (py >= y + hauteur - TAILLE_POIGNET / 2 && py <= y + hauteur + TAILLE_POIGNET / 2) ||
               (px >= x + largeur - TAILLE_POIGNET / 2 && px <= x + largeur + TAILLE_POIGNET / 2) && 
               (py >= y + hauteur - TAILLE_POIGNET / 2 && py <= y + hauteur + TAILLE_POIGNET / 2);
    }
}
