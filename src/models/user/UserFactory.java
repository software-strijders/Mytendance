package models.user;

import enums.UserType;
import models.user.Student;
import models.user.Teacher;
import models.user.User;
import utils.Utils;

public class UserFactory {

    public static User create(String email, String password, String firstname, String surname, UserType type){
        switch (type){
            case STUDENT:
                return new Student(email, password, firstname, surname, Utils.idGenerator());
            case TEACHER:
                return new Teacher(email, password, firstname, surname, Utils.idGenerator());
        }

        return null;
    }
}
