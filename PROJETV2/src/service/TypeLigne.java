package service;

public enum TypeLigne {
    METRO(500), TRAM(400), BUS(300);

    private final double vitesse;

    TypeLigne(double vitesse) {
        this.vitesse = vitesse;
    }
    public double getVitesse() {
        return vitesse;
    }
}
