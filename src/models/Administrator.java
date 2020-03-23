package models;

import java.util.UUID;

public class Administrator extends User{


    public Administrator(String email, String password, String firstname, String surname, UUID userId) {
        super(email, password, firstname, surname, userId);
    }

    public void registerNewUser(User user){
        User.addUser(user);
    }
}
