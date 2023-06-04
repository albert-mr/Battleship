package business.entities;

public class GameDTO {
    private String id;
    private String username;
    private String path;
    private String date;
    private String state;
    private int num_attacks;
    private String winner;

    public GameDTO(String id, String username, String path, String date, String state, int num_attacks, String winner){
        this.id = id;
        this.username = username;
        this.path = path;
        this.state = state;
        this.date = date;
        this.num_attacks = num_attacks;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNum_attacks() {
        return num_attacks;
    }

    public void setNum_attacks(int num_attacks) {
        this.num_attacks = num_attacks;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}
}
