import java.util.Scanner;

class Main6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ships = Integer.parseInt(input.nextLine());
        Loader[] loaderArray = new Loader[ships * 9];
        //this stores the list of loaders available

        int numLoaders = 0;
        //counts loaders

        for (int i = 0; i < ships; i++) {
            String nextLine = input.nextLine();
            String[] parts = nextLine.split(" ");
            int len = parts.length;
            String shipID;
            int time;
            int shipLength;
            int passengers;
            boolean assigned = false;
            if (len == 2) {
                shipID = parts[0];
                time = Integer.parseInt(parts[1]);

                for (int j = 0; j < numLoaders; j++) {

                    if (loaderArray[j].canServe(new SmallCruise(shipID, time))) {

                        loaderArray[j] = loaderArray[j].serve(new SmallCruise(shipID, time));

                        System.out.println(loaderArray[j]);
                        assigned = true;

                        break;
                    } else {

                        continue;

                    }

                }

                if (assigned == false) {
                    if ((numLoaders + 1) % 3 == 0) {
                        loaderArray[numLoaders] = new RecycledLoader(numLoaders + 1).serve(
                                                    new SmallCruise(shipID, time));
                        System.out.println(loaderArray[numLoaders]);
                        numLoaders++;
                    } else {
                        loaderArray[numLoaders] = new Loader(numLoaders + 1).serve(
                                                    new SmallCruise(shipID, time));
                        System.out.println(loaderArray[numLoaders]);
                        numLoaders++;
                    }
                }


            } else {
                shipID = parts[0];
                time = Integer.parseInt(parts[1]);
                shipLength = Integer.parseInt(parts[2]);
                passengers = Integer.parseInt(parts[3]);
                int loaderCount = (int) Math.ceil(shipLength / 40.0);
                if (loaderCount > 9) {
                    loaderCount = 9;
                }
                for (int k = 0; k < loaderCount; k++) {
                    assigned = false;
                    for (int j = 0; j < numLoaders; j++) {
                        if (loaderArray[j].canServe(new BigCruise(shipID, time,
                                                    shipLength, passengers))) {
                            loaderArray[j] = loaderArray[j].serve(new BigCruise(shipID,
                                                        time, shipLength, passengers));
                            System.out.println(loaderArray[j]);
                            assigned = true;
                            break;
                        } else {
                            continue;
                        }
                    }

                    if (assigned == false) {
                        if ((numLoaders + 1) % 3 == 0) {
                            loaderArray[numLoaders] = new RecycledLoader(numLoaders + 1).serve(
                                                        new BigCruise(shipID, time,
                                                        shipLength, passengers));
                            System.out.println(loaderArray[numLoaders]);
                            numLoaders++;
                        } else {
                            loaderArray[numLoaders] = new Loader(numLoaders + 1).serve(
                                                        new BigCruise(shipID, time,
                                                        shipLength, passengers));
                            System.out.println(loaderArray[numLoaders]);
                            numLoaders++;
                        }
                    }
                }
            }
        }
    }
}
