package uz.dev.airport;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Flight {
    @Getter
    private final String code;
    private final Set<Passenger> passengers;

    public Flight(String code) {
        this.code = code;
        this.passengers = new HashSet<>();
    }

    public abstract boolean tryAddPassenger(Passenger passenger);

    protected boolean addPassenger(Passenger passenger) {
        return passengers.add(passenger);
    }

    public abstract boolean tryRemovePassenger(Passenger passenger);

    protected boolean removePassenger(Passenger passenger) {
        return passengers.remove(passenger);
    }

    protected void removeAllPassengers() {
        passengers.clear();
    }

    public Set<Passenger> getPassengers() {
        return Collections.unmodifiableSet(passengers);
    }
}
