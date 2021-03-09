package uz.dev.airport;

public class PremiumFlight extends Flight {
    public PremiumFlight(String code) {
        super(code);
    }

    @Override
    public boolean tryAddPassenger(Passenger passenger) {
        if(canAddPassenger(passenger)) {
            return addPassenger(passenger);
        }
        return false;
    }

    private boolean canAddPassenger(Passenger passenger) {
        return passenger.isVip();
    }

    @Override
    public boolean tryRemovePassenger(Passenger passenger) {
        return removePassenger(passenger);
    }
}
