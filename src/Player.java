
public class Player {

    private String name;
    private String position;
    private String conference;
    private String division;
    private String team;

    public Player(String name, String position, String conference, String division, String team) {
        this.name = name;
        this.position = position;
        this.conference = conference;
        this.division = division;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getConference() {
        return conference;
    }

    public String getDivision() {
        return division;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return getName();
    }
}
