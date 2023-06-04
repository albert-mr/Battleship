package business;

import business.entities.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

//TODO remove this class as we already have database
public class UsersFileReader {
    public static User[] usersArray;
    public static List<User> users = new ArrayList<User>();
    public static List<User> getUsers() {
        int i = 0;
        Gson gson = new Gson();
        try {
            BufferedReader usersFile = new BufferedReader(new FileReader("files/users.json"));
            usersArray = gson.fromJson(usersFile, User[].class);
            do {
                users.add(usersArray[i]);
                i++;
            }while(i < usersArray.length);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
