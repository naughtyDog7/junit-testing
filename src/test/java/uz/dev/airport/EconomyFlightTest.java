package uz.dev.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static uz.dev.airport.PassengerLevel.STANDARD;
import static uz.dev.airport.PassengerLevel.VIP;

public class EconomyFlightTest extends FlightTest<EconomyFlight> {

    private Flight flight;
    private Passenger mike;
    private Passenger james;

    @Override
    EconomyFlight getFlight() {
        return new EconomyFlight("1");
    }

    @BeforeEach
    void setUp() {
        flight = getFlight();
        mike = new Passenger("Mike", STANDARD);
        james = new Passenger("James", VIP);
    }

    @Test
    void successAddingAndRemovingStandardPassenger() {
        assertAll(
                () -> assertEquals("1", flight.getCode()),
                () -> assertTrue(flight.tryAddPassenger(mike)),
                () -> assertThat(flight.getPassengers(), contains(mike)),
                () -> assertTrue(flight.tryRemovePassenger(mike)),
                () -> assertThat(flight.getPassengers(), hasSize(0))
        );
    }

    @Test
    void successAddingAndFailureRemovingVipPassenger() {
        assertAll(
                () -> assertEquals("1", flight.getCode()),
                () -> assertTrue(flight.tryAddPassenger(james)),
                () -> assertThat(flight.getPassengers(), contains(james)),
                () -> assertFalse(flight.tryRemovePassenger(james)),
                () -> assertThat(flight.getPassengers(), contains(james))
        );
    }

    @RepeatedTest(5)
    void cantAddOnePassengerToFlightMoreThanOneTime(RepetitionInfo repetitionInfo) {
        for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
            flight.tryAddPassenger(mike);
        }
        assertAll(
                () -> assertThat(flight.getPassengers(), hasSize(1)),
                () -> assertThat(flight.getPassengers(), contains(mike))
        );
    }
}
