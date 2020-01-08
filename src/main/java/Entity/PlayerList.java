package Entity;

public class PlayerList {
    private Player[] players;
    private int index = 0;

    public PlayerList() {

    }

    public void addPlayers(String[] playerNames, int amountOfPlayers) {
        this.players = new Player[amountOfPlayers];
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
            if (name.equals(p.getName())) {
                choosenPlayer = p;
            }
        }

        return choosenPlayer;
    }

    public void nextPlayer() {
        this.index = ++index % players.length;
    }

    public void transfer(int amount, String from, String to){
        searchPlayer(from).getBalance().pay(amount);
        searchPlayer(to).getBalance().add(amount);
    }
}
