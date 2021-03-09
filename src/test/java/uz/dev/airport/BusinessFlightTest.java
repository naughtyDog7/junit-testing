package uz.dev.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static uz.dev.airport.PassengerLevel.STANDARD;
import static uz.dev.airport.PassengerLevel.VIP;

public class BusinessFlightTest extends FlightTest<BusinessFlight> {

    private Flight businessFlight;

    @Override
    BusinessFlight getFlight() {
        return new BusinessFlight("2");
    }

    @BeforeEach
    void setUp() {
        businessFlight = getFlight();
    }

    @Test
    void failureAddingStandardPassenger() {
        Passenger mike = new Passenger("Mike", STANDARD);

        assertAll(
                () -> assertEquals("2", businessFlight.getCode()),
                () -> assertFalse(businessFlight.tryAddPassenger(mike)),
                () -> assertThat(businessFlight.getPassengers(), hasSize(0))
        );
    }

    @Test
    void successAddingAndFailureRemovingVipPassenger() {
        Passenger james = new Passenger("James", VIP);

        assertAll(
                () -> assertEquals("2", businessFlight.getCode()),
                () -> assertTrue(businessFlight.tryAddPassenger(james)),
                () -> assertThat(businessFlight.getPassengers(), contains(james)),
                () -> assertFalse(businessFlight.tryRemovePassenger(james)),
                () -> assertThat(businessFlight.getPassengers(), contains(james))
        );
    }
}
