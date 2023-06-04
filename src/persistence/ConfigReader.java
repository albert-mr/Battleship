package persistence;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;


public class ConfigReader {

    private Config config;

    public Config getConfig() {
        Gson gson = new Gson();
        try {
            BufferedReader configFile = new BufferedReader(new FileReader("files/config.json"));
            return gson.fromJson(configFile, Config.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return config;
    }
}