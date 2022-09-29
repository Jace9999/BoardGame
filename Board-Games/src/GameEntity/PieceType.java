package GameEntity;

public enum PieceType {
    X_Type(0, "X"),
    O_Type(1, "O");

    private String symbol;
    private int typeNum;

    public static String getSymbolByIndex(int index){
        for(PieceType symbol : PieceType.values()){
            if(index == symbol.getTypeNum()){
                return symbol.getSymbol();
            }
        }
        return "";
    }

    PieceType( int typeNum, String symbol) {
        this.symbol = symbol;
        this.typeNum = typeNum;
    }

    public static int getTypeNumBySymbol(String symbol){
        for(PieceType type : PieceType.values()){
            if(type.getSymbol().equals(symbol)){
                return type.getTypeNum();
            }
        }
        return 99;
    }
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(int typeNum) {
        this.typeNum = typeNum;
    }
}
