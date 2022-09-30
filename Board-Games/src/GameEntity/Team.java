package GameEntity;

import java.util.List;

public class Team {

    private int winTime;

    private int stalemateTime;
    private List<Player> playerList;

    private int nextGamePlayer;

    public Player getNextGamePlayer(){
        Player player = playerList.get(nextGamePlayer);
        return player;
    }

    public void setNextGamePlayer(int playerIndex){
        this.nextGamePlayer = playerIndex;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public int getWinTime() {
        return winTime;
    }

    public void setWinTime(int winTime) {
        this.winTime = winTime;
    }

    public int getStalemateTime() {
        return stalemateTime;
    }

    public void setStalemateTime(int stalemateTime) {
        this.stalemateTime = stalemateTime;
    }

}
