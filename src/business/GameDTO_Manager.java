package business;

import business.entities.GameDTO;
import persistence.dao.GameDTODAO;
import persistence.dao.sql.SQLGameDAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class GameDTO_Manager {
    private final GameDTODAO gamesdao;

    public GameDTO_Manager(){gamesdao = new SQLGameDAO();}

    public void addGame(String id, String username, String path, String date, String state, int num_attacks, String winner){
        gamesdao.addGame(id,username,path, date, state,num_attacks, winner);
    }

    public void updateGame(String id, String username, String path, String date, String state, int num_attacks, String winner){
        gamesdao.updateGame(id,username,path, date, state,num_attacks, winner);
    }

    public void change_attacks(String id, int num_attacks){
        gamesdao.change_attacks(id, num_attacks);
    }

    public void change_winner(String id, String winner){
        gamesdao.change_winner(id,winner);
    }

    public void change_state(String id, String state){
        gamesdao.change_state(id, state);
    }

    public int[] stats(String username){
        int[] stats = new int[3];

        stats[0] = gamesdao.stats_games(username);
        stats[1] = gamesdao.stats_wins(username);
        stats[2] = gamesdao.stats_attacks(username);

        return stats;
    }

    public boolean uniqueGameName(String username, String gameName){
        return gamesdao.uniqueGameName(username, gameName);
    }

    public LinkedList<GameDTO> getAllGames(String username){return gamesdao.getAllGames(username);}

    public String[] getAllPlayableGamesNames(String username){
        return gamesdao.getAllPlayableGamesNames(username).toArray(new String[5]);
    }

    public void updateGameState(String gameName, String state){
        gamesdao.change_state(gameName, state);
    }

    public String getDate(String gameName) {
        return gamesdao.getDateOfGame(gameName);
    }

    public void change_date(String id, String date){gamesdao.change_date(id, date);}

    public String currentDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        String formattedDateTime = currentDateTime.format(formatter);

        String[] parts = formattedDateTime.split("T",2);

        return parts[0];
    }

    public void deleteGamesOfPlayer(String to_delete) {
        gamesdao.deleteGamesOfPlayer(to_delete);
    }
}
