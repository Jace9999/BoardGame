package GameEntity.OrderChaos;

import GameEntity.*;

import java.util.Scanner;

public class OCGameOperation extends GameOperation {

    private int orderPlayerTeam;
    private int chaoPlayerTeam;

    private final static int WinRequire =5;

    public OCGameOperation(int gameType, int boardScaleRow, int boardScaleCol) {
        super(gameType, boardScaleRow, boardScaleCol);
    }

    @Override
    public void menu() {
        System.out.println("Welcome to Order & Chaos");
        super.menu();
    }
    public void selectCharacterForPlayer(){
        System.out.println("Please enter the player number of Order");
        int num = new Scanner(System.in).nextInt();
        if(num == 1){
            orderPlayerTeam= 0;
            chaoPlayerTeam = 1;
        }else{
            orderPlayerTeam= 1;
            chaoPlayerTeam = 0;
        }
    }

    @Override
    public boolean checkEnd(Piece piece, int turn) {
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
        if(consecutive == WinRequire){
            updateRecord(orderPlayerTeam);
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
        if(consecutive == WinRequire){
            updateRecord(orderPlayerTeam);
            return true;
        }
        consecutive = 0;
        if(row==col){
            for(int i=0;i<boardArray.length;i+=2){
                if(!boardArray[i][i].equals(curPieceType)){
                    break;
                }else{
                    consecutive++;
                }
            }
        }
        if(consecutive == WinRequire){
            updateRecord(orderPlayerTeam);
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
        if(consecutive == WinRequire){
            updateRecord(orderPlayerTeam);
            return true;
        }

        if(turn == boardScaleRow * boardScaleRow - 1){
            updateRecord(chaoPlayerTeam);
            return true;
        }
        return false;
    }

    public Piece createPiece(int turn){
        printBoard();
        int playerNum = turn % 2 + 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter piece type for Player " + playerNum);
        String symbol = scanner.nextLine();
        System.out.println("Enter the position for Player " + playerNum);
        String position = scanner.nextLine();
        String[] split = position.split(",");
        int row = Integer.parseInt(split[0]);
        int col = Integer.parseInt(split[1]);
        int pieceType = PieceType.getTypeNumBySymbol(symbol);
        return new Piece(pieceType, 0,row,col);
    }

}
