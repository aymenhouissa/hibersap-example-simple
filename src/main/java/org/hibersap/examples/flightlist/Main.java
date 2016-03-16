package org.hibersap.examples.flightlist;

import java.io.IOException;
import java.util.List;
import org.hibersap.bapi.BapiRet2;

public class Main {

    public static void main(String[] args) throws IOException {
        FlightListDao dao = new FlightListDao();
        FlightListBapi flightList = dao.findFlights("DE", "Frankfurt", "DE", "Berlin", null, false, 10);
        showResult(flightList);
    }

    private static void showResult(FlightListBapi flightList) {
        System.out.println("AirlineId: " + flightList.getFromCountryKey());
        System.out.println("FromCity: " + flightList.getFromCity());
        System.out.println("ToCountryKey: " + flightList.getToCountryKey());
        System.out.println("ToCity: " + flightList.getToCity());
        System.out.println("AirlineCarrier: " + flightList
                .getAirlineCarrier());
        System.out.println("Afternoon: " + flightList.isAfternoon());
        System.out.println("MaxRead: " + flightList.getMaxRead());

        System.out.println("\nFlightData");
        List<Flight> flights = flightList.getFlightList();
        for (Flight flight : flights) {
            System.out.print("\t" + flight.getAirportFrom());
            System.out.print("\t" + flight.getAirportTo());
            System.out.print("\t" + flight.getCarrierId());
            System.out.print("\t" + flight.getConnectionId());
            System.out.print("\t" + flight.getSeatsMax());
            System.out.print("\t" + flight.getSeatsOccupied());
            System.out.println("\t" + flight.getDepartureTime());
        }

        System.out.println("\nReturn");
        BapiRet2 returnStruct = flightList.getReturnData();
        System.out.println("\tMessage: " + returnStruct.getMessage());
        System.out.println("\tNumber: " + returnStruct.getNumber());
        System.out.println("\tType: " + returnStruct.getType());
        System.out.println("\tId: " + returnStruct.getId());
    }
}
