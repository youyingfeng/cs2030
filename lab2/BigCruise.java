class BigCruise extends Cruise {
    private static final int METERS_PER_LOADER = 40;
    private static final int PASSENGERS_PER_MINUTE = 50;

    BigCruise(String identifier, int arrivalTime, int length, int passengerCount) {
        super(identifier, arrivalTime, (int) Math.ceil(length / (double) METERS_PER_LOADER),
                (int) Math.ceil(passengerCount / (double) PASSENGERS_PER_MINUTE));
    }
}
