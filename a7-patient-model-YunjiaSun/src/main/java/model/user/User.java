package model.user;

public class User {
    private String name;
    private Role role;

    public User(String name, Role role){
        this.name = name;
        this.role = role;
    }

    public Role getRole(){
        return role;
    }

    public String getName(){
        return name;
    }
}
