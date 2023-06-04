package business;

import business.entities.User;
import persistence.dao.UserDAO;
import persistence.dao.sql.SQLUserDAO;

import java.util.LinkedList;


public class UserManager {
    private final UserDAO userdao;

    public UserManager() {
        userdao = new SQLUserDAO();
    }

    /*---------------------------------------------------------------------------------------*/

    public void addUser(String username, String email, String password, int logged_in){
        userdao.addUser(username, email, password, logged_in);
    }

    public LinkedList<User> getAllUsers(){
        return userdao.getAllUsers();
    }

    public void deleteUser(String username){
        userdao.deleteUser(username);
    }

    public boolean loginVerification(String username, String email, String password){return userdao.loginVerification(username, email, password);}

    public boolean signUpVerification(String username, String email, String password) {
        LinkedList<User> users = getAllUsers();
        for (User u : users) {
            if (username.equals(u.getUsername()) || email.equals(u.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyPassword(String password, String repeatedPassword) {
        return password.equals(repeatedPassword) && password.length() >= 8;
        //return password.equals(repeatedPassword) && password.length() >= 8 && user.getPassword().matches("^(?=.[0-9])(?=.[a-z])(?=.*[A-Z])(?=\S+$).{8,}$");
    }

    public void change_status(String username, int status){userdao.change_status(username, status);}

    public String logged_user(){return userdao.logged_user();}
}