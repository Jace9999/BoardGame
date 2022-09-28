package GameEntity.TicTacToe;

import GameEntity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TTTGameOperation extends GameOperation {


    public TTTGameOperation(int gameType) {
        super(gameType);
    }

    @Override
    public void menu() {
        System.out.println("Welcome to Tic Tac Toe");
        super.menu();
    }

    @Override
    public boolean checkEnd(Piece piece, int turn) {
        int TeamNum = turn % 2 == 0 ? 0 : 1;
        int col = piece.getCol();
        int row = piece.getRow();
        row = row * 2 - 2;
        col = col * 2 - 2;
        String[][] boardArray = this.board.getBoardArray();
        int type = piece.getType();
        int consecutive = 0;
        String curPieceType = PieceType.getSymbolByIndex(type);
        for(int i=0;i<boardArray[0].length;i+=2){
            if(!boardArray[row][i].equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == boardScaleRow){
            updateRecord(TeamNum);
            return true;
        }
        consecutive = 0;
        for(int i=0;i<boardArray.length;i+=2){
            if(!boardArray[i][col].equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == boardScaleRow){
            updateRecord(TeamNum);
            return true;
        }
        consecutive = 0;
        if(row==col && row % 2 == 0){
            for(int i=0;i<boardArray.length;i+=2){
                if(!boardArray[row][col].equals(curPieceType)){
                    break;
                }else{
                    consecutive++;
                }
            }
        }
        if(consecutive == boardScaleRow){
            updateRecord(TeamNum);
            return true;
        }
        consecutive = 0;
        if(row + col ==  boardArray.length -1){
            for(int i=0,j=boardArray.length -1;i<boardArray.length && j >=0;i+=2,j-=2){
                if(!boardArray[row][col].equals(curPieceType)){
                    break;
                }else{
                    consecutive++;
                }
            }
        }
        if(consecutive == boardScaleRow){
            updateRecord(TeamNum);
            return true;
        }
        return false;
    }

    public Piece createPiece(int turn){
        printBoard();
        int playerNum = turn % 2 + 1;
        System.out.println("Enter the position for Player " + playerNum);
        String position = new Scanner(System.in).nextLine();
        String[] split = position.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        int pieceType;
        if(turn % 2 == 0){
            pieceType = PieceType.X_Type.getTypeNum();
        }else{
            pieceType = PieceType.O_Type.getTypeNum();
        }
        return new Piece(pieceType, 0,row,col);
    }

    //    public TTTGameOperation(){
//
//        teamList = new ArrayList<>();
//        menu();
//    }
//

    //    @Override
//    public void createTeam(int teamNum) {
////        System.out.println("Please enter the player name for team " + teamNum + ", end with /");
////        Team team = new Team();
////        List<Player> playerList = new ArrayList<>();
////        Scanner scanner = new Scanner(System.in);
////        String s ="";
////        while(true){
////            s = scanner.nextLine();
////            if(s.equals("/")){
////                break;
////            }
////            Player player = new Player();
////            player.setName(s);
////            playerList.add(player);
////        }
////        team.setPlayerList(playerList);
////        this.teamList.add(team);
//    }

//    public void printBattleRecord(){
//        int teamNum = 1;
//        for(Team team: this.teamList){
//            System.out.println("The total win time of team " + teamNum  + " is " + team.getWinTime());
//            teamNum++;
//            for(Player player: team.getPlayerList()){
//                System.out.println(player.getName() + " has won " + player.getWinTimes() + " and stalemate time is " + player.getStalemateTime());
//            }
//        }
//    }

//    public boolean placePiece(int turn, Player player) {
//        int playerNum = turn % 2 + 1;
//        System.out.println("Enter the position for Player " + playerNum);
//        String position = new Scanner(System.in).nextLine();
//        String[] split = position.split(",");
//        int row = Integer.parseInt(split[0]);
//        int col = Integer.parseInt(split[1]);
//        String[][] boardArray = this.board.getBoardArray();
//        if(player.equals(this.team1Player)){
//            boardArray[row*2-2][col*2-2] = "X";
//        }else{
//            boardArray[row*2-2][col*2-2] = "O";
//        }
//        this.board.setBoardArray(boardArray);
//        if(turn < this.boardScale * 2 - 2){
//            return false;
//        }else{
//            return checkEnd(row, col, turn);
//        }
//    }

//    private void updateRecord(int turn){
//        this.thisRoundWinner = turn % 2 == 0 ? team1Player : team2Player;
//        if(thisRoundWinner.equals(team1Player)){
//            teamList.get(0).setWinTime(teamList.get(0).getWinTime() + 1);
//        }else{
//            teamList.get(1).setWinTime(teamList.get(1).getWinTime() + 1);
//        }
//    }

//    public void selectRoundPlayer(int TeamNum){
//        int index = 1;
//        for(Player player: this.teamList.get(TeamNum).getPlayerList()){
//            System.out.println(index + " " + player.getName());
//            index++;
//        }
//        Scanner scanner = new Scanner(System.in);
//        int playerIndex = scanner.nextInt();
//        teamList.get(TeamNum).setNextGamePlayer(playerIndex-1);
//        if(TeamNum == 0){
//            this.team1Player =  teamList.get(TeamNum).getNextGamePlayer();
//        }else{
//            this.team2Player =  teamList.get(TeamNum).getNextGamePlayer();
//        }
//    }


//    @Override
//    public void initialBoard(int rowSize, int colSize) {
//        this.board = new TTTBoard(rowSize, colSize);
//        int rowLength = (board.getMap_row() * 2)- 1;
//        int colLength = (board.getMap_column() * 2) - 1;
//        String[][] boardArray = new String[rowLength][colLength];
//        for(int i=0;i<rowLength;i++){
//            for(int j=0;j<colLength;j++){
//                if(i % 2 == 0 && j % 2 == 0){
////                    String s = Integer.toString(index);
////                    boardArray[i][j] = s;
////                    index++;
//                    boardArray[i][j] = " ";
//                }else if(i % 2 == 0 && j % 2 == 1){
//                    boardArray[i][j] = "|";
//                }else if(i % 2 == 1 && j % 2 == 0){
//                    boardArray[i][j] = "-";
//                }else if(i % 2 == 1 && j % 2 == 1){
//                    boardArray[i][j] = "+";
//                }
//            }
//        }
//        board.setBoardArray(boardArray);
//    }
//
//    public void printBoard(){
//        String[][] boardArray = this.board.getBoardArray();
//        for(int i=0;i<boardArray.length;i++){
//            for (int j=0;j<boardArray[0].length;j++){
//                System.out.print(boardArray[i][j]);
//            }
//            System.out.println();
//        }
//    }
}
