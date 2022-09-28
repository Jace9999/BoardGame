package GameEntity;

import java.util.List;

public class Team {

    private int winTime;

    private int stalemateTime;
    private List<Player> playerList;

    private int nextGamePlayer;

    public void printTeamBattleRecord(){

    }

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

    public void addNewPlayer(Player player){
        playerList.add(player);
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

    public void removePlayer(String name){
        if(playerList.size() == 0){
            throw new RuntimeException("Current Team do not has any player.");
        }
        boolean find = false;
        for(int i=0;i<playerList.size();i++){
            if(playerList.get(i).getName().equals(name)){
                playerList.remove(i);
                find = true;
            }
        }
        if(!find){
            throw new RuntimeException("Player Name is not correct.");
        }
    }
}
