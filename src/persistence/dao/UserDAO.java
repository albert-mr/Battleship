package persistence.dao;

import business.entities.User;

import java.util.LinkedList;

/**
 * Interface that abstracts the persistence of students from the rest of the code.
 *
 * In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 */
public interface UserDAO {

    void addUser(String username, String email, String password, int logged_in);

    LinkedList<User> getAllUsers();

    void deleteUser(String username);

    boolean loginVerification(String username, String email, String password);

    void change_status(String username, int status);

    String logged_user();
}
