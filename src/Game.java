import java.util.*;

public class Game {

    private Roster roster = new Roster();
    private Player[] players = new Player[3];

    private Player playerToGuess;
    private String userGuessPosition = "";
    private String userGuessConference = "";
    private String userGuessDivision = "";
    private String userGuessTeam = "";
    private int count;

    public void fillArray() {
        for (int i = 0; i < players.length; i++) {
            players[i] = roster.getPlayers()[i];
        }
    }

    ArrayList<Player> remainingPlayers = new ArrayList<>(Arrays.asList(players));

    // convert Player to String to check if players are in the list
    // another/better way to do this?
    private final String[] playerArrayToString = Arrays.stream(players).map(Object::toString).
            toArray(String[]::new);

    public void selectRandomPlayer() {
        fillArray();
        Random random = new Random();
        playerToGuess = players[random.nextInt(players.length)];
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
            System.out.printf(format, players[i]);
            System.out.printf(format, players[i + 10]);
            System.out.printf(format, players[i + 20]);
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

            // checks for player in player pool
            // don't understand code, just knows what it does
            if (Arrays.stream(playerArrayToString).noneMatch(userGuess::equalsIgnoreCase)) {
                System.out.println("Please select a player from the player pool.");
            } else {
                // only counts valid guesses
                count++;
            }

            // checks for correct player, else sets userGuess to player object attributes
            if (!userGuess.equalsIgnoreCase(playerToGuess.getName())) {
                for (Player player : players) {
                    if (userGuess.equalsIgnoreCase(player.getName())) {
                        userGuess = player.getName();
                        userGuessPosition = player.getPosition();
                        userGuessConference = player.getConference();
                        userGuessDivision = player.getDivision();
                        userGuessTeam = player.getTeam();
                    }
                }

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
                if (!userGuessPosition.equals(playerToGuess.getPosition())) {
                    for (String pos : positionArray) {
                        if (userGuessPosition.equals(pos)) {
                            System.out.print("The player is not a " + pos);
                        }
                    }
                } else {
                    for (String pos : positionArray) {
                        if (userGuessPosition.equals(pos)) {
                            System.out.print("The player is a " + pos);
                        }
                    }
                }

                // checks conference (wrong)
                if (!userGuessConference.equals(playerToGuess.getConference())) {
                    for (String conf : conferenceArray) {
                        if (userGuessConference.equals(conf)) {
                            System.out.println(", is not in the " + conf);
                        }
                    }

                    // checks conference (correct)
                } else {
                    for (String conf : conferenceArray) {
                        if (userGuessConference.equals(conf)) {
                            System.out.print(", is in the " + conf);

                            // checks division
                            if (!userGuessDivision.equals(playerToGuess.getDivision())) {
                                System.out.println(", but not the " + userGuessConference + " " + userGuessDivision);
                            } else {
                                System.out.print(" " + userGuessDivision);

                                // checks team
                                if (!userGuessTeam.equals(playerToGuess.getTeam())) {
                                    for (String team : teamArray) {
                                        if (userGuessTeam.equals(team)) {
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
        for (Player player : players) {

            // removes position -- guessed incorrectly
            if (!userGuessPosition.equals(playerToGuess.getPosition())) {
                if (userGuessPosition.equals(player.getPosition())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, removes other positions
                if (!userGuessPosition.equals(player.getPosition())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes conference -- guessed incorrectly
            if (!userGuessConference.equals(playerToGuess.getConference())) {
                if (userGuessConference.equals(player.getConference())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, removes other conference
                if (!userGuessConference.equals(player.getConference())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes divisions only if correctly guessed conference
            // division guessed correctly, removes other divisions
            if (!userGuessDivision.equals(playerToGuess.getDivision())) {
                if (userGuessDivision.equals(player.getDivision())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed incorrectly
                if (!userGuessDivision.equals(player.getDivision())) {
                    remainingPlayers.remove(player);
                }
            }

            // removes team -- guessed incorrectly
            if (!userGuessTeam.equals(playerToGuess.getTeam())) {
                if (userGuessTeam.equals(player.getTeam())) {
                    remainingPlayers.remove(player);
                }
            } else {
                // guessed correctly, remove other teams
                if (!userGuessTeam.equals(player.getTeam())) {
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