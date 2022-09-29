package GameEntity;

public class Unit {

    String symbol;
    Boolean isOccupied;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public Unit(String symbol, Boolean isOccupied) {
        this.symbol = symbol;
        this.isOccupied = isOccupied;
    }
}
