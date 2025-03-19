package service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Station {
    public static final Station ASHFORD_ROAD = new Station("ASHFORD_ROAD", 52.650221, -1.298728, "Inaccessible PMR ❌♿ ", "💼ℹ️💳");
    public static final Station BROOKSIDE_AVENUE = new Station("BROOKSIDE_AVENUE", 52.647475, -1.290910, "Accessible PMR ♿ ", "💳");
    public static final Station CASTLE_HILL = new Station("CASTLE_HILL", 52.648781, -1.307891, "Accessible PMR ♿ ", "");
    public static final Station DUNHAM_END = new Station("DUNHAM_END", 52.648778, -1.276361, "Accessible PMR ♿ ", "");
    public static final Station EASTBOURNE_WEST = new Station("EASTBOURNE_WEST", 52.645530, -1.287253, "Accessible PMR ♿ ", "");
    public static final Station FAIRFIELDS = new Station("FAIRFIELDS", 52.644776, 1.290910, "Accessible PMR ♿ ", "💳");
    public static final Station GRAND_CENTRAL = new Station("GRAND_CENTRAL", 52.640370, -1.289039, "Accessible PMR ♿ ", "💼ℹ️🛒💳🚻");
    public static final Station HIGH_STREET = new Station("HIGH_STREET", 52.644032, -1.305970, "Accessible PMR ♿ ", "🛒");
    public static final Station IVY_LANE = new Station("IVY_LANE", 52.643425, -1.281723, "Accessible PMR ♿ ", "");
    public static final Station JUBILEE_PLACE = new Station("JUBILEE_PLACE", 52.644255, -1.264680, "Accessible PMR ♿ ", "💳");
    public static final Station KINGS_WAY = new Station("KINGS_WAY", 52.639933, -1.279472, "Inaccessible PMR ❌♿ ", "ℹ️💳");
    public static final Station LAKESIDE = new Station("LAKESIDE", 52.644786, -1.300272, "Accessible PMR ♿ ", "");
    public static final Station MARKET_SQUARE = new Station("MARKET_SQUARE", 52.638819, -1.296610, "Inaccessible PMR ❌♿ ", "🛒💳");
    public static final Station NORTHGATE_SHOPPING_CENTRE = new Station("NORTHGATE_SHOPPING_CENTRE", 52.640474, -1.307375, "Accessible PMR ♿ ", "🛒💳");
    public static final Station OAKWOODS = new Station("OAKWOODS", 52.637930, -1.282890, "Accessible PMR ♿ ", "");
    public static final Station PARKSIDE_PLACE = new Station("PARKSIDE_PLACE", 52.637530, -1.273220, "Accessible PMR ♿ ", "");
    public static final Station QUEENSBRIDGE = new Station("QUEENSBRIDGE", 52.633615, -1.278053, "Accessible PMR ♿ ", "");
    public static final Station RIVERSIDE = new Station("RIVERSIDE", 52.632514, -1.273485, "Accessible PMR ♿ ", "");
    public static final Station SOUTHBANK_PLACE = new Station("SOUTHBANK_PLACE", 52.632040, -1.288380, "Inaccessible PMR ❌♿ ", "🛒");
    public static final Station TOWNHALL = new Station("TOWNHALL", 52.637897, -1.288380, "Accessible PMR ♿ ", "");
    public static final Station UNION_STREET = new Station("UNION_STREET", 52.632594, -1.295634, "Accessible PMR ♿ ", "💳");
    public static final Station VICTORIA_DOCKS = new Station("VICTORIA_DOCKS", 52.635870, -1.300010, "Accessible PMR ♿ ", "💼💳🚻");
    public static final Station WATERFRONT = new Station("WATERFRONT", 52.632567, -1.298006, "Accessible PMR ♿ ", "");
    public static final Station XENOBIOTICS_RESEARCH_FACILITY = new Station("XENOBIOTICS_RESEARCH_FACILITY", 52.635297, -1.307750, "Accessible PMR ♿ ", "");
    public static final Station YORK_ROAD = new Station("YORK_ROAD", 52.635089, -1.269646, "Accessible PMR ♿ ", "");
    public static final Station ZEPHYR_CLOSE = new Station("ZEPHYR_CLOSE", 52.651505, -1.280011, "Accessible PMR ♿ ", "");

    private final String nom;
    private final double latitude;
    private final double longitude;
    private final String acces;
    private final String service;

    private Station(String nom, double latitude, double longitude, String acces, String service) {
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.acces = acces;
        this.service = service;
    }

    public static Station[] getStations() {
        return new Station[] {ASHFORD_ROAD, BROOKSIDE_AVENUE, CASTLE_HILL, DUNHAM_END, EASTBOURNE_WEST, FAIRFIELDS, GRAND_CENTRAL, HIGH_STREET,
                IVY_LANE, JUBILEE_PLACE, KINGS_WAY, LAKESIDE, MARKET_SQUARE, NORTHGATE_SHOPPING_CENTRE, OAKWOODS, PARKSIDE_PLACE,
                QUEENSBRIDGE, RIVERSIDE, SOUTHBANK_PLACE, TOWNHALL, UNION_STREET, VICTORIA_DOCKS, WATERFRONT, XENOBIOTICS_RESEARCH_FACILITY,
                YORK_ROAD, ZEPHYR_CLOSE
        };
    }

    public String getNom() {
        return nom;
    }

    public static List<Station> getStationsByNameCroissant() {
        List<Station> stations = Arrays.asList(
                ASHFORD_ROAD, BROOKSIDE_AVENUE, CASTLE_HILL, DUNHAM_END, EASTBOURNE_WEST, FAIRFIELDS, GRAND_CENTRAL, HIGH_STREET,
                IVY_LANE, JUBILEE_PLACE, KINGS_WAY, LAKESIDE, MARKET_SQUARE, NORTHGATE_SHOPPING_CENTRE, OAKWOODS, PARKSIDE_PLACE,
                QUEENSBRIDGE, RIVERSIDE, SOUTHBANK_PLACE, TOWNHALL, UNION_STREET, VICTORIA_DOCKS, WATERFRONT, XENOBIOTICS_RESEARCH_FACILITY,
                YORK_ROAD, ZEPHYR_CLOSE
        );
        stations.sort(Comparator.comparing(Station::getNom));
        return stations;
    }

    public static List<Station> getStationsByNameDecroissant() {
        List<Station> stations = Arrays.asList(
                ASHFORD_ROAD, BROOKSIDE_AVENUE, CASTLE_HILL, DUNHAM_END, EASTBOURNE_WEST, FAIRFIELDS, GRAND_CENTRAL, HIGH_STREET,
                IVY_LANE, JUBILEE_PLACE, KINGS_WAY, LAKESIDE, MARKET_SQUARE, NORTHGATE_SHOPPING_CENTRE, OAKWOODS, PARKSIDE_PLACE,
                QUEENSBRIDGE, RIVERSIDE, SOUTHBANK_PLACE, TOWNHALL, UNION_STREET, VICTORIA_DOCKS, WATERFRONT, XENOBIOTICS_RESEARCH_FACILITY,
                YORK_ROAD, ZEPHYR_CLOSE
        );
        stations.sort(Comparator.comparing(Station::getNom).reversed());
        return stations;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAcces() {
        return acces;
    }

    public String getService() {
        return service;
    }

    @Override
    public String toString() {
        return nom + " :" + latitude + "° N, " + longitude + "° E - " + acces + service;
    }
}