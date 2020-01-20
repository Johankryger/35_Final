package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class BalanceTest {

    @Test
    public void getAmount() {
        Player player1 = new Player("Test1");
        assertEquals(30000, player1.getBalance().getAmount());
    }

    @Test
    public void pay() {
        Player player1 = new Player("Test1");
        Player player2 = new Player("Test2");
        Player player3 = new Player("Test3");
        player1.getBalance().pay(3000);
        player2.getBalance().pay(31000);
        player3.getBalance().pay(-3000);
        assertEquals(27000, player1.getBalance().getAmount());
        assertEquals(30000, player2.getBalance().getAmount());
        assertEquals(30000, player3.getBalance().getAmount());
    }

    @Test
    public void add() {
        Player player1 = new Player("Test1");
        Player player2 = new Player("Test2");
        Player player3 = new Player("Test3");
        player1.getBalance().add(4500);
        player2.getBalance().add(-1000);
        player3.getBalance().add(1000000);
        assertEquals(34500,player1.getBalance().getAmount(),0);
        assertEquals(30000,player2.getBalance().getAmount(),0);
        assertEquals(1030000,player3.getBalance().getAmount(),0);
    }
}