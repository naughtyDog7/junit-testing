package uz.dev.airport;

public class BusinessFlight extends Flight {

    public BusinessFlight(String code) {
        super(code);
    }

    @Override
    public boolean tryAddPassenger(Passenger passenger) {
        if (canAddPassenger(passenger)) {
            return addPassenger(passenger);
        }
        return false;
    }

    private boolean canAddPassenger(Passenger passenger) {
        return passenger.isVip();
    }

    @Override
    public boolean tryRemovePassenger(Passenger passenger) {
        return false;
    }
}
