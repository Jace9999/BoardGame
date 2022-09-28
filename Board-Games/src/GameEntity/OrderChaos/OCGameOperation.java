package GameEntity.OrderChaos;

import GameEntity.*;

import java.util.Scanner;

public class OCGameOperation extends GameOperation {


    public OCGameOperation(int gameType, int boardScaleRow) {
        super(gameType, boardScaleRow);
    }

    @Override
    public void menu() {
        System.out.println("Welcome to Order & Chaos");
        super.menu();
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
        if(consecutive == boardScaleRow){
            updateRecord(turn);
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
            updateRecord(turn);
            return true;
        }
        consecutive = 0;
        //TODO
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