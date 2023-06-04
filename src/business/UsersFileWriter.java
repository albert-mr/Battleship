package business;

import business.entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

//TODO remove this class as we already have database
public class UsersFileWriter {
    public static void addUser(User user) {
        try {
            List<User> users = UsersFileReader.getUsers();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = new FileWriter("files/users.json");
            users.add(user);
            gson.toJson(users, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reset(List<User> users) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = new FileWriter("files/users.json");
            gson.toJson(users, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}