public class PlayerList {
    private Player[] players;
    private int index = 0;

    public PlayerList(int amountOfPlayers) {
        players = new Player[amountOfPlayers];
    }

    public void addPlayers(String[] playerNames) {
//        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
        }
    }

    public Player[] getAllPlayers() {
        return players;
    }

    public Player getPlayer() {
        return players[index];
    }

    public Player searchPlayer(String name) {
        Player choosenPlayer = null;

        for (Player p : players) {
            if (name == p.getName()) {
                choosenPlayer = p;
            }
        }

        return choosenPlayer;
    }

    public void nextPlayer() {
        this.index = ++index % players.length;
    }


}
