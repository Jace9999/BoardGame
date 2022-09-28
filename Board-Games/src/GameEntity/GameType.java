package GameEntity;

public enum GameType {
    TicTacToe(0, "TicTacToe"),
    OrderChaos(1, "OrderChaos");

    private String gameName;
    private int gameType;

    GameType( int gameType, String gameName) {
        this.gameName = gameName;
        this.gameType = gameType;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }
}
