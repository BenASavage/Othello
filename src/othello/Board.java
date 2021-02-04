package othello;

import java.awt.*;

/**
 * Board is the logical representation of a game of Othello. A Board object is characterized by an 8x8 grid where Discs
 * can be placed. When a Disc is played onto the board, the board updates and switches other Discs on the board as per
 * the rules of Othello. The board class keeps track of player turn and alternates it each time a Disc is placed.
 * @see Disc
 */
public class Board {

    /**
     * The logical representation of the 8x8 grid that contains the discs placed by the players.
     * @see #placeDisc(Coordinate)
     */
    private Disc[][] tiles;

    /**
     * Keeps track of whose turn it is. The playerTurn variable can either be Black or White.
     * Each time a new Disc is placed the playerTurn will alternate.
     * @see #placeDisc(Coordinate)
     * @see #switchTurn()
     */
    private Color playerTurn;

    /**
     * Constructor for the Board Class. Calls the initTiles method to instantiate the tiles object. Sets the starting
     * player to Black
     * @see #tiles
     * @see #initTiles()
     * @see #playerTurn
     */
    public Board() {
        this.tiles = initTiles();
        this.playerTurn = Color.BLACK;
    }

    /**
     * Sets the board to its starting state. <p></p>
     * This method creates an 8x8 grid, using a 2d Disc array, and fills the center 4 positions/tiles with Discs of
     * alternating colors (White, Black) such that each color is diagonal to itself. E.g. WB/BW.
     * The pattern starts at (3,3) with white.
     * Used for the instantiation of the tiles object in the Board constructor.
     * @return 2d Disc array of size 8x8 with the center four tiles filled with Discs of alternating colors
     * @see #tiles
     * @see #Board()
     * @see Disc
     */
    private Disc[][] initTiles() {
        Disc[][] tiles = new Disc[8][8];
        tiles[3][3] = new Disc(Color.WHITE);
        tiles[4][3] = new Disc(Color.BLACK);
        tiles[3][4] = new Disc(Color.BLACK);
        tiles[4][4] = new Disc(Color.WHITE);
        return tiles;
    }

    /**
     * @return 2d Disc array (8x8)
     * @see #tiles
     */
    public Disc[][] getTiles() {
        return tiles;
    }

    /**
     * @return A Color, either black or white depending on whose turn it is
     * @see #playerTurn
     */
    public Color getPlayerTurn() {
        return playerTurn;
    }

    /**
     * Calculates which positions on the board can be played based on whose turn it currently is.
     * TODO describe the logic behind which tiles are playable and which are not
     * @return an array of coordinates at which it is legal to place a Disc
     * @see Coordinate
     * @see #playerTurn
     */
    public Coordinate[] getPlayableTiles() {
        //TODO
        return null;
    }

    /**
     * Switches whose turn it is.<p></p>
     * The turn alternates between black and white.
     * I.e. if black then white and vice versa. The change is kept track by the playerTurn object.
     * This method is called at the end of the placeDisc method.
     * @see #playerTurn
     * @see #placeDisc(Coordinate)
     */
    private void switchTurn() {
        if (playerTurn.equals(Color.BLACK)) {
            playerTurn = Color.WHITE;
        } else {
            playerTurn = Color.BLACK;
        }
    }

    /**
     * Places a Disc onto the board and correspondingly flips other Discs to the placed Disc's color and then switches
     * the turn.<p></p>
     * Creates a new Disc whose color is determined by the playerTurn object in the tiles array at the position
     * specified by the x and y values of the coordinate parameter.
     * TODO describe the logic of switching tiles
     * Then calls the switchTurn method.
     * @param coordinate the location a Disc is to be placed at
     * @see Disc
     * @see #tiles
     * @see #switchTurn()
     */
    public void placeDisc(Coordinate coordinate) {
        Disc placedDisc = new Disc(playerTurn);
        tiles[coordinate.x][coordinate.y] = placedDisc;

        //TODO logic for updating the board
        //Checks to the right
        try {
            if (tiles[coordinate.x + 1][coordinate.y] != null
                    && !placedDisc.equals(tiles[coordinate.x + 1][coordinate.y])) {
                for (int i = coordinate.x + 2; i < tiles[0].length - coordinate.x - 1; i++) {
                    if (placedDisc.equals(tiles[i][coordinate.y])) {
                        for (int j = coordinate.x + 1; j < i; j++) {
                            tiles[j][coordinate.y].switchColor();
                        }
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        //Checks diagonally up and right
        try {
            if (tiles[coordinate.x + 1][coordinate.y - 1] != null
                    && !placedDisc.equals(tiles[coordinate.x + 1][coordinate.y - 1])) {
                int j = coordinate.y - 2;
                for (int i = coordinate.x + 2; i < tiles[0].length - coordinate.x - 1 && j >= 0; i++) {
                    if (placedDisc.equals(tiles[i][j])) {
                        int h = coordinate.y - 1;
                        for (int k = coordinate.x + 1; k < i; k++) {
                            tiles[k][h].switchColor();
                            h--;
                        }
                        break;
                    }
                    j--;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        //Checks upwards
        try {
            if (tiles[coordinate.x][coordinate.y - 1] != null
                    && !placedDisc.equals(tiles[coordinate.x][coordinate.y + 1])) {
                for (int i = coordinate.y - 2; i > 0; i--) {
                    if (placedDisc.equals(tiles[coordinate.x][i])) {
                        for (int j = coordinate.y - 1; j > i; j--) {
                            tiles[coordinate.x][j].switchColor();
                        }
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        //Checks diagonally up and left


        //Checks to the left


        //Checks diagonally down and left


        //Checks downwards


        //Checks diagonally down and to the right

        switchTurn();
    }
}
