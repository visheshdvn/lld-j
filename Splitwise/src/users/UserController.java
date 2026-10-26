package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserController {
    private static volatile UserController instance;

    public static UserController getController() {
        if(instance == null) {
            synchronized (UserController.class) {
                if(instance == null) {
                    instance = new UserController();
                }
            }
        }
        return instance;
    }

    private List<User> users;

    private UserController() {
        this.users = new ArrayList<>();
    }

    /*
    functional methods
     */
    public Optional<User> getUserById(String userId) {
        return users.stream()
                .filter(user -> user.getId().equalsIgnoreCase(userId))
                .findFirst();
    }

    public User createUser(String userId, String username) {
        User user = new User(userId, username);
        this.users.add(user);
        return user;
    }

    /*
     getters & setters
    */
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
