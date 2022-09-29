package GameEntity;

public class Piece {
    public Piece(int type, int row, int col) {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    private int type;

    private int row;

    private int col;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
