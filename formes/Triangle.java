package formes;

import java.awt.*;

public class Triangle extends Forme {

    // Constructeur de la classe Triangle
    public Triangle(Color couleur, int x, int y) {
        super(couleur, x, y);
    }

    // Méthode pour dessiner un triangle
    @Override
    public void dessiner(Graphics2D g) {
        int[] xPoints = {x, x + largeur / 2, x + largeur};
        int[] yPoints = {y + hauteur, y, y + hauteur};
        g.setColor(couleur);
        g.drawPolygon(xPoints, yPoints, 3);
    }

    // Méthode pour vérifier si un point (px, py) est à l'intérieur du triangle
    @Override
    public boolean contientPoint(int px, int py) {
        int[] xPoints = {x, x + largeur / 2, x + largeur};
        int[] yPoints = {y + hauteur, y, y + hauteur};
        Polygon polygon = new Polygon(xPoints, yPoints, 3);
        return polygon.contains(px, py);
    }
}
