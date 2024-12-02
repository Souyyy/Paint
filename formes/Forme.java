package formes;

import java.awt.*;

// Classe abstraite Forme, servant de base pour toutes les formes
public abstract class Forme {
    protected Color couleur;
    // Position initiale
    public int x; 
    public int y;
    protected int largeur, hauteur; 

    // Taille des poignes de redimensionnement
    protected static final int TAILLE_POIGNET = 8; 

    // Constructeur pour init la couleur et la position
    public Forme(Color couleur, int x, int y) {
        this.couleur = couleur;
        this.x = x;
        this.y = y;
    }

    // Méthode abstraite
    public abstract void dessiner(Graphics2D g);
    public abstract boolean contientPoint(int px, int py);

    // Methode de déplacement 
    public void deplacer(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Methode pour redimensionner la forme
    public void redimensionner(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    // Dessine les poignees de redimensionnement (petits carrés autour de la forme)
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
