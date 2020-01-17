package Entity;

public class PlayerList {
    private Player[] players;
    private int index = 0;

    public PlayerList() {

    }

    public void addPlayers(String[] playerNames, int amountOfPlayers) {
        this.players = new Player[amountOfPlayers];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
        }
    }


    public Player[] getAllPlayers() {
        return players;
    }

    public String[] getPlayerNames() {
        String[] names = new String[players.length];
        for (int i = 0; i < players.length; i++) {
            names[i] = players[i].getName();
        }
        return names;
    }

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

    public Player searchPlayer(String name) {
        Player choosenPlayer = null;

        for (Player p : players) {
            if (name != null && name.equals(p.getName())) {
                choosenPlayer = p;
            }
        }

        return choosenPlayer;
    }

    public void nextPlayer() {
        this.index = ++index % players.length;
    }


}
