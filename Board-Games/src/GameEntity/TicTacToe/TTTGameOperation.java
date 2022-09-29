package GameEntity.TicTacToe;

import GameEntity.*;

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
        int teamNum = turn % 2 == 0 ? TeamOneIndex : TeamTwoIndex;
        boolean flag = checkPieceCoherent(piece, this.boardScaleCol);
        if(flag){
            updateRecord(teamNum);
            return true;
        }
        if(turn == boardScaleRow * boardScaleRow - 1){
            updateRecord(StalemateIndex);
            return true;
        }
        return false;
    }

    public Piece createPiece(int turn){
        printBoard();
        int playerNum = turn % 2 + 1;
        int pieceType;
        if(turn % 2 == 0){
            pieceType = PieceType.X_Type.getTypeNum();
        }else{
            pieceType = PieceType.O_Type.getTypeNum();
        }
        return validPosition(playerNum, pieceType);
    }

    @Override
    public void selectCharacterForPlayer() {

    }
}
