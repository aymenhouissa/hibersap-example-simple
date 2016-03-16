package org.hibersap.examples.flightlist;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightListDaoTest {

    private static final char SUCCESS = 'S';

    private final FlightListDao dao = new FlightListDao();

    /**
     * Because the result of the call is dependent on which data is there in the SAP system,
     * we simply check if SAP returned a success message and if there are any flights returned.
     * If an error is returned by SAP, there is possibly no current data.
     * Flight demo data can be created by running the program 'SAPBC_DATA_GENERATOR' in
     * transaction SE38 in SAP.
     * But anyway, when the code reaches the assert statements without throwing an exception,
     * we can be sure SAP was called which should be enough for the simple test.
     */
    @Test
    public void findsFlightsFromFrankfurtToBerlin() throws Exception {
        FlightListBapi flightList = dao.findFlights("DE", "Frankfurt", "DE", "Berlin", null, false, 10);

        assertEquals(SUCCESS, flightList.getReturnData().getType());
        assertTrue(flightList.getFlightList().size() > 0);
    }
}