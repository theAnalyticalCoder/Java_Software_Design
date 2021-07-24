import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShuffledPlayTest {
    private ArrayList<Integer> aAlreadyPlayed= new ArrayList<>();

    private ShuffledPlay aShuffled=new ShuffledPlay(-1,0,aAlreadyPlayed);
    @AfterEach
    void reset(){
        aAlreadyPlayed=new ArrayList<>();
        aShuffled.setSize(0);
        aShuffled.setCurrent(-1);
    }
    @Test
    void getNext() {
        aShuffled.setSize(1000);
        /**
         * Tests randomness
         * 1/1000000 chance of this happening and it being correct*/
        int a=0;
        a=Math.max(aShuffled.getNext(),aShuffled.getNext());
        assertFalse(a==0);
        aAlreadyPlayed.add(0);
        aShuffled.setSize(2);
        assertFalse(aShuffled.getNext()==0);

    }

    @Test
    void hasNext() {
        aAlreadyPlayed.add(0);
        aShuffled.setSize(1);
        assertFalse(aShuffled.hasNext());
        aShuffled.setSize(2);
        assertTrue(aShuffled.hasNext());
    }
}