class Loader {
    protected final int id;
    protected final int timeComplete;
    protected final Cruise currentCruise;

    Loader(int id) {
        this.id = id;
        this.timeComplete = 0;
        this.currentCruise = null;
    }

    Loader(int id, Cruise cruise) {
        this.id = id;
        this.timeComplete = cruise.getServiceCompletionTime();
        this.currentCruise = cruise;
    }

    boolean canServe(Cruise cruise) {
        if (currentCruise == null) {
            return true;
        } else if (cruise.getArrivalTime() >= timeComplete) {
            return true;
        } else {
            return false;
        }
    }

    Loader serve(Cruise cruise) {
        if (cruise == null) {
            return new Loader(id);
        } else if (canServe(cruise) == true) {
            return new Loader(id, cruise);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (currentCruise == null) {
            return "Loader " + id;
        } else {
            return "Loader " + id + " serving " + currentCruise;
        }
    }
}
