package outils;

import java.awt.event.MouseEvent;
import ui.ZoneDessin;

public class Gomme {
    private boolean modeGomme; // Indique si le mode gomme est activé
    private ZoneDessin zoneDessin; // Référence à la zone de dessin pour effectuer l'effacement

    public Gomme(ZoneDessin zoneDessin) {
        this.zoneDessin = zoneDessin;
        this.modeGomme = false; // Le mode gomme est désactivé par défaut
    }

    // Active ou désactive le mode gomme
    public void activerModeGomme(boolean mode) {
        modeGomme = mode;
    }

    
    public boolean isModeGomme() {
        return modeGomme;
    }

    
    public void gestionGomme(MouseEvent e) {
        if (modeGomme) {
            int x = e.getX();
            int y = e.getY();
            zoneDessin.effacerSousCurseur(x, y); 
            zoneDessin.repaint(); 
        }
    }

}
