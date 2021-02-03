package othello;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void getPlayableTiles() {
    }

    @Test
    void placeDisc() {
        Board board = new Board();
        Disc testDisc = new Disc(Color.BLACK);
        board.placeDisc(new Coordinate(5,4));
        assertEquals(testDisc, board.getTiles()[5][4]);
    }

    @Test
    void placeDiscFlippingRight() {
        Board board = new Board();
        board.placeDisc(new Coordinate(5,4));
        assertEquals(Color.BLACK, board.getTiles()[4][4].getColor());
    }
}