package enums;

import models.user.Administrator;
import models.user.Student;
import models.user.Teacher;

public enum UserType {

    TEACHER("Docent", Teacher.class),
    STUDENT("Student", Student.class),
    ADMIN("Administrator", Administrator.class);

    private final String typeName;
    private final Class typeClass;

    UserType(String typeName, Class typeClass) {
        this.typeName = typeName;
        this.typeClass = typeClass;
    }

    public Class typeClass() {
        return this.typeClass;
    }

    @Override
    public String toString() {
        return this.typeName;
    }
}
