class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int loaders;
    private final int serviceTime;

    Cruise(String identifier, int arrivalTime, int loaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.loaders = loaders;
        this.serviceTime = serviceTime;
    }

    int getServiceCompletionTime() {
        int arrival = getArrivalTime();
        return arrival + serviceTime;
    }

    int getArrivalTime() {
        int hours = arrivalTime / 100;
        int minutes = arrivalTime % 100;
        return (hours * 60) + minutes;
    }

    int getNumOfLoadersRequired() {
        return loaders;
    }

    public String toString() {
        return identifier + "@" + String.format("%04d", arrivalTime);
    }
}
