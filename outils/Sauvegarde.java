package outils;

import ui.ZoneDessin;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sauvegarde {

    private ZoneDessin zoneDessin;

    // Constructeur pour initialiser la classe avec la ZoneDessin
    public Sauvegarde(ZoneDessin zoneDessin) {
        this.zoneDessin = zoneDessin;
    }

    // Méthode pour sauvegarder la ZoneDessin sous forme d'image
    public void sauvegarderImage(File fichier, String format) throws IOException {
        BufferedImage image = new BufferedImage(zoneDessin.getWidth(), zoneDessin.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        zoneDessin.paint(g2d);
        g2d.dispose(); 
        ImageIO.write(image, format, fichier);
    }
}