package GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOperation {

    protected List<Team> teamList;

    protected Player team1Player;

    protected Player team2Player;

    protected Player thisRoundWinner;

    protected int boardScaleRow;

    protected int boardScaleCol;
    protected Board board;

    protected int playerDecision;

    protected int gameType;

    public GameOperation(int gameType){
        this.gameType = gameType;
        teamList = new ArrayList<>();
        menu();
    }

    public GameOperation(int gameType, int boardScaleRow, int boardScaleCol){
        this.boardScaleRow = boardScaleRow;
        this.boardScaleCol = boardScaleCol;
        this.gameType = gameType;
        teamList = new ArrayList<>();
        menu();
    }

    public void menu() {
        createTeam(1);
        createTeam(2);
        selectRoundPlayer(0);
        selectRoundPlayer(1);
        selectCharacterForPlayer();
        initialBoard();
        Gaming();
    }


    public void createTeam(int teamNum){
        System.out.println("Please enter the player name for team " + teamNum + ", end with /");
        Team team = new Team();
        List<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String s ="";
        while(true){
            s = scanner.nextLine();
            if(s.equals("/")){
                break;
            }
            Player player = new Player();
            player.setName(s);
            playerList.add(player);
        }
        team.setPlayerList(playerList);
        this.teamList.add(team);
    }

    public boolean placePiece(int turn, Piece piece) {
        String[][] boardArray = this.board.getBoardArray();
        int row = piece.getRow();
        int col = piece.getCol();
        int type = piece.getType();
        String symbol = PieceType.getSymbolByIndex(type);
        boardArray[row*2-2][col*2-2] = symbol;
        this.board.setBoardArray(boardArray);
        if(turn < this.boardScaleRow * 2 - 2){
            return false;
        }else{
            return checkEnd(piece, turn);
        }
    }

    public void printBattleRecord(){
        int teamNum = 1;
        for(Team team: this.teamList){
            System.out.println("The total win time of team " + teamNum  + " is " + team.getWinTime());
            teamNum++;
            for(Player player: team.getPlayerList()){
                System.out.println(player.getName() + " has won " + player.getWinTimes() + " and stalemate time is " + player.getStalemateTime());
            }
        }
    }

    public void updateRecord(int TeamNum){
        if(TeamNum == 0){
            this.thisRoundWinner = team1Player;
        }else{
            this.thisRoundWinner = team2Player;
        }
        teamList.get(TeamNum).setWinTime(teamList.get(0).getWinTime() + 1);
        Player winner = this.thisRoundWinner;
        winner.setWinTimes(winner.getWinTimes() + 1);
        System.out.println("Congratulate " + this.thisRoundWinner.getName() + " win the game!");
    }

    public void selectCharacterForPlayer(){

    }

    public void selectRoundPlayer(int TeamNum){
        System.out.println("Select player in team" + TeamNum + 1 + " for this round game, Enter Player Index");
        int index = 1;
        for(Player player: this.teamList.get(TeamNum).getPlayerList()){
            System.out.println(index + " " + player.getName());
            index++;
        }
        Scanner scanner = new Scanner(System.in);
        int playerIndex = scanner.nextInt();
        teamList.get(TeamNum).setNextGamePlayer(playerIndex-1);
        if(TeamNum == 0){
            this.team1Player =  teamList.get(TeamNum).getNextGamePlayer();
        }else{
            this.team2Player =  teamList.get(TeamNum).getNextGamePlayer();
        }
    }

    public boolean checkEnd(Piece piece, int turn) {
        return  false;
    }

    public void initialBoard() {
        Scanner scanner = new Scanner(System.in);
        if(this.gameType != GameType.OrderChaos.getGameType()){
            System.out.println("Please enter the scale for the game board, for example: 3 means 3x3, 6 means 6x6");
            int scaleSize = scanner.nextInt();
            this.boardScaleRow = scaleSize;
            this.boardScaleCol = scaleSize;
        }
        this.board = new Board(boardScaleRow, boardScaleCol);
        int rowLength = (board.getMap_row() * 2)- 1;
        int colLength = (board.getMap_column() * 2) - 1;
        String[][] boardArray = new String[rowLength][colLength];
        for(int i=0;i<rowLength;i++){
            for(int j=0;j<colLength;j++){
                if(i % 2 == 0 && j % 2 == 0){
                    boardArray[i][j] = " ";
                }else if(i % 2 == 0 && j % 2 == 1){
                    boardArray[i][j] = "|";
                }else if(i % 2 == 1 && j % 2 == 0){
                    boardArray[i][j] = "-";
                }else if(i % 2 == 1 && j % 2 == 1){
                    boardArray[i][j] = "+";
                }
            }
        }
        board.setBoardArray(boardArray);
    }

    public boolean menuAfterGame(){
        Scanner scanner = new Scanner(System.in);
        boolean loop;
        do{
            System.out.println();
            System.out.println("Press 1 to continue play Tic Tac Toe.");
            System.out.println("Press 2 to check battle record.");
            System.out.println("Press 8 to exit game.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    return false;
                case 2:
                    printBattleRecord();
                    loop = true;
                    break;
                case 8:
                    this.playerDecision = 0;
                    return false;
                default:
                    loop = true;
                    System.out.println("Wrong input, please select again.");
            }
        }while(loop);
        return false;
    }

    public void printBoard(){
        String[][] boardArray = this.board.getBoardArray();
        for(int i=0;i<boardArray.length;i++){
            for (int j=0;j<boardArray[0].length;j++){
                System.out.print(boardArray[i][j]);
            }
            System.out.println();
        }
    }

    public void Gaming() {
        boolean continueGame;
        do{
            int turn = 0;
            boolean isEnd;
            do{
                Piece piece = createPiece(turn);
                isEnd = this.placePiece(turn,piece);
                turn++;
            }while(!isEnd);
            continueGame = menuAfterGame();
        }while(continueGame);
    }

    public Piece createPiece(int turn){
        return null;
    }

}
