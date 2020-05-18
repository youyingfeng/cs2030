import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int counter = Integer.parseInt(inputScanner.nextLine());

        Roster roster = new Roster("roster");

        while (counter > 0) {
            String line = inputScanner.nextLine();
            String[] splitString = line.split("\\s+");

            String studentName = splitString[0];
            String moduleName = splitString[1];
            String assessmentName = splitString[2];
            String grade = splitString[3];

            roster.putGrade(studentName, moduleName, assessmentName, grade);

            counter--;
        }

        while (inputScanner.hasNext() == true) {
            String line = inputScanner.nextLine();
            String[] splitString = line.split("\\s+");

            String studentName = splitString[0];
            String moduleName = splitString[1];
            String assessmentName = splitString[2];

            try {
                System.out.println(roster.getGrade(studentName, moduleName, assessmentName));
            } catch (Exception e) {
                System.out.println("NoSuchRecordException: " + e.getMessage());
            }
        }
    }
}
