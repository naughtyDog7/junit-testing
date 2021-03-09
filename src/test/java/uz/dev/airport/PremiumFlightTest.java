package uz.dev.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static uz.dev.airport.PassengerLevel.STANDARD;
import static uz.dev.airport.PassengerLevel.VIP;

public class PremiumFlightTest extends FlightTest<PremiumFlight> {

    private Flight premiumFlight;

    @Override
    PremiumFlight getFlight() {
        return new PremiumFlight("3");
    }

    @BeforeEach
    void setUp() {
        premiumFlight = getFlight();
    }

    @Test
    void failureAddingStandardPassenger() {
        Passenger mike = new Passenger("Mike", STANDARD);

        assertAll(
                () -> assertEquals("3", premiumFlight.getCode()),
                () -> assertFalse(premiumFlight.tryAddPassenger(mike)),
                () -> assertThat(premiumFlight.getPassengers(), hasSize(0))
        );
    }

    @Test
    void successAddingAndFailureRemovingVipPassenger() {
        Passenger james = new Passenger("James", VIP);

        assertAll(
                () -> assertEquals("3", premiumFlight.getCode()),
                () -> assertTrue(premiumFlight.tryAddPassenger(james)),
                () -> assertThat(premiumFlight.getPassengers(), contains(james)),
                () -> assertTrue(premiumFlight.tryRemovePassenger(james)),
                () -> assertThat(premiumFlight.getPassengers(), hasSize(0))
        );
    }
}
