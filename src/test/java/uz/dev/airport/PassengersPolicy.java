package uz.dev.airport;

import io.cucumber.java8.En;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PassengersPolicy implements En {
    private Flight flight;
    private Passenger passenger;

    public PassengersPolicy() {
        Given("^there is (\\w+) flight$", this::setFlightType);
        When("^we have a (\\w+) passenger$", this::setPassenger);
        Then("^you can add him to flight$", this::assertCanAdd);
        Then("^you cannot add him to the flight$", this::assertCannotAdd);
        And("^you can remove him from the flight$", this::assertCanRemove);
        But("^you cannot remove him from the flight$", this::assertCannotRemove);
        And("^you cannot add him more than once$", this::assertCannotAddMoreThanOnce);
    }

    private void assertCanAdd() {
        assertAll(
                () -> assertTrue(flight.tryAddPassenger(passenger)),
                () -> assertThat(flight.getPassengers(), hasItem(passenger))
        );
    }

    private void assertCannotAdd() {
        assertFalse(flight.tryAddPassenger(passenger));
    }

    private void assertCanRemove() {
        assertAll(
                () -> assertTrue(flight.tryRemovePassenger(passenger)),
                () -> assertThat(flight.getPassengers(), not(hasItem(passenger)))
        );
    }

    private void assertCannotRemove() {
        assertFalse(flight.tryRemovePassenger(passenger));
    }

    private void setPassenger(String passengerLevel) {
        passenger = PassengerTestUtil.getTestPassenger("Mike", passengerLevel);
    }

    private void setFlightType(String flightType) {
        switch (flightType.toLowerCase()) {
            case "economy":
                flight = new EconomyFlight("1");
                break;
            case "business":
                flight = new BusinessFlight("2");
                break;
            case "premium":
                flight = new PremiumFlight("3");
                break;
            default:
                throw new IllegalArgumentException("Unsupported flight type: " + flightType);
        }
    }

    private void assertCannotAddMoreThanOnce() {
        clearPassengers();
        assertAll(
                () -> assertTrue(flight.tryAddPassenger(passenger)),
                () -> assertFalse(flight.tryAddPassenger(passenger)),
                () -> assertThat(flight.getPassengers(), contains(passenger))
        );
    }

    private void clearPassengers() {
        flight.removeAllPassengers();
    }

}
