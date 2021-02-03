package othello;

import java.awt.*;
import java.util.Objects;

/**
 * A Disc is a token that can either be black or white. The token is placed onto the game board and can switch color
 * based on interactions with other Discs. The amount of Discs in a player's control at the end of the game determines
 * the winner.
 * @see Board
 */
public class Disc {

    /**
     * The color object refers to which java awt Color the Disc is currently. The two states the Disc can be is black
     * or white. The initial state is passed into the Disc constructor.
     * @see #Disc(Color)
     */
    private Color color;

    /**
     * Constructor for the Disc Class. Initialises the color of the disc to the passed Color, provided it is either
     * black or white. If the provided color is not Color.BLACK or Color.WHITE then the color of the Disc is
     * set to black.
     * @param color the initial color state of the Disc, this can either be Color.BLACK or Color.WHITE
     * @see #color
     */
    public Disc(Color color) {
        if (color.equals(Color.BLACK) || color.equals(Color.WHITE)) {
            this.color = color;
        } else {
            this.color = Color.BLACK;
        }
    }

    /**
     * Returns the color of the Disc. The expected values should only ever be Color.BLACK or Color.WHITE.
     * @return color of the Disc.
     * @see #color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Switches the color of the Disc to its opposite. I.e. if black then white or if white then black. Additionally,
     * this adds another layer of security such that the Disc can only be black or white. If the Disc constructor's
     * input validation is bypassed, this method ensures that the color will be either Color.BLACK or Color.WHITE.
     * If the switchColor method is called and it finds that the Disc's color field is equal to Color.BLACK then the
     * color is set to Color.WHITE, however if it finds that color is not equal to Color.BLACK, meaning equal to any
     * other color (done through an else statement), then the color is set to Color.BLACK.
     * @see #color
     */
    public void switchColor() {
        if (color.equals(Color.BLACK)) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return Objects.equals(color, disc.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
