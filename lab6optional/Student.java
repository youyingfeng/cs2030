import java.util.Optional;

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
        Optional.ofNullable(map.get(moduleCode)).ifPresentOrElse(
            (module) -> {
                module.putGrade(assessmentName, grade);
            },
            () -> {
                map.put(moduleCode, new Module(moduleCode).putGrade(assessmentName, grade));
            }
        );

        return this;
    }
}
