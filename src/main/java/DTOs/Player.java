package DTOs;

public class Player {
    private int id;
    private String first_name;
    private String last_name;
    private String birthdate;
    private String position;
    private int team_id;


    public Player(int id, String first_name, String last_name, String birthdate, String position, int team_id ){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.position = position;
        this.team_id = team_id;
    }

    public Player(String first_name, String last_name, String birthdate, String position, int team_id ){
        this.id = 0;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.position = position;
        this.team_id = team_id;
    }

    public Player(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    @Override
    public String toString()
    {
        return "Player{" + "id=" + id + ", firstName=" + first_name + ", lastName=" +
                last_name + ", birthdate=" + birthdate + ", position=" + position + '}';
    }
}

