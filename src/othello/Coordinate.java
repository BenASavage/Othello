package othello;

import java.util.Objects;

/**
 * A coordinate is a pair of 2 integers, x and y. This class is used to communicate positions in a 2d array.
 */
public class Coordinate {

    /**
     * The position on the x-axis or the first index of a 2d array.
     */
    public final int x;

    /**
     * The position on the y-axis or the second index of a 2d array.
     */
    public final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
