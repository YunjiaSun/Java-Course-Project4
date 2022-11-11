package model.user;

import model.clinic.Clinic;

import java.util.ArrayList;

public class UserDirectory {
    private Clinic clinic;
    private ArrayList<User> userList;

    public UserDirectory(Clinic c) {
        clinic = c;
        userList = new ArrayList<User>();
    }

    public User newUser(String userName, Role role) {
        // If a user exist return null 
        if (checkUserByName(userName) != null)
            return null;
        // Create new user
        User newUser = new User(userName, role);
        userList.add(newUser);
        return newUser;
    }

    public Role checkUserByName(String name) {
        // If user exists return role
        for (User u : userList) {
            if (u.getName().equals(name))
                return u.getRole();
        }
        return null;
    }
}
