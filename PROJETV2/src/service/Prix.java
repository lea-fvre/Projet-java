package service;

import trajet.EtapeTrajet;
import trajet.Trajet;

public class Prix {
    // Prix de base en euros
    private static final double PRIX_BASE = 1.50;

    // Prix par station selon le type de transport (en euros)
    private static final double PRIX_STATION_METRO = 0.40;
    private static final double PRIX_STATION_TRAM = 0.30;
    private static final double PRIX_STATION_BUS = 0.20;

    public static double calculerPrix(Trajet trajet, TypePassager typePassager) {
        double prixTotal = PRIX_BASE;


        for (EtapeTrajet etape : trajet.getEtapes()) {
            Ligne ligne = etape.getLigne();
            TypeLigne typeLigne = ligne.getType();

            switch (typeLigne) {
                case METRO:
                    prixTotal += PRIX_STATION_METRO;
                    break;
                case TRAM:
                    prixTotal += PRIX_STATION_TRAM;
                    break;
                case BUS:
                    prixTotal += PRIX_STATION_BUS;
                    break;
            }
        }

        prixTotal *= typePassager.getCoeff_tarifaire();

        return prixTotal;
    }

    public static String formatPrix(double prix) {
        return String.format("%.2f€", prix);
    }
}