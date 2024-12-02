package formes;

import java.awt.*;

public class Rectangle extends Forme {

    // Constructeur de la classe Rectangle
    public Rectangle(Color couleur, int x, int y) {
        super(couleur, x, y);
    }

    // Méthode pour dessiner un rectangle
    @Override
    public void dessiner(Graphics2D g) {
        g.setColor(couleur);
        g.drawRect(x, y, largeur, hauteur);
    }

    // Méthode pour vérifier si un point (px, py) est à l'intérieur du rectangle
    @Override
    public boolean contientPoint(int px, int py) {
        return px >= x && px <= x + largeur && py >= y && py <= y + hauteur;
    }
}
