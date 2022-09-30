package GameEntity.OrderChaos;

import GameEntity.*;

import java.util.Scanner;

public class OCGameOperation extends GameOperation {

    private int orderPlayerTeam;
    private int chaoPlayerTeam;

    public final static int WinRequireNum =5;

    public OCGameOperation(int gameType, int boardSize) {
        super(gameType, boardSize);
    }

    @Override
    public void enterGame() {
        System.out.println("Welcome to Order & Chaos");
        super.enterGame();
    }
    public void selectCharacterForPlayer(){
        boolean valid;
        do {
            valid = true;
            System.out.println("Please enter the team number for playing Order character");
            try{
                int num = Integer.parseInt(new Scanner(System.in).nextLine());
                if(num <= 0 || num > this.teamList.size()){
                    throw new RuntimeException();
                }
                orderPlayerTeam = num - 1;
                chaoPlayerTeam = num;
            }catch (RuntimeException e){
                System.out.println("Invalid input, please enter a valid number!");
                System.out.println();
                valid = false;
            }
        }while (!valid);
    }

    @Override
    public boolean checkEnd(Piece piece, int turn) {
        boolean flag = checkPieceCoherent(piece, WinRequireNum);
        if(flag){
            updateRecord(orderPlayerTeam);
            return true;
        }
        if(turn == boardSize * boardSize - 1){
            updateRecord(chaoPlayerTeam);
            return true;
        }
        return false;
    }

    // create new piece and check if entered position is valid
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
