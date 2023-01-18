package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    void editList();
    void saveAsCSV(List<User> users);
}
