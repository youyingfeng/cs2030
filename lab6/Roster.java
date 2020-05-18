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
        if (map.get(studentID) == null) {
            map.put(studentID, new Student(studentID).putGrade(moduleCode, assessmentName, grade));
            return this;
        } else {
            map.get(studentID).putGrade(moduleCode, assessmentName, grade);
            return this;
        }
    }

    public String getGrade(String studentID, String moduleCode, String assessmentName)
            throws NoSuchRecordException {
        try {
            return this.get(studentID).get(moduleCode).get(assessmentName).getGrade();
        } catch (Exception e) {
            throw new NoSuchRecordException("No such record: " + studentID + " "
                                + moduleCode + " " + assessmentName);
        }
    }
}
