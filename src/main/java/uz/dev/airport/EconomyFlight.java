package uz.dev.airport;

public class EconomyFlight extends Flight {

    public EconomyFlight(String code) {
        super(code);
    }

    @Override
    public boolean tryAddPassenger(Passenger passenger) {
        return addPassenger(passenger);
    }

    @Override
    public boolean tryRemovePassenger(Passenger passenger) {
        if (canRemovePassenger(passenger)) {
            return removePassenger(passenger);
        }
        return false;
    }

    private boolean canRemovePassenger(Passenger passenger) {
        return isNotVip(passenger);
    }

    private boolean isNotVip(Passenger passenger) {
        return !passenger.isVip();
    }
}
