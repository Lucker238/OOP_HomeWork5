package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) throws Exception{
        validateUser(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public List<User> readList() {
        List<User> readL = repository.getAllUsers();
        return readL;
    }

    public void updateUser(String idNumber, User newData) throws Exception {
        validateIDPresence(idNumber);
        newData.setId(idNumber);
        validateUserID(newData);
        repository.updateUser(newData);
    }

    public void deleteUser(String idNumber) throws Exception {
        validateIDPresence(idNumber);
        User userData = readUser(idNumber);
        repository.deleteUser(userData);
        repository.editList();
    }


    private void validateUser(User user) throws Exception{
        if (user.getFirstName().contains(" "))
            throw new Exception("Wrong name");
        if (user.getLastName().contains(" "))
            throw new Exception("Wrong family name");
    }

    private void validateUserID(User user) throws Exception{
        if (user.getId().isEmpty())
            throw new Exception("ID not found");
        validateUser(user);
    }

    public void validateIDPresence(String inputID) throws Exception{
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if(user.getId().equals(inputID)){
                return;
            }
        }
        throw new Exception("ID not found");
    }

    public void saveAsCSV(List<User> users) {
        repository.saveAsCSV(users);
    }


}
