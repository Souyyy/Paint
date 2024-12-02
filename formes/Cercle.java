package formes;

import java.awt.*;


// Classe Cercle qui hérite de la classe Forme
public class Cercle extends Forme {

    // Constructeur de la classe Cercle
    public Cercle(Color couleur, int x, int y) {
        super(couleur, x, y);
    }

    // Méthode pour dessiner un cercle
    @Override
    public void dessiner(Graphics2D g) {
        g.setColor(couleur);
        g.drawOval(x, y, largeur, hauteur);
    }

    // Méthode pour vérifier si un point (px, py) est à l'intérieur du cercle
    @Override
    public boolean contientPoint(int px, int py) {
        // Calcul des coordonnées du centre du cercle et du rayon
        double centerX = x + largeur / 2.0;
        double centerY = y + hauteur / 2.0;
        double radius = Math.min(largeur, hauteur) / 2.0;
        return Math.pow(px - centerX, 2) + Math.pow(py - centerY, 2) <= Math.pow(radius, 2);
    }
}
