package persistence;

import business.entities.Game;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadGameDAO {
    private static Game game;

    public LoadGameDAO(Game game) {
        LoadGameDAO.game = game;
    }

    public void saveGame(Game gameToAdd, String gameName) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gameToAdd.setGameName(gameName);
            //gameToAdd.setTimer(timer);
            Writer writer = new FileWriter("files/"+gameName+".json");
            gson.toJson(gameToAdd, writer);
            writer.flush();
            writer.close();
            //System.out.println("Game saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public String getTimer() {
        return String.valueOf(game.getTimer());
    }*/

    public String getDateOfGame(String gameName) throws IOException {
        String path = getGamePath(gameName);
        return Files.readAttributes(Path.of(path), "lastModifiedTime").toString();
    }

    public String getGamePath(String gameName) {
        return "files/"+gameName+".json";
    }

    public Game loadGame(String gameName) {
        Gson gson = new Gson();
        try {
            BufferedReader gamesFile = new BufferedReader(new FileReader(getGamePath(gameName)));
            game = gson.fromJson(gamesFile, Game.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return game;
    }

    public void deleteGame(String gameName) {
        File file = new File("files/"+gameName+".json");
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }
    }
    
}
