class Module extends KeyableMap<String, String, Assessment> {
    Module(String moduleCode) {
        super(moduleCode);
    }

    @Override
    public Module put(Assessment assessment) {
        map.put(assessment.getKey(), assessment);
        return this;
    }

    public Module putGrade(String assessmentName, String grade) {
        map.put(assessmentName, new Assessment(assessmentName, grade));
        return this;
    }
}
