import java.util.Optional;

class Assessment implements Keyable<String> {
    String assessmentName;
    String grade;

    Assessment(String assessmentName, String grade) {
        this.assessmentName = assessmentName;
        this.grade = grade;
    }

    public String getKey() {
        return assessmentName;
    }

    String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "{" + assessmentName + ": " + grade + "}";
    }
}
