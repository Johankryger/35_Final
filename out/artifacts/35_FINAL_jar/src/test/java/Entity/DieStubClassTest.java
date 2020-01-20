package Entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class DieStubClassTest {

    Die d1 = new DieStubClass();

    //JUnit test of rollDice and rollDiceOutcome
    @Test
    public void rollDice() {
        assertEquals(5, d1.roll() );
    }


    @Test
    public void rollDiceOutcome() {
        int[] arr = new int[6];

        for (int i = 0; i < 36000; i++) {
            int value = d1.roll();
            switch (value) {
                case 1:
                    arr[0]++;
                    break;
                case 2:
                    arr[1]++;
                    break;
                case 3:
                    arr[2]++;
                    break;
                case 4:
                    arr[3]++;
                    break;
                case 5:
                    arr[4]++;
                    break;
                case 6:
                    arr[5]++;
                    break;

            }
        }
        assertEquals(0, arr[0]);
        assertEquals(0, arr[1]);
        assertEquals(0, arr[2]);
        assertEquals(0, arr[3]);
        assertEquals(36000, arr[4]);
        assertEquals(0, arr[5]);

    }
}