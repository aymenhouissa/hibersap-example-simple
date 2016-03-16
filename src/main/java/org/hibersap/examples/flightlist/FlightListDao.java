package org.hibersap.examples.flightlist;

import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;

/**
 * Data access logic to access flights in a SAP ABAP system.
 */
public class FlightListDao {

    /**
     * Find flights in SAP.
     *
     * @param fromCountryKey e.g. "DE" for Germany
     * @param fromCity       e.g. "Frankfurt"
     * @param toCountryKey   e.g. "US" for USA
     * @param toCity         e.g. "New York"
     * @param airlineCarrier e.g. "LH" for Lufthansa
     * @param afternoon      if false, find flights until noon, if true find flights after noon
     * @param maxResults     maximum number of returned flights
     */
    public FlightListBapi findFlights(String fromCountryKey, String fromCity,
                                      String toCountryKey, String toCity,
                                      String airlineCarrier, boolean afternoon, int maxResults) {
        Session session = createSessionManager().openSession();
        try {
            FlightListBapi flightList = new FlightListBapi(
                    fromCountryKey, fromCity,
                    toCountryKey, toCity,
                    airlineCarrier, afternoon, maxResults);
            session.execute(flightList);
            return flightList;
        } finally {
            session.close();
        }
    }

    private SessionManager createSessionManager() {
        AnnotationConfiguration configuration = new AnnotationConfiguration("A12");
        return configuration.buildSessionManager();
    }
}
