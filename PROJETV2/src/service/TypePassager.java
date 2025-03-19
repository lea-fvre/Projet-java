package service;

public enum TypePassager {
    REGULIER(false,1),
    ETUDIANT(false,0.8),
    SENIOR(false,0.65),
    HANDICAPE(true,0.5);


    private final boolean AccessibilitePMR;
    private final double Coeff_tarifaire;

    TypePassager(boolean AccessibilitePMR, double Coeff_tarifaire) {
        this.Coeff_tarifaire = Coeff_tarifaire;
        this.AccessibilitePMR = AccessibilitePMR;
    }

    public boolean getAccessibilitePMR() {
        return AccessibilitePMR;
    }

    public double getCoeff_tarifaire() {
        return Coeff_tarifaire;
    }
}