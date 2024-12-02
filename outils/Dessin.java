package outils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dessin {
    private List<Point> points; // Liste des points dessinés
    private Color couleur; // Couleur du dessin
    private static final int RAYON_TOLERANCE = 5; // Rayon de tolérance pour détecter si un point est dans le dessin

    public Dessin(Color couleur) {
        this.couleur = couleur;
        this.points = new ArrayList<>();
    }

    public void ajouterPoint(Point point) {
        points.add(point);
    }

    public void dessiner(Graphics2D g2d) {
        g2d.setColor(couleur);
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    // Méthode pour vérifier si un point (px, py) est proche d'un des points du dessin
    public boolean contientPoint(int px, int py) {
        for (Point point : points) {
            if (Math.abs(point.x - px) <= RAYON_TOLERANCE && Math.abs(point.y - py) <= RAYON_TOLERANCE) {
                return true;
            }
        }
        return false;
    }
}
