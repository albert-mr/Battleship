package persistence.dao.sql;

import business.entities.GameDTO;
import persistence.dao.GameDTODAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SQLGameDAO implements GameDTODAO {

    @Override
    public void addGame(String id, String username, String path, String date, String state, int num_attacks, String winner){
        String query = "INSERT INTO game(id, username, path, date, state, num_attacks, winner) VALUES ('" +
                id + "', '" +
                username + "', '" +
                path + "', '" +
                date + "', '" +
                state + "', '" +
                num_attacks+ "', '" +
                winner+
                "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void updateGame(String id, String username, String path, String date, String state, int num_attacks, String winner){
        String query = "UPDATE game SET username = '" +
                username + "', path = '" +
                path + "', date = '" +
                date + "', state = '" +
                state + "', num_attacks = '" +
                num_attacks + "', winner = '" +
                winner +
                "' WHERE id = '" +
                id + "';";
        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public void deleteGamesOfPlayer(String to_delete){
        String query = "DELETE FROM game WHERE username = '" + to_delete + "';";
        SQLConnector.getInstance().insertQuery(query);
    }

    @Override
    public LinkedList<GameDTO> getAllGames(String username) {
        LinkedList<GameDTO> games = new LinkedList<>();
        String query = "SELECT id, username, path, date, state, num_attacks, winner FROM game WHERE state = 'playing' AND username = '"+username+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {

                String id = result.getString("id");
                String username2 = result.getString("username");
                String path = result.getString("path");
                String date =result.getString("date");
                String state = result.getString("state");
                int num_attacks = result.getInt("num_attacks");
                String winner = result.getString("winner");
                games.add(new GameDTO(id, username2, path, date, state, num_attacks, winner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public boolean uniqueGameName(String username, String gameName){
        String query = "SELECT * FROM game WHERE username = '"+username+"' AND id = '"+gameName+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            if(result.next())
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public LinkedList<String> getAllPlayableGamesNames(String username) {
        LinkedList<String> games = new LinkedList<>();
        String query = "SELECT id FROM game WHERE state = 'playing' AND username = '"+username+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            while (result.next()) {
                String gameName = result.getString("id");
                games.add(gameName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public String getDateOfGame(String gameName) {
        String query = "SELECT date FROM game WHERE id = '"+gameName+"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            if(result.next())
                return result.getString("date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void change_attacks(String id, int num_attacks){
        String query = "UPDATE game SET num_attacks = "+ num_attacks +" WHERE id = '"+ id +"';";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void change_winner(String id, String winner){
        String query = "UPDATE game SET winner = '"+ winner +"' WHERE id = '"+ id +"';";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public void change_state(String id, String state){
        String query = "UPDATE game SET state = '"+ state +"' WHERE id = '"+ id +"';";
        SQLConnector.getInstance().updateQuery(query);
    }

    public void change_date(String id, String date){
        String query = "UPDATE game SET date = '"+ date +"' WHERE id = '"+ id +"';";
        SQLConnector.getInstance().updateQuery(query);
    }

    @Override
    public int stats_games(String username){
        String query = "SELECT COUNT(username) AS num_games FROM game WHERE username = '"+ username +"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        int games = 0;
        try {
            while (result.next()) {
                games = result.getInt("num_games");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    @Override
    public int stats_attacks(String username){
        String query = "SELECT SUM(num_attacks) AS num_attacks FROM game WHERE username = '"+ username +"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        int attacks = 0;
        try {
            while (result.next()) {
                attacks = result.getInt("num_attacks");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attacks;
    }

    @Override
    public int stats_wins(String username){
        String query = "SELECT COUNT(winner) AS num_wins FROM game WHERE winner = '"+ username +"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        int wins = 0;
        try {
            while (result.next()) {
                wins = result.getInt("num_wins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wins;
    }

}
