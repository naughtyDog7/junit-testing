package uz.dev.airport;

public enum PassengerLevel {
    STANDARD(20),
    VIP(10);

    private final int milesPerPoint;

    PassengerLevel(int milesPerPoint) {
        this.milesPerPoint = milesPerPoint;
    }

    public int getMilesPerPointBonus() {
        return milesPerPoint;
    }
}
