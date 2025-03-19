package service;

import java.util.*;

public class LigneStations {
    private final Map<Ligne, List<Station>> ligneStations;

    public LigneStations() {
        ligneStations = new HashMap<>();
        initialiserLignesStations();
    }

    private void initialiserLignesStations() {
        // MILLENNIUM
        ligneStations.put(Ligne.MILLENNIUM, Arrays.asList(
                Station.JUBILEE_PLACE,
                Station.KINGS_WAY,
                Station.GRAND_CENTRAL,
                Station.MARKET_SQUARE,
                Station.NORTHGATE_SHOPPING_CENTRE
        ));

        // HERITAGE
        ligneStations.put(Ligne.HERITAGE, Arrays.asList(
                Station.ASHFORD_ROAD,
                Station.BROOKSIDE_AVENUE,
                Station.FAIRFIELDS,
                Station.GRAND_CENTRAL,
                Station.TOWNHALL,
                Station.VICTORIA_DOCKS
        ));

        // UNIVERSITY
        ligneStations.put(Ligne.UNIVERSITY, Arrays.asList(
                Station.ASHFORD_ROAD,
                Station.CASTLE_HILL,
                Station.HIGH_STREET,
                Station.NORTHGATE_SHOPPING_CENTRE,
                Station.XENOBIOTICS_RESEARCH_FACILITY,
                Station.VICTORIA_DOCKS
        ));

        // RIVERVIEW
        ligneStations.put(Ligne.RIVERVIEW, Arrays.asList(
                Station.VICTORIA_DOCKS,
                Station.WATERFRONT,
                Station.UNION_STREET,
                Station.SOUTHBANK_PLACE
        ));

        // PARKLAND
        ligneStations.put(Ligne.PARKLAND, Arrays.asList(
                Station.PARKSIDE_PLACE,
                Station.JUBILEE_PLACE,
                Station.KINGS_WAY,
                Station.SOUTHBANK_PLACE,
                Station.GRAND_CENTRAL,
                Station.EASTBOURNE_WEST,
                Station.BROOKSIDE_AVENUE
        ));

        // MARKET
        ligneStations.put(Ligne.MARKET, Arrays.asList(
                Station.EASTBOURNE_WEST,
                Station.FAIRFIELDS,
                Station.MARKET_SQUARE,
                Station.VICTORIA_DOCKS
        ));

        // GREENWAY
        ligneStations.put(Ligne.GREENWAY, Arrays.asList(
                Station.ZEPHYR_CLOSE,
                Station.DUNHAM_END,
                Station.KINGS_WAY,
                Station.GRAND_CENTRAL,
                Station.FAIRFIELDS,
                Station.LAKESIDE,
                Station.HIGH_STREET
        ));

        // SOUTHERN_LOOP
        ligneStations.put(Ligne.SOUTHERN_LOOP, Arrays.asList(
                Station.EASTBOURNE_WEST,
                Station.IVY_LANE,
                Station.OAKWOODS,
                Station.QUEENSBRIDGE,
                Station.PARKSIDE_PLACE,
                Station.YORK_ROAD,
                Station.RIVERSIDE
        ));
    }

    public List<Station> getStationsForLine(Ligne ligne) {
        return ligneStations.getOrDefault(ligne, Collections.emptyList());
    }

    public Set<Ligne> getAllLines() {
        return ligneStations.keySet();
    }
}