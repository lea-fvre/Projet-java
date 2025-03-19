import service.*;
import trajet.*;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu principal:");
            System.out.println("1. Afficher les stations par ordre");
            System.out.println("2. Afficher les informations des lignes");
            System.out.println("3. Afficher les informations des stations");
            System.out.println("4. Calculer un itinéraire entre deux stations");
            System.out.println("0. Quitter");
            System.out.print("Choix: ");

            int choixMenu = scanner.nextInt();

            switch (choixMenu) {
                case 0:
                    running = false;
                    break;
                case 1:
                    afficherStationsParOrdre(scanner);
                    break;
                case 2:
                    afficherInfosLignes();
                    break;
                case 3:
                    afficherInfosStations();
                    break;
                case 4:
                    calculerItineraire(scanner);
                    break;
                default:
                    System.out.println("Erreur😅");
            }
        }

        System.out.println("Au revoir!👋");
        scanner.close();
    }


    private static void afficherStationsParOrdre(Scanner scanner) {
        System.out.println("Afficher la liste des stations par ordre :");
        System.out.println("1. Croissant");
        System.out.println("2. Décroissant");
        System.out.print("Choix 1 ou 2: ");

        int choix = scanner.nextInt();

        List<Station> stations;
        if (choix == 1) {
            stations = Station.getStationsByNameCroissant();
            System.out.println("\nStations par ordre croissant :");
        } else {
            stations = Station.getStationsByNameDecroissant();
            System.out.println("\nStations par ordre décroissant :");
        }

        for (Station station : stations) {
            System.out.println("- " + station.getNom());
        }
    }

    private static void afficherInfosLignes() {
        System.out.println("\nInformations sur les lignes:");

        LigneStations ligneStations = new LigneStations();
        Ligne[] lignes = Ligne.getLignes();

        for (Ligne ligne : lignes) {
            System.out.println("- Nom: " + ligne.getNom() + ", Type: " + ligne.getType());

            System.out.println("  Stations de cette ligne:");
            List<Station> stations = ligneStations.getStationsForLine(ligne);
            for (Station station : stations) {
                System.out.println("  - " + station.getNom());
            }
            System.out.println();
        }
    }

    private static void afficherInfosStations(){
        System.out.println("\nInformations sur les stations:");
        Station[] stations = Station.getStations();
        for (Station station : stations) {
            String reponse =  "";
            reponse += "- Nom: " + station.getNom();
            reponse += ", Latitude: " + station.getLatitude();
            reponse += ", Longitude: " + station.getLongitude();
            if (!station.getAcces().equals(" ")) {
                reponse += ", Acces: " + station.getAcces();
            }
            if (!station.getService().isEmpty()) {
                reponse += ", Service: " + station.getService();
            }
            System.out.println(reponse);
        }
    }

    private static void calculerItineraire(Scanner scanner) {
        scanner.nextLine();

        System.out.println("\nCalcul d'itinéraire");
        // Demander le type de passager
        System.out.println("Type de passager:");
        System.out.println("1. Régulier");
        System.out.println("2. Etudiant");
        System.out.println("3. Senior");
        System.out.println("4. Handicapé");
        System.out.print("Choix: ");
        int typeChoice = scanner.nextInt();

        TypePassager typePassager;
        switch(typeChoice) {
            case 1:
                typePassager = TypePassager.REGULIER;
                break;
            case 2:
                typePassager = TypePassager.ETUDIANT;
                break;
            case 3:
                typePassager = TypePassager.SENIOR;
                break;
            case 4:
                typePassager = TypePassager.HANDICAPE;
                break;
            default:
                typePassager = TypePassager.REGULIER;
                break;
        }

        System.out.println("Stations disponibles:");

        Station[] stations = Station.getStations();
        for (int i = 0; i < stations.length; i++) {
            if (typePassager == TypePassager.HANDICAPE && !stations[i].getAcces().contains("Accessible")) {
                continue;
            }
            System.out.println((i+1) + ". " + stations[i].getNom());
        }

        System.out.print("\nNuméro de la station de départ: ");
        int departIndex = scanner.nextInt();

        System.out.print("Numéro de la station d'arrivée: ");
        int arriveeIndex = scanner.nextInt();

        Station depart = stations[departIndex-1];
        Station arrivee = stations[arriveeIndex-1];

        Trajet trajet = Trajet.calculerTrajet(depart, arrivee, typePassager);

        // Affiche le résultat
        if (trajet != null) {
            trajet.afficherTrajet(typePassager);
        } else {
            System.out.println("Aucun itinéraire trouvé 😅");
        }
    }
}