package service;

public class Ligne {
    public static final Ligne MILLENNIUM = new Ligne("MILLENNIUM", TypeLigne.METRO);
    public static final Ligne HERITAGE = new Ligne("HERITAGE", TypeLigne.METRO);
    public static final Ligne UNIVERSITY = new Ligne("UNIVERSITY", TypeLigne.BUS);
    public static final Ligne RIVERVIEW = new Ligne("RIVERVIEW", TypeLigne.BUS);
    public static final Ligne PARKLAND = new Ligne("PARKLAND", TypeLigne.TRAM);
    public static final Ligne MARKET = new Ligne("MARKET", TypeLigne.TRAM);
    public static final Ligne GREENWAY = new Ligne("GREENWAY", TypeLigne.BUS);
    public static final Ligne SOUTHERN_LOOP = new Ligne("SOUTHERN_LOOP", TypeLigne.BUS);

    private final String nom;
    private final TypeLigne type;

    private Ligne(String nom, TypeLigne type) {
        this.nom = nom;
        this.type = type;
    }

    public static Ligne[] getLignes() {
        return new Ligne[] {MILLENNIUM, HERITAGE, UNIVERSITY, RIVERVIEW, PARKLAND, MARKET, GREENWAY, SOUTHERN_LOOP};
    }

    public String getNom() {
        return nom;
    }

    public TypeLigne getType() {
        return type;
    }

    public String toString() {
        return nom + " - " + type;
    }
}