package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {
        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        writeDown(users);
        return id;
    }

    @Override
    public void updateUser(User user) {
        List<User> users = getAllUsers();
            User userToEdit = users.stream().filter(i -> i.getId().equals(user.getId())).findFirst().get();
            userToEdit.setFirstName(user.getFirstName());
            userToEdit.setLastName(user.getLastName());
            userToEdit.setPhone(user.getPhone());
            writeDown(users);
    }

    private void writeDown(List<User> users){
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void deleteUser(User user) {
        List<User> users = getAllUsers();
        User userToDelete = users.stream().filter(i -> i.getId().equals(user.getId())).findFirst().get();
        users.remove(userToDelete);
        writeDown(users);
    }

    @Override
    public void editList() {
        List<User> users = getAllUsers();
        int id = 1;
        for (User user : users) {
            user.setId(Integer.toString(id));
            id += 1;
        }
        writeDown(users);        
    }

    @Override
    public void saveAsCSV(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item: users) {
            lines.add(mapper.map(item, ";"));
        }
        fileOperation.saveCSV(lines);
    }
}
