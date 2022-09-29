package GameEntity.OrderChaos;

import GameEntity.*;

import java.util.Scanner;

public class OCGameOperation extends GameOperation {

    private int orderPlayerTeam;
    private int chaoPlayerTeam;

    public final static int WinRequireNum =5;

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
        orderPlayerTeam = num - 1;
        chaoPlayerTeam = num;
    }

    @Override
    public boolean checkEnd(Piece piece, int turn) {
        boolean flag = checkPieceCoherent(piece, WinRequireNum);
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
        String symbol;
        boolean symbolAvailability;
        int pieceType;
        do{
            symbolAvailability = true;
            System.out.println("Enter piece type for Player " + playerNum);
            symbol = scanner.nextLine();
            pieceType = PieceType.getTypeNumBySymbol(symbol);
            if(pieceType == 99){
                System.out.println("This symbol is not valid, you only can choose \"X\" or \"O\" !");
                symbolAvailability = false;
            }
        }while(!symbolAvailability);

        return validPosition(playerNum, pieceType);
    }



}
