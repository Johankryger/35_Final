package Entity;

/**
 * Class which has an array with players and can find a specific player or the next player.
 */
public class PlayerList {
    private Player[] players;
    /**
     * Index number for the player array, which changes according to what the methods does
     */
    private int index = 0;

    public PlayerList() {

    }

    /**
     * Adds entered players to the players array attribute.
     * @param playerNames String array of names that is entered, so it can be added to player array.
     * @param amountOfPlayers Number of players, so we know how long the player array is.
     */
    public void addPlayers(String[] playerNames, int amountOfPlayers) {
        this.players = new Player[amountOfPlayers];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
        }
    }

    /**
     * Method to find all players (as an Player object) in a Player[].
     * @return players
     */
    public Player[] getAllPlayers() {
        return players;
    }

    /**
     * @return all players' names as a String array.
     */
    public String[] getPlayerNames() {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            names[i] = players[i].getName();
        }
        return names;
    }

    /**
     * Method o kill a specific player if it is the player's own turn
     * @param playerName To find the specific player
     */
    public void killPlayer(String playerName) {

        boolean playerDiedOnOwnTurn = searchPlayer(playerName) == getPlayer();

        Player[] arr = new Player[this.players.length - 1];

        int counter = 0;
        for (int i = 0; i < players.length; i++) {
            if (!players[i].getName().equals(playerName)) {
                arr[counter] = players[i];
                counter++;
            }
        }

        this.players = arr;

        if (playerDiedOnOwnTurn) {
            this.index = (this.index - 1 + players.length) % players.length;
        }

    }

    public Player getPlayer() {
        return players[index];
    }

    /**
     * Method to find player, if it isn't the player's turn.
     * @param name A player name which is playing the game
     * @return The player you were looking for, as long as the player is in the game or else it will return null.
     */
    public Player searchPlayer(String name) {
        Player choosenPlayer = null;

        for (Player p : players) {
            if (name != null && name.equals(p.getName())) {
                choosenPlayer = p;
            }
        }

        return choosenPlayer;
    }

    /**
     * Finds the index number of the next player
     */
    public void nextPlayer() {
        this.index = ++index % players.length;
    }


}
