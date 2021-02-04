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
    void placeDiscAlternateColors() {
        Board board = new Board();
        Disc testDiscBlack = new Disc(Color.BLACK);
        Disc testDiscWhite = new Disc(Color.WHITE);
        board.placeDisc(new Coordinate(5,4));
        board.placeDisc(new Coordinate(3, 2));
        assertEquals(testDiscBlack, board.getTiles()[5][4]);
        assertEquals(testDiscWhite, board.getTiles()[3][2]);
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

    @Test
    void placeDiscFlippingUpLeft() {
        Board board = new Board();
        board.placeDisc(new Coordinate(2,3));
        board.placeDisc(new Coordinate(2,4));
        board.placeDisc(new Coordinate(4,5));
        assertEquals(Color.BLACK, board.getTiles()[3][4].getColor());
    }

    @Test
    void placeDiscFlippingLeft() {
        Board board = new Board();
        board.placeDisc(new Coordinate(5,4));
        assertEquals(Color.BLACK, board.getTiles()[4][4].getColor());
    }

    @Test
    void placeDiscFlippingDownLeft() {
        Board board = new Board();
        board.placeDisc(new Coordinate(5,4));
        board.placeDisc(new Coordinate(5,3));
        board.placeDisc(new Coordinate(5,2));
        assertEquals(Color.BLACK, board.getTiles()[4][3].getColor());
    }

    @Test
    void placeDiscFlippingDown() {
        Board board = new Board();
        board.placeDisc(new Coordinate(3,2));
        assertEquals(Color.BLACK, board.getTiles()[3][3].getColor());
    }

    @Test
    void placeDiscFlippingDownRight() {
        Board board = new Board();
        board.placeDisc(new Coordinate(4,5));
        board.placeDisc(new Coordinate(3,5));
        board.placeDisc(new Coordinate(2,3));
        assertEquals(Color.BLACK, board.getTiles()[3][4].getColor());
    }

    @Test
    void placeDiscMultipleDirectionFlips() {
        Board board = new Board();
        board.placeDisc(new Coordinate(2,3));
        board.placeDisc(new Coordinate(2,4));
        board.placeDisc(new Coordinate(2,5));
        assertEquals(Color.BLACK, board.getTiles()[3][4].getColor());
        assertEquals(Color.BLACK, board.getTiles()[2][4].getColor());
    }

    @Test
    void placeDiscMultipleFlips() {
        Board board = new Board();
        board.placeDisc(new Coordinate(2,3));
        board.placeDisc(new Coordinate(4,2));
        board.placeDisc(new Coordinate(5,5));
        board.placeDisc(new Coordinate(1,3));
        assertEquals(Color.WHITE, board.getTiles()[2][3].getColor());
        assertEquals(Color.WHITE, board.getTiles()[3][3].getColor());
    }
}