package persistence.dao.sql;

import business.entities.User;
import persistence.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SQLUserDAO implements UserDAO {

    @Override
    public void addUser(String username, String email, String password, int logged_in) {
        String query = "INSERT INTO player(username, email, password, logged_in) VALUES ('" +
                username + "', '" +
                email + "', '" +
                password + "', '" +
                logged_in+
                "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public LinkedList<User> getAllUsers() {
        LinkedList<User> users = new LinkedList<>();
        String query = "SELECT username, email, password, logged_in FROM player;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String username = result.getString("username");
                String email = result.getString("email");
                String password = result.getString("password");
                int logged_in = result.getInt("logged_in");

                users.add(new User(username, email, password, logged_in));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(String username) {
        String query = "DELETE FROM player WHERE player.username ='"+ username +"';";
        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public boolean loginVerification(String username, String email, String password) {
        String query = "SELECT * FROM player WHERE (player.username ='"+ username +"' OR player.email ='"+ email +"') AND player.password ='"+ password +"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void change_status(String username , int status){
        String query = "UPDATE player SET logged_in = "+ status +" WHERE username = '"+ username +"';";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public String logged_user(){
        String query = "SELECT username FROM player WHERE logged_in = 1;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        String username = new String();
        try {
            while (result.next()) {
                username = result.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
}
