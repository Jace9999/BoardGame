package GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class GameOperation {

    protected List<Team> teamList;

    protected Player thisRoundWinner;

    protected int boardSize;

    protected Board board;

    protected int gameType;

    public static final int TeamOneIndex = 0;
    public static final int TeamTwoIndex = 1;

    public static final int StalemateIndex = 99;

    public GameOperation(int gameType){
        this.gameType = gameType;
        teamList = new ArrayList<>();
        enterGame();
    }

    // Constructor for board game which has fixed board size
    public GameOperation(int gameType, int boardSize){
        this.boardSize = boardSize;
        this.gameType = gameType;
        teamList = new ArrayList<>();
        enterGame();
    }

    public void enterGame(){
        createTeam(TeamOneIndex);
        createTeam(TeamTwoIndex);
        Gaming();
    }

    //used for create team and create team member in this team
    public void createTeam(int teamIndexNum){
        int teamNum = teamIndexNum + 1;
        System.out.println("Please enter the player name for team " + teamNum + ", end with /");
        Team team = new Team();
        List<Player> playerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String s ="";
        while(true){
            s = scanner.nextLine().trim();
            if(s== "" || s.length() == 0 ){
                continue;
            }
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

    public void Gaming() {
        boolean continueGame;
        do{
            selectRoundPlayer(TeamOneIndex);
            selectRoundPlayer(TeamTwoIndex);
            selectCharacterForPlayer();
            initialBoard();
            int turn = 0;
            boolean isEnd;
            do{
                // create a new piece, check for position availability
                Piece piece = createPiece(turn);
                // if it is valid, then check if is end;
                isEnd = this.placePiece(turn,piece);
                turn++;
            }while(!isEnd);
            printBoard();
            continueGame = continuePlayGame();
        }while(continueGame);
    }

    //selected player for this round game
    public void selectRoundPlayer(int teamIndexNum){
        int teamNum = teamIndexNum + 1;
        boolean valid;
        int playerIndex = 0;
        do {
            valid = true;
            System.out.println("Select player in team" + teamNum + " for this round game, Enter Player Index");
            int index = 1;
            for (Player player : teamList.get(teamIndexNum).getPlayerList()) {
                System.out.println(index + ". " + player.getName());
                index++;
            }
            Scanner scanner = new Scanner(System.in);
            try{
                playerIndex = Integer.parseInt(scanner.nextLine());
            }catch (RuntimeException e){
                System.out.println("Please enter a valid number !");
                System.out.println();
                valid = false;
                continue;
            }
            playerIndex = playerIndex - 1;
            if(playerIndex < 0 || playerIndex > teamList.get(teamIndexNum).getPlayerList().size() - 1){
                System.out.println("Please enter a valid number !");
                System.out.println();
                valid = false;
            }
        }while (!valid);
        this.teamList.get(teamIndexNum).setNextGamePlayer(playerIndex);
    }

    public boolean placePiece(int turn, Piece piece) {
        Unit[][] boardArray = this.board.getBoardArray();
        int row = piece.getRow();
        int col = piece.getCol();
        int type = piece.getType();
        String symbol = PieceType.getSymbolByIndex(type);
        boardArray[row][col].setSymbol(symbol);
        boardArray[row][col].setOccupied(true);
        this.board.setBoardArray(boardArray);
        return checkEnd(piece, turn);
    }

    public void printBattleRecord(){
        int teamNum = 1;
        for(Team team: this.teamList){
            System.out.println("The total win time of team " + teamNum  + " is " + team.getWinTime() + " time, and stalemate time is " + team.getStalemateTime());
            teamNum++;
            for(Player player: team.getPlayerList()){
                System.out.println("Player " + player.getName() + " has won " + player.getWinTimes() + " time, and stalemate time is " + player.getStalemateTime());
            }
        }
    }

    public void updateRecord(int teamNum){
        if(teamNum == StalemateIndex){
            System.out.println("This round game is stalemate.....");
            for(Team team : this.teamList){
                team.setStalemateTime(team.getStalemateTime() + 1);
                Player thisGamePlayer = team.getNextGamePlayer();
                thisGamePlayer.setStalemateTime(thisGamePlayer.getStalemateTime() + 1);
            }
        }else{
            Team winnerTeam = this.teamList.get(teamNum);
            this.thisRoundWinner = winnerTeam.getNextGamePlayer();
            winnerTeam.setWinTime(winnerTeam.getWinTime() + 1);
            Player winner = this.thisRoundWinner;
            winner.setWinTimes(winner.getWinTimes() + 1);
            System.out.println();
            System.out.println("Congratulate " + this.thisRoundWinner.getName() + " win the game!");
        }
    }

    public void setBoardSize(){
        Scanner scanner = new Scanner(System.in);
        int scaleSize = 0;
        boolean valid;
        do {
            valid = false;
            System.out.println("Please enter the scale for the game board, for example: 3 means 3x3, 6 means 6x6.");
            System.out.println("Maximum is 10 and minimum size is 3.");
            try{
                scaleSize = Integer.parseInt(scanner.nextLine());
                valid = true;
            }catch (RuntimeException e){
                System.out.println("Please enter a valid number !");
                System.out.println();
            }
            if(scaleSize < 3 || scaleSize > 10){
                valid = false;
            }
        }while (!valid);
        this.boardSize = scaleSize;

    }

    public boolean continuePlayGame(){
        Scanner scanner = new Scanner(System.in);
        boolean loop;
        do{
            System.out.println();
            System.out.println("Press 1 to continue play this game.");
            System.out.println("Press 2 to check battle record.");
            System.out.println("Press 8 to return to main menu.");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    return true;
                case "2":
                    printBattleRecord();
                    loop = true;
                    break;
                case "8":
                    return false;
                default:
                    loop = true;
                    System.out.println("Wrong input, please select again.");
            }
        }while(loop);
        return false;
    }

    public void printBoard(){
        Unit[][] boardArray = this.board.getBoardArray();
        for(int i=0;i<boardArray.length;i++){
            for (int j=0;j<boardArray[0].length;j++){
                System.out.print(boardArray[i][j].getSymbol());
                if(j < boardArray[0].length - 1){
                    System.out.print("|");
                }
            }
            System.out.println();
            for(int col=0;col<boardArray.length * 2;col++){
                System.out.print("-");
            }
            System.out.println();
        }
    }


    public boolean checkPieceCoherent(Piece piece, int requireNum){
        int col = piece.getCol();
        int row = piece.getRow();
        Unit[][] boardArray = this.board.getBoardArray();
        int type = piece.getType();
        int consecutive = 0;
        String curPieceType = PieceType.getSymbolByIndex(type);
        //check for same row
        for(int i=0;i<boardArray[0].length;i++){
            if(!boardArray[row][i].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == requireNum){
            return true;
        }
        consecutive = 0;
        //check for same colum
        for(int i=0;i<boardArray.length;i++){
            if(!boardArray[i][col].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == requireNum){
            return true;
        }
        consecutive = 0;
        // check for left diagonal
        for(int r = row,c = col;r>=0 && c>=0;r--,c--){
            if(!boardArray[r][c].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        for(int r = row,c = col;r< boardArray.length && c< boardArray[0].length;r++,c++){
            if(!boardArray[r][c].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == requireNum+1){
            return true;
        }
        consecutive = 0;
        //check for right diagonal
        for(int r = row,c = col;r>=0 && c < boardArray[0].length;r--,c++){
            if(!boardArray[r][c].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        for(int r = row, c = col;r<boardArray.length && c >=0;r++,c--){
            if(!boardArray[r][c].getSymbol().equals(curPieceType)){
                break;
            }else{
                consecutive++;
            }
        }
        if(consecutive == requireNum+1){
            return true;
        }
        return false;
    }

    public boolean verifyTypedPosition(int row, int col){
        if(row >= this.boardSize || row < 0|| col >= this.boardSize || col < 0){
            return false;
        }
        Unit[][] boardArray = this.board.getBoardArray();
        if(boardArray[row][col].getOccupied()){
            return false;
        }
        return true;
    }

    public Piece validPosition(int playerNum, int pieceType){
        int row = 0;
        int col = 0;
        boolean positionAvailability;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the position for Player " + playerNum);
            String position = scanner.nextLine();
            String[] split = position.split(",");
            try{
                // checked for invalid input format
                row = Integer.parseInt(split[0]);
                col = Integer.parseInt(split[1]);
                row = row-1;
                col = col-1;
            }catch (RuntimeException e){
                System.out.println("This position format is not correct, please type like \"1,1\"");
                positionAvailability = false;
                continue;
            }
            positionAvailability = verifyTypedPosition(row, col);
            if(!positionAvailability){
                System.out.println("This position is not available, please try again.");
            }
        }while(!positionAvailability);
        return new Piece(pieceType,row,col);
    }

    public void initialBoard(){
        this.board = new Board(this.boardSize);
        Unit[][] boardArray = new Unit[boardSize][boardSize];
        for(int i=0;i<boardArray.length;i++){
            for(int j=0;j<boardArray[0].length;j++){
                boardArray[i][j] = new Unit(" ", false);
            }
        }
        board.setBoardArray(boardArray);
    }

    public abstract Piece createPiece(int turn);

    public abstract boolean checkEnd(Piece piece, int turn);

    // some game require select a character before play the game, like Order & Chaos
    public abstract void selectCharacterForPlayer();

}
