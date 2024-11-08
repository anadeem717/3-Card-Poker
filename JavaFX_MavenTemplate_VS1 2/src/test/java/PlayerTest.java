
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import java.util.ArrayList;


public class PlayerTest {

    private Player player;

    // Create a new player before each test
    @BeforeEach
    public void init() {
        player = new Player();
    }

    @Test
    public void testPlaceBet_ValidAnteBet() {
        boolean result = player.placeBet(5, "ante");
        assertTrue(result);
    }

    @Test
    public void testPlaceBet_ValidPairPlusBet() {
        boolean result = player.placeBet(5, "pp");
        assertTrue(result);
    }

    @Test
    public void testPlaceBet_InvalidAmount() {
        boolean result = player.placeBet(26, "ante");
        assertFalse(result);
    }

    @Test
    public void testPlaceBet_InvalidBetType() {
        boolean result = player.placeBet(10, "invalid");
        assertFalse(result);
    }

    @Test
    public void testSetAndGetHand() {
        // setting and getting the player's hand
        ArrayList<Card> newHand = new ArrayList<>();
        newHand.add(new Card('C', 10));
        newHand.add(new Card('C', 9));
        newHand.add(new Card('C', 8));

        player.setHand(newHand); // set player hand
        ArrayList<Card> playerHand = player.getHand(); // get hand

        assertEquals(3, playerHand.size());

        // 1st card
        assertEquals('C', playerHand.get(0).suit);
        assertEquals(10, playerHand.get(0).value);

        // 2nd card
        assertEquals('C', playerHand.get(1).suit);
        assertEquals(9, playerHand.get(1).value);

        //3rd card
        assertEquals('C', playerHand.get(2).suit);
        assertEquals(8, playerHand.get(2).value);
    }

    @Test
    public void testResetBets() {
        player.placeBet(10, "ante");
        player.placeBet(15, "pp");
        player.resetBets();

        assertEquals(0, player.getTotalWinnings());
        assertEquals(0, player.getPlayBet());
        assertEquals(0, player.getPairPlusBet());
    }

    @Test
    public void testUpdateWinnings() {
        player.updateWinnings(100);
        assertEquals(100, player.getTotalWinnings());

        player.updateWinnings(-90);
        assertEquals(10, player.getTotalWinnings());
    }

}
