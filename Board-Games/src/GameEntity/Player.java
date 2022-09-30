package GameEntity;

public class Player {

    private String name;
    private int winTimes;

    private int stalemateTime;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", winTimes=" + winTimes +
                ", stalemateTime=" + stalemateTime +
                '}';
    }

    public int getStalemateTime() {
        return stalemateTime;
    }

    public void setStalemateTime(int stalemateTime) {
        this.stalemateTime = stalemateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }
}
