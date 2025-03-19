package trajet;

import service.*;
import java.util.*;

public class Trajet {
    private Station depart;
    private Station arrivee;
    private List<EtapeTrajet> etapes;
    private double distanceTotale;
    private double tempsTotalMinutes;

    public Trajet(Station depart, Station arrivee) {
        this.depart = depart;
        this.arrivee = arrivee;
        this.etapes = new ArrayList<>();
        this.distanceTotale = 0;
        this.tempsTotalMinutes = 0;
    }

    public static Trajet calculerTrajet(Station depart, Station arrivee, TypePassager typePassager) {
        Trajet trajet = new Trajet(depart, arrivee);

        // Créer le graphe du réseau
        Map<Station, Map<Station, LigneConnection>> graph = construireGraphe(typePassager);

        // Algorithme de Dijkstra : pr trouver chemin + rapide
        Map<Station, Double> temps = new HashMap<>();
        Map<Station, Station> precedent = new HashMap<>();
        Map<Station, Ligne> ligneUtilisee = new HashMap<>();
        PriorityQueue<Station> file = new PriorityQueue<>(
                Comparator.comparingDouble(temps::get));

        // Initialisation
        for (Station station : Station.getStations()) {
            temps.put(station, Double.MAX_VALUE);
        }
        temps.put(depart, 0.0);
        file.add(depart);

        while (!file.isEmpty()) {

            Station stationEnCours = file.poll();

            if (stationEnCours.equals(arrivee)) {
                break;
            }

            if (graph.containsKey(stationEnCours)) {
                for (Map.Entry<Station, LigneConnection> voisin : graph.get(stationEnCours).entrySet()) {
                    Station prochaine = voisin.getKey();
                    Ligne ligne = voisin.getValue().ligne;
                    double tempsParcours = voisin.getValue().tempsMinutes;
                    double nouveauTemps = temps.get(stationEnCours) + tempsParcours;

                    if (nouveauTemps < temps.get(prochaine)) {
                        temps.put(prochaine, nouveauTemps);
                        precedent.put(prochaine, stationEnCours);
                        ligneUtilisee.put(prochaine, ligne);

                        file.remove(prochaine);
                        file.add(prochaine);
                    }
                }
            }
        }

        // Reconstituer le trajet
        if (!precedent.containsKey(arrivee)) {
            return null;
        }

        // Construction du trajet en partant de l'arrivée
        List<EtapeTrajet> etapesList = new ArrayList<>();
        Station stationEnCours = arrivee;
        double distanceTotale = 0;
        double tempsTotalMinutes = 0;

        while (precedent.containsKey(stationEnCours)) {
            Station precedenteStation = precedent.get(stationEnCours);
            Ligne ligne = ligneUtilisee.get(stationEnCours);
            double distance = calculerDistance(precedenteStation, stationEnCours);
            double tempsMinutes = calculerTemps(distance, ligne.getType().getVitesse());

            etapesList.add(0, new EtapeTrajet(precedenteStation, stationEnCours, ligne, distance));
            distanceTotale += distance;
            tempsTotalMinutes += tempsMinutes;

            stationEnCours = precedenteStation;
        }

        trajet.etapes = etapesList;
        trajet.distanceTotale = distanceTotale;
        trajet.tempsTotalMinutes = tempsTotalMinutes;

        return trajet;
    }

    private static Map<Station, Map<Station, LigneConnection>> construireGraphe(TypePassager typePassager) {
        Map<Station, Map<Station, LigneConnection>> graph = new HashMap<>();
        LigneStations ligneStations = new LigneStations();

        // Pour chaque ligne
        for (Ligne ligne : Ligne.getLignes()) {
            List<Station> stations = ligneStations.getStationsForLine(ligne);

            // Connecter chaque station avec la suivante dans la ligne
            for (int i = 0; i < stations.size() - 1; i++) {
                Station current = stations.get(i);
                Station next = stations.get(i + 1);

                if (typePassager.getAccessibilitePMR() &&
                        (current.getAcces().contains("❌♿") || next.getAcces().contains("❌♿"))) {
                    continue;
                }

                double distance = calculerDistance(current, next);

                // Ajouter connexion dans les deux sens
                addConnection(graph, current, next, ligne, distance);
                addConnection(graph, next, current, ligne, distance);
            }
        }
        return graph;
    }

    private static void addConnection(Map<Station, Map<Station, LigneConnection>> graph,
                                      Station from, Station to, Ligne ligne, double distance) {
        if (!graph.containsKey(from)) {
            graph.put(from, new HashMap<>());
        }
        // Calculer le temps de parcours en minutes
        double tempsMinutes = calculerTemps(distance, ligne.getType().getVitesse());
        graph.get(from).put(to, new LigneConnection(ligne, distance, tempsMinutes));
    }

    // Calcule le temps de parcours en minutes à partir de la distance (km) et la vitesse (m/s)
    private static double calculerTemps(double distance, double vitesse) {
        // Convertir distance de km en m et vitesse en m/s pour calculer temps en secondes
        double tempsSecondes = (distance * 1000) / vitesse;
        // Convertir en minutes
        return tempsSecondes / 60;
    }

    private static double calculerDistance(Station s1, Station s2) {
        // Calcul de distance à vol d'oiseau (formule de Haversine)
        final double R = 6364.673; // Rayon de la Terre en km

        double latDistance = Math.toRadians(s2.getLatitude() - s1.getLatitude());
        double lonDistance = Math.toRadians(s2.getLongitude() - s1.getLongitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(s1.getLatitude())) * Math.cos(Math.toRadians(s2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // Distance en km
    }

    public void afficherTrajet(TypePassager typePassager) {
        System.out.println("Trajet de " + depart.getNom() + " à " + arrivee.getNom() + ":");
        System.out.println("Distance totale: " + String.format("%.2f", distanceTotale) + " km");
        System.out.println("Temps estimé: " + String.format("%.2f", tempsTotalMinutes) + " minutes");

        // Calculer / afficher le prix
        double prix = Prix.calculerPrix(this, typePassager);
        System.out.println("Prix du trajet: " + Prix.formatPrix(prix));


        System.out.println("Étapes:");

        Ligne ligneCourante = null;

        for (EtapeTrajet etape : etapes) {
            if (ligneCourante == null || ligneCourante != etape.getLigne()) {
                // Changement de ligne
                ligneCourante = etape.getLigne();
                System.out.println("- Prendre " + ligneCourante.getType() + " " + ligneCourante.getNom() +
                        " (vitesse: " + ligneCourante.getType().getVitesse() + " m/s)");
            }

            double tempsEtape = calculerTemps(etape.getDistance(), ligneCourante.getType().getVitesse());
            System.out.println("  • " + etape.getDepart().getNom() + " → " + etape.getArrivee().getNom() +
                    " (" + String.format("%.2f", etape.getDistance()) + " km, " +
                    String.format("%.2f", tempsEtape) + " min)");
        }
    }

    public Station getDepart() {
        return depart;
    }

    public Station getArrivee() {
        return arrivee;
    }

    public List<EtapeTrajet> getEtapes() {
        return etapes;
    }

    public double getDistanceTotale() {
        return distanceTotale;
    }

    public double getTempsTotalMinutes() {
        return tempsTotalMinutes;
    }

    // Classe interne pour représenter une connexion entre deux stations
    private static class LigneConnection {
        Ligne ligne;
        double distance;
        double tempsMinutes;

        LigneConnection(Ligne ligne, double distance, double tempsMinutes) {
            this.ligne = ligne;
            this.distance = distance;
            this.tempsMinutes = tempsMinutes;
        }
    }
}