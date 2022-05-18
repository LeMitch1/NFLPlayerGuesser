import java.util.*;

public class Game {

    private Player playerToGuess;
    private int count = 0;

    // quarterbacks
    private final Player JOSH_ALLEN = new Player("Josh Allen", "QB", "AFC",
            "East", "BUF Bills");
    private final Player TOM_BRADY = new Player("Tom Brady", "QB", "NFC",
            "South", "TB Buccaneers");
    private final Player JUSTIN_HERBERT = new Player("Justin Herbert", "QB", "AFC",
            "West", "LAC Chargers");
    private final Player PATRICK_MAHOMES = new Player("Patrick Mahomes", "QB", "AFC",
            "West", "KC Chiefs");
    private final Player LAMAR_JACKSON = new Player("Lamar Jackson", "QB", "AFC",
            "North", "BAL Ravens");
    private final Player KYLER_MURRAY = new Player("Kyler Murray", "QB", "NFC",
            "West", "ARI Cardinals");
    private final Player JOE_BURROW = new Player("Joe Burrow", "QB", "AFC",
            "North", "CIN Bengals");
    private final Player DAK_PRESCOTT = new Player("Dak Prescott", "QB", "NFC",
            "East", "DAL Cowboys");
    private final Player MATT_STAFFORD = new Player("Matt Stafford", "QB", "NFC",
            "West", "LAR Rams");
    private final Player AARON_RODGERS = new Player("Aaron Rodgers", "QB", "NFC",
            "North", "GB Packers");

    // wide receivers
    private final Player COOPER_KUPP = new Player("Cooper Kupp", "WR", "NFC",
            "West", "LAR Rams");
    private final Player JAMARR_CHASE = new Player("Ja'Marr Chase", "WR", "AFC",
            "North", "CIN Bengals");
    private final Player JUSTIN_JEFFERSON = new Player("Justin Jefferson", "WR", "NFC",
            "North", "MIN Vikings");
    private final Player DEEBO_SAMUEL = new Player("Deebo Samuel", "WR", "NFC",
            "West", "SF 49ers");
    private final Player DAVANTE_ADAMS = new Player("Davante Adams", "WR", "AFC",
            "West", "LV Raiders");
    private final Player STEFON_DIGGS = new Player("Stefon Diggs", "WR", "AFC",
            "East", "BUF Bills");
    private final Player AJ_BROWN = new Player("AJ Brown", "WR", "NFC",
            "East", "PHI Eagles");
    private final Player CEEDEE_LAMB = new Player("CeeDee Lamb", "WR", "NFC",
            "East", "DAL Cowboys");
    private final Player TYREEK_HILL = new Player("Tyreek Hill", "WR", "AFC",
            "East", "MIA Dolphins");
    private final Player DEANDRE_HOPKINS = new Player("DeAndre Hopkins", "WR", "NFC",
            "West", "ARI Cardinals");

    // running backs
    private final Player JONATHAN_TAYLOR = new Player("Jonathan Taylor", "RB", "AFC",
            "South", "IND Colts");
    private final Player DERRICK_HENRY = new Player("Derrick Henry", "RB", "AFC",
            "South", "TEN Titans");
    private final Player AUSTIN_EKELER = new Player("Austin Ekeler", "RB", "AFC",
            "West", "LAC Chargers");
    private final Player DALVIN_COOK = new Player("Dalvin Cook", "RB", "NFC",
            "North", "MIN Vikings");
    private final Player CHRISTIAN_MCCAFFREY = new Player("Christian McCaffrey", "RB", "NFC",
            "South", "CAR Panthers");
    private final Player JOE_MIXON = new Player("Joe Mixon", "RB", "AFC",
            "North", "CIN Bengals");
    private final Player NICK_CHUBB = new Player("Nick Chubb", "RB", "AFC",
            "North", "CLE Browns");
    private final Player ALVIN_KAMARA = new Player("Alvin Kamara", "RB", "NFC",
            "South", "NO Saints");
    private final Player LEONARD_FOURNETTE = new Player("Leonard Fournette", "RB", "NFC",
            "South", "TB Buccaneers");
    private final Player SAQUON_BARKLEY = new Player("Saquon Barkley", "RB", "NFC",
            "East", "NYG Giants");

    private final Player[] playerPool = {JOSH_ALLEN, TOM_BRADY, JUSTIN_HERBERT, PATRICK_MAHOMES, LAMAR_JACKSON,
            KYLER_MURRAY, JOE_BURROW, DAK_PRESCOTT, MATT_STAFFORD, AARON_RODGERS, COOPER_KUPP, JAMARR_CHASE,
            JUSTIN_JEFFERSON, DEEBO_SAMUEL, DAVANTE_ADAMS, STEFON_DIGGS, AJ_BROWN, CEEDEE_LAMB, TYREEK_HILL,
            DEANDRE_HOPKINS, JONATHAN_TAYLOR, DERRICK_HENRY, AUSTIN_EKELER, DALVIN_COOK, CHRISTIAN_MCCAFFREY,
            JOE_MIXON, NICK_CHUBB, ALVIN_KAMARA, LEONARD_FOURNETTE, SAQUON_BARKLEY};

    ArrayList<Player> remainingPlayers = new ArrayList<>(Arrays.asList(playerPool));

    public void selectRandomPlayer() {
        Random random = new Random();
        playerToGuess = playerPool[random.nextInt(playerPool.length)];
    }

    public void startPlaying() {
        selectRandomPlayer();
        System.out.println("\nThe program will select a random player from the following list of 30 players:");

        // formats list into clean columns
        String format = "%-25s";
        System.out.println();
        System.out.printf(format, "Quarterbacks:");
        System.out.printf(format, "Wide Receivers:");
        System.out.printf(format, "Running Backs:");
        System.out.println("\n");

        // prints players in player pool
        for (int i = 0; i < 10; i++) {
            System.out.printf(format, playerPool[i]);
            System.out.printf(format, playerPool[i + 10]);
            System.out.printf(format, playerPool[i + 20]);
            System.out.println();
        }
        System.out.println();
        System.out.println("Correctly guess the player in as few attempts as possible. Good luck!\n");
        System.out.println("Please enter your guess below:");

        checkUserGuess();
    }

    public void checkUserGuess() {
        Scanner scanner = new Scanner(System.in);
        String userGuess;
        Player userPickedPlayer;
        boolean correctPlayer = false;

        String[] positionArray = {"QB", "WR", "RB"};
        String[] conferenceArray = {"NFC", "AFC"};
        String[] teamArray = {"GB Packers", "MIN Vikings", "CAR Panthers", "TB Buccaneers", "NO Saints", "DAL Cowboys",
                "NYG Giants", "PHI Eagles", "ARI Cardinals", "LAR Rams", "SF 49ers", "BAL Ravens", "CLE Browns",
                "CIN Bengals", "IND Colts", "TEN Titans", "BUF Bills", "MIA Dolphins", "KC Chiefs", "LV Raiders",
                "LAC Chargers"};

        // game loop
        while (!correctPlayer) {
            userGuess = scanner.nextLine();

            userPickedPlayer = null;
            for (Player currentPlayer : playerPool)
                if (currentPlayer.getName().equalsIgnoreCase(userGuess)) {
                    userPickedPlayer = currentPlayer;
                    break;
                }

            if (userPickedPlayer == null) {
                System.out.println("Please select a player from the player pool.");
                continue;
            }

            // checks for correct player, else sets userGuess to player object attributes
            if (playerToGuess != userPickedPlayer) {
                /**
                 * commented-out code below will end the game if there is only one eligible player left,
                 * but I found that I was losing a majority of the time with it active
                 * feel free to try it yourself
                 */

//                removePlayers();
//                // lose game by only leaving one player left to guess
//                if (remainingPlayers.size() == 1) {
//                    System.out.println("Sorry was this too hard? It took you " + count + " guesses just to get it wrong.");
//                    System.out.println("The player was " + playerToGuess);
//                    break;
//                }

                // checks position
                if (!userPickedPlayer.getPosition().equals(playerToGuess.getPosition())) {
                    for (String pos : positionArray) {
                        if (userPickedPlayer.getPosition().equals(pos)) {
                            System.out.print("The player is not a " + pos);
                        }
                    }
                } else {
                    for (String pos : positionArray) {
                        if (userPickedPlayer.getPosition().equals(pos)) {
                            System.out.print("The player is a " + pos);
                        }
                    }
                }

                // checks conference (wrong)
                if (!userPickedPlayer.getConference().equals(playerToGuess.getConference())) {
                    for (String conf : conferenceArray) {
                        if (userPickedPlayer.getConference().equals(conf)) {
                            System.out.println(", is not in the " + conf);
                        }
                    }

                    // checks conference (correct)
                } else {
                    for (String conf : conferenceArray) {
                        if (userPickedPlayer.getConference().equals(conf)) {
                            System.out.print(", is in the " + conf);

                            // checks division
                            if (!userPickedPlayer.getDivision().equals(playerToGuess.getDivision())) {
                                System.out.println(", but not the " + userPickedPlayer.getConference() + " " + userPickedPlayer.getDivision());
                            } else {
                                System.out.print(" " + userPickedPlayer.getDivision());

                                // checks team
                                if (!userPickedPlayer.getTeam().equals(playerToGuess.getTeam())) {
                                    for (String team : teamArray) {
                                        if (userPickedPlayer.getTeam().equals(team)) {
                                            System.out.println(", but not on the " + team);
                                        }
                                    }
                                } else {
                                    System.out.println(", and is on the " + playerToGuess.getTeam());
                                }
                            }
                        }
                    }
                }
                System.out.println(removePlayers());

            } else {
                correctPlayer = true;
                System.out.println(countGuesses(count));
            }
        }
    }

    public ArrayList<Player> removePlayers() {

        // removes players that don't fit criteria and prints remaining players
        for (Player player : playerPool) {

            // removes position
            if (!userPickedPlayer.getPosition().equals(playerToGuess.getPosition())) {
                // guessed incorrectly, removes players with same incorrect guess
                if (userPickedPlayer.getPosition().equals(player.getPosition())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, removes other positions
                if (!userPickedPlayer.getPosition().equals(player.getPosition())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes conference
            if (!userPickedPlayer.getConference().equals(playerToGuess.getConference())) {
                // guessed incorrectly, removes players with same incorrect guess
                if (userPickedPlayer.getConference().equals(player.getConference())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, removes other conference
                if (!userPickedPlayer.getConference().equals(player.getConference())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes divisions only if correctly guessed conference
            // division guessed correctly, removes other divisions
            if (!userPickedPlayer.getDivision().equals(playerToGuess.getDivision())) {
                // guessed incorrectly, removes players with same incorrect guess
                if (userPickedPlayer.getDivision().equals(player.getDivision())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed incorrectly
                if (!userPickedPlayer.getDivision().equals(player.getDivision())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes team
            if (!userPickedPlayer.getTeam().equals(playerToGuess.getTeam())) {
                // guessed incorrectly, removes players with same incorrect guess
                if (userPickedPlayer.getTeam().equals(player.getTeam())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, remove other teams
                if (!userPickedPlayer.getTeam().equals(player.getTeam())) {
                    remainingPlayers.remove(player);
                }
            }
        }
        return remainingPlayers;
    }

    public String countGuesses(int count) {
        this.count = count;

        if (count < 2) {
            return "Fun to cheat is it?";
        } else if (count < 4) {
            return "Not bad. You got it in " + count + " attempts";
        } else {
            return "It took you " + count + " attempts. Going for a high score are ya?";
        }
    }
}

// NFC
//  North: GB Packers, MIN Vikings
//  South: CAR Panthers, TB Buccaneers, NO Saints
//  East: DAL Cowboys, NYG Giants, PHI Eagles
//  West: ARI Cardinals, LAR Rams, SF 49ers

// AFC
//  North: BAL Ravens, CLE Browns, CIN Bengals
//  South: IND Colts, TEN Titans
//  East: BUF Bills
//  West: KC Chiefs, LV Raiders, LAC Chargers
