class Student extends KeyableMap<String, String, Module> {
    Student(String id) {
        super(id);
    }

    @Override
    public Student put(Module module) {
        map.put(module.getKey(), module);
        return this;
    }

    public Student putGrade(String moduleCode, String assessmentName, String grade) {
        if (map.get(moduleCode) == null) {
            map.put(moduleCode, new Module(moduleCode).putGrade(assessmentName, grade));
            return this;
        } else {
            map.get(moduleCode).putGrade(assessmentName, grade);
            return this;
        }
    }
}
