package GameEntity;

public class Board {
    private int boardSize;
    private Unit boardArray[][];

    public Board(int map_row, int boardSize, Unit[][] boardArray) {
        this.boardSize = boardSize;
        this.boardArray = boardArray;
    }

    public Unit[][] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(Unit[][] boardArray) {
        this.boardArray = boardArray;
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
