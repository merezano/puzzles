import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecretEntranceTest {
    private static final int STARTING_POSITION = 50;
    private SecretEntrance entrance;

    @BeforeEach
    public void setUp() {
        entrance = new SecretEntrance();
    }

    @Test public void ensureStartingPositionIsCorrect() {
        assertEquals(STARTING_POSITION, entrance.getDialPosition());
    }

    @Test public void ensureTheEntranceIsLockedAtTheBeginning() {
        assertEquals(0, entrance.getPassword());
    }

    @Test
    public void whenTheDialRotatesLeftThePositionMovesBackward() {
        entrance.rotateDial("L68");
        assertEquals(82, entrance.getDialPosition());
    }

    @Test
    public void whenTheDialRotatesRightThePositionMovesForward() {
        entrance.rotateDial("R48");
        assertEquals(98, entrance.getDialPosition());
    }

    @Test
    public void thePasswordIsTheNumberOfClicksThatTouchTheZeroPosition() {
        makeASeriesOfMoves();
        assertEquals(6, entrance.getPassword());
    }

    private void makeASeriesOfMoves() {
        String[] moves = {
                "L68",
                "L30",
                "R48",
                "L5",
                "R60",
                "L55",
                "L1",
                "L99",
                "R14",
                "L82"};
        System.out.println("The dial starts by pointing at " + entrance.getStartingPosition());
        for (String move : moves) {
            entrance.rotateDial(move);
            System.out.println("The dial rotated " + move + " to point at " + entrance.getDialPosition());
        }
    }
}
