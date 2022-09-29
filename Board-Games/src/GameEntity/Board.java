package GameEntity;

public class Board {
    private int map_row;
    private int map_column;
    private Unit boardArray[][];

    public Board(int map_row, int map_column, Unit[][] boardArray) {
        this.map_row = map_row;
        this.map_column = map_column;
        this.boardArray = boardArray;
    }

    public Unit[][] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(Unit[][] boardArray) {
        this.boardArray = boardArray;
    }

    public Board(int map_row, int map_column) {
        this.map_row = map_row;
        this.map_column = map_column;
    }

    public int getMap_row() {
        return map_row;
    }

    public void setMap_row(int map_row) {
        this.map_row = map_row;
    }

    public int getMap_column() {
        return map_column;
    }

    public void setMap_column(int map_column) {
        this.map_column = map_column;
    }

}
