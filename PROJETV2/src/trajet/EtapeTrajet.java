package trajet;

import service.Ligne;
import service.Station;

public class EtapeTrajet {
    private Station depart;
    private Station arrivee;
    private Ligne ligne;
    private double distance;

    public EtapeTrajet(Station depart, Station arrivee, Ligne ligne, double distance) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.ligne = ligne;
        this.distance = distance;
    }

    public Station getDepart() {
        return depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public double getDistance() {
        return distance;
    }
}