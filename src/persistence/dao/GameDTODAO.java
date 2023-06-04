package persistence.dao;

import business.entities.GameDTO;

import java.util.LinkedList;

public interface GameDTODAO {
    void addGame(String id, String username, String path, String date, String state, int num_attacks, String winner);
    void change_attacks(String id, int num_attacks);
    void change_winner(String id, String winner);
    void change_state(String id, String status);
    void change_date(String id, String date);
    int stats_games(String username);
    int stats_attacks(String username);
    int stats_wins(String username);
    boolean uniqueGameName(String username, String gameName);
    LinkedList<GameDTO> getAllGames(String username);
    LinkedList<String> getAllPlayableGamesNames(String username);
    String getDateOfGame(String gameName);
    void updateGame(String id, String username, String path, String date, String state, int num_attacks, String winner);
    void deleteGamesOfPlayer(String to_delete);
}
