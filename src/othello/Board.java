package othello;

public class Board {

    private Disc[][] tiles;

    public Board() {
        this.tiles = initTiles();
    }

    public Disc[][] initTiles() {
        Disc[][] tiles = new Disc[8][8];
        return tiles;
    }

    public Disc[][] getTiles() {
        return tiles;
    }

    public Coordinate[] getPlayableTiles() {
        return null;
    }

    public void placeTile(Coordinate coordinate) {

    }
}
