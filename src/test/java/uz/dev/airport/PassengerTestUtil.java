package uz.dev.airport;

public class PassengerTestUtil {

    public static Passenger getTestPassenger(String name, String passengerLevelStr) {
        PassengerLevel lvl = PassengerLevel.valueOf(passengerLevelStr.toUpperCase());
        return getTestPassenger(name, lvl);
    }

    public static Passenger getTestPassenger(String name, PassengerLevel passengerLevel) {
        return new Passenger(name, passengerLevel);
    }
}
