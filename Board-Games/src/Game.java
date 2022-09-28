import GameEntity.GameOperation;
import GameEntity.GameType;
import GameEntity.OrderChaos.OCGameOperation;
import GameEntity.TicTacToe.TTTGameOperation;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        while(true){
            System.out.println("Welcome to board game");
            System.out.println("Please enter the game number to select game");
            System.out.println("1.Tic Tac Toe");
            System.out.println("2.Order & Chaos");
            System.out.println("8.Exit");
            Scanner scanner = new Scanner(System.in);
            int gameNum = scanner.nextInt();
            switch (gameNum){
                case 1:
                    new TTTGameOperation(GameType.TicTacToe.getGameType());
                    break;
                case 2:
                    new OCGameOperation(GameType.OrderChaos.getGameType(), 6,6);
                    break;
                case 8:
                    System.out.println("Good Bey!");
                    return;
                default:
                    System.out.println();
            }
        }
    }

    public void exit(){

    }
}
