class RecycledLoader extends Loader {
    RecycledLoader(int id) {
        super(id);
    }

    RecycledLoader(int id, Cruise cruise) {
        super(id, cruise);
    }

    @Override
    boolean canServe(Cruise cruise) {
        if (super.currentCruise == null) {
            return true;
        } else if (cruise.getArrivalTime() >= super.timeComplete + 60) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    RecycledLoader serve(Cruise cruise) {
        if (cruise == null) {
            return new RecycledLoader(id);
        } else if (canServe(cruise) == true) {
            return new RecycledLoader(id, cruise);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        if (super.currentCruise == null) {
            return "Loader " + super.id + " (recycled)";
        } else {
            return "Loader " + super.id + " (recycled) serving " + super.currentCruise;
        }
    }
}
