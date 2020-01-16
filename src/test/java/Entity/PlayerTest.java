package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void move() {
        Player p1 = new Player("Hej");
        Player p2 = new Player("Mr. Miyagi");
        Player p3 = new Player("John Travolta");
        Player p4 = new Player("Yes");

        p1.move(5,true);
        p2.move(-2,true);
        p3.move(48,true);
        p4.setFieldPos(13);
        p4.move(-3,true);

        assertEquals(5,p1.getFieldPos());
        assertEquals(38,p2.getFieldPos());
        assertEquals(8,p3.getFieldPos());
        assertEquals(10,p4.getFieldPos());
    }

    @Test
    public void isAboutToLose() {
        Player p1 = new Player("Skipper Bent");
        p1.setAboutToLose(true);
        assertTrue(p1.isAboutToLose());
    }

    @Test
    public void isHasLost() {
        Player p1 = new Player("Mr. Meeseeks");
        p1.setHasLost(true);
        assertTrue(p1.isHasLost());
    }

    @Test
    public void setFieldPos() {
        Player p1 = new Player("Dota bad");
        Player p2 = new Player("CS gut");
        Player p3 = new Player("But me best");
        p1.setFieldPos(-20);
        p2.setFieldPos(48);
        p3.setFieldPos(13);
        assertEquals(0,p1.getFieldPos());
        assertEquals(8,p2.getFieldPos());
        assertEquals(13,p3.getFieldPos());
    }

    @Test
    public void getName() {
        Player.setPlayerCounter(0);
        Player p1 = new Player("Hejsa");
        Player p2 = new Player("");
        Player p3 = new Player("Player 3");
        assertEquals("Hejsa",p1.getName());
        assertEquals("Player 2",p2.getName());
        assertEquals("Player 3",p3.getName());
    }

    @Test
    public void setGotChanceCard() {
        Player p1 = new Player("Where yo at boi");
        Player p2 = new Player("I'm here");
        p1.setGotChanceCard(true);
        p2.setGotChanceCard(false);
        assertTrue(p1.hasGotChanceCard());
        assertFalse(p2.hasGotChanceCard());
    }

    @Test
    public void setGotFreeJailCard() {
        Player p1 = new Player("Where yo at boi");
        Player p2 = new Player("I'm here");
        p1.setGotFreeJailCard(true);
        p2.setGotFreeJailCard(false);
        assertTrue(p1.hasGotFreeJailCard());
        assertFalse(p2.hasGotFreeJailCard());
    }

    @Test
    public void hasExtraTurn() {
        Player p1 = new Player("OMEGALUL");
        Player p2 = new Player("This fun ecksDee");
        p1.extraTurn(true);
        p2.extraTurn(false);
        assertTrue(p1.hasExtraTurn());
        assertFalse(p2.hasExtraTurn());
    }

    @Test
    public void getTurnsInJail() {
        Player p1 = new Player("Jens");
        Player p2 = new Player("Benny");
        Player p3 = new Player("KappaPride");
        p1.addTurnInJail();
        p2.addTurnInJail();
        p2.addTurnInJail();
        assertEquals(1,p1.getTurnsInJail());
        assertEquals(2,p2.getTurnsInJail());
        assertEquals(0,p3.getTurnsInJail());
    }

    @Test
    public void isInJail() {
        Player p1 = new Player("Y?!");
        Player p2 = new Player("Tonari No Totoro");
        p1.setInJail(true);
        p2.setInJail(false);
        assertTrue(p1.isInJail());
        assertFalse(p2.isInJail());
    }

    @Test
    public void getPairCounter() {
        Player p1 = new Player("Test er boring ;-;");
        Player p2 = new Player("Der er for meget at teste -_-");
        p1.incrementPairCounter();
        p2.incrementPairCounter();
        p2.incrementPairCounter();
        assertEquals(1,p1.getPairCounter());
        assertEquals(2,p2.getPairCounter());
    }
}