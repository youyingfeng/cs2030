class SmallCruise extends Cruise {
    private static final int SMALL_CRUISE_LOADERS_REQUIRED = 1;
    private static final int SMALL_CRUISE_TIME_REQUIRED = 30;

    SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, SMALL_CRUISE_LOADERS_REQUIRED, SMALL_CRUISE_TIME_REQUIRED);
    }
}
