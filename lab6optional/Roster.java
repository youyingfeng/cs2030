import java.util.Optional;

class Roster extends KeyableMap<String, String, Student> {
    Roster(String id) {
        super(id);
    }

    @Override
    public Roster put(Student student) {
        map.put(student.getKey(), student);
        return this;
    }

    public Roster putGrade(String studentID, String moduleCode,
                            String assessmentName, String grade) {
        Optional.ofNullable(map.get(studentID)).ifPresentOrElse(
            (student) -> {
                student.putGrade(moduleCode, assessmentName, grade);
            },
            () -> {
                map.put(studentID, new Student(studentID)
                    .putGrade(moduleCode, assessmentName, grade));
            }
        );

        return this;
    }

    public String getGrade(String studentID, String moduleCode, String assessmentName)
            throws NoSuchRecordException {

        Optional<String> monadicGrade = this.get(studentID)
                            .flatMap((student) -> student.get(moduleCode))
                                .flatMap((module) -> module.get(assessmentName))
                                    .map((assessment) -> assessment.getGrade());
        return monadicGrade.orElseThrow(() -> new NoSuchRecordException("No such record: "
                    + studentID + " " + moduleCode + " " + assessmentName)
        );

    }
}
