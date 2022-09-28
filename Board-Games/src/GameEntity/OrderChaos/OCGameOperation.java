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
        boolean flag = checkPieceCoherent(piece, 5);
        if(flag){
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
