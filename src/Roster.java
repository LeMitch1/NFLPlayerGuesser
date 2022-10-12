public class Roster {

    private final Player joshAllen = new Player("Josh Allen", "QB", "AFC", "East", "BUF Bills");
    private final Player tomBrady = new Player("Tom Brady", "QB",  "NFC", "South", "TB Buccaneers");
    private final Player mattStafford = new Player("Matt Stafford", "QB", "NFC", "West", "LA Rams");

    private final Player[] players = {joshAllen, tomBrady, mattStafford};

    public Player[] getPlayers() {
        return players;
    }
}
