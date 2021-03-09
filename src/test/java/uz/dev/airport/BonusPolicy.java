package uz.dev.airport;

import io.cucumber.java8.En;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusPolicy implements En {
    Passenger passenger;
    Mileage mileage;

    public BonusPolicy() {
        Given("^we have a (\\w+) passenger with mileage$", this::setPassengerAndMileage);
        When("^passenger travels (\\d+) and (\\d+) and (\\d+) miles$",
                this::whenTravels);
        Then("^the bonus points should be (\\d+)$", this::assertPointsEquals);
    }

    private void whenTravels(int miles1, int miles2, int miles3) {
        List.of(miles1, miles2, miles3)
                .forEach(miles -> mileage.addMileage(passenger, miles));
    }

    private void setPassengerAndMileage(String passengerType) {
        passenger = PassengerTestUtil.getTestPassenger("Mike", passengerType);
        mileage = new Mileage();
    }

    private void assertPointsEquals(int pointsExpected) {
        mileage.calculateGivenPoints();
        int pointsActual = mileage.getPassengerPointsMap().get(passenger);
        assertEquals(pointsExpected, pointsActual);
    }
}
