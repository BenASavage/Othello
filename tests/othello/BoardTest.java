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
        board.placeDisc(new Coordinate(2,3));
        assertEquals(Color.BLACK, board.getTiles()[3][3].getColor());
    }

    @Test
    void placeDiscFlippingUpRight() {
        Board board = new Board();
        board.placeDisc(new Coordinate(2,3));
        board.placeDisc(new Coordinate(2,4));
        board.placeDisc(new Coordinate(2,5));
        assertEquals(Color.BLACK, board.getTiles()[3][4].getColor());
    }

    @Test
    void placeDiscFlippingUp() {
        Board board = new Board();
        board.placeDisc(new Coordinate(4,5));
        assertEquals(Color.BLACK, board.getTiles()[4][4].getColor());
    }
}