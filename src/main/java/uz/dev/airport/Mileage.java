package uz.dev.airport;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Mileage {
    private final Map<Passenger, Integer> passengerMileageMap;
    @Getter
    private final Map<Passenger, Integer> passengerPointsMap;

    public Mileage() {
        passengerMileageMap = new HashMap<>();
        passengerPointsMap = new HashMap<>();
    }

    public void addMileage(Passenger passenger, int miles) {
        passengerMileageMap.merge(passenger, miles, Integer::sum);
    }

    public void calculateGivenPoints() {
        for (Entry<Passenger, Integer> passengerEntry : passengerMileageMap.entrySet()) {
            int milesPerPoint = passengerEntry.getKey().getMilesPerPoint();
            int passengerPoints = passengerEntry.getValue() / milesPerPoint;
            passengerPointsMap.put(passengerEntry.getKey(), passengerPoints);
        }
    }

}
