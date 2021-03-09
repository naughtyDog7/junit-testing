package uz.dev.airport;

import lombok.AllArgsConstructor;
import lombok.Data;

import static uz.dev.airport.PassengerLevel.VIP;

@Data
@AllArgsConstructor
public class Passenger {

    private final String name;
    private PassengerLevel level;

    public boolean isVip() {
        return level == VIP;
    }

    public int getMilesPerPoint() {
        return level.getMilesPerPointBonus();
    }
}
