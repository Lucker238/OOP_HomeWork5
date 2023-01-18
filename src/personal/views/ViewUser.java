package personal.views;

import personal.controllers.UserController;
import personal.model.Repository;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

import javax.swing.SortOrder;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            try{
                switch (com) {
                    case NONE:
                        System.out.println("Ничего не произошло!");
                        break;
                    case EXIT:
                        break;
                    case CREATE:
                        String firstName = prompt("Имя: ");
                        String lastName = prompt("Фамилия: ");
                        String phone = prompt("Номер телефона: ");
                        userController.saveUser(new User(firstName, lastName, phone));
                        break;
                    case READ:
                        String id = prompt("Идентификатор пользователя: ");
                        User user = userController.readUser(id);
                        System.out.println(user);
                        break;
                    case LIST:
                        List<User> lst = userController.readList();
                        lst.forEach(i -> System.out.println(i + "\n"));
                        break;
                    case UPDATE:
                        String updateID = prompt("Введите ID редактируемого пользователя: ");
                        userController.validateIDPresence(updateID);
                        userController.updateUser(updateID, newUser());
                        break;
                    case DELETE:
                        String deleteID = prompt("Введите ID для удаления: ");
                        userController.deleteUser(deleteID);
                        break;
                    case CSV:
                        List<User> csvs = userController.readList();
                        userController.saveAsCSV(csvs);
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("Ошибка " + e.getMessage());
            }
            
        }
    }

    private String prompt(String message) {
        Scanner input = new Scanner(System.in);
        System.out.print(message);
        return input.nextLine();
    }

    private User newUser() {
        String fName = prompt("Имя: ");
        String lName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        User user = new User(fName, lName, phone);
        return user;
    }
}
