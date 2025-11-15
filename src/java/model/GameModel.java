package java.model;

public class GameModel {
    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";
    public static final String EMPTY = "";
    private static String currentPlayer;
    private String[][] board;
    private boolean gameOver;
    private int xWins;
    private int oWins;

    public int getXWins(){return xWins;}
    public int getOWins(){return oWins;}
    public boolean isGameOver(){return gameOver;}
    public String getCell(int row, int col){return board[row][col];}
    public String getCurrentPlayer(){return currentPlayer;}

    public GameModel(){
        board = new String[3][3];
        xWins = 0; oWins = 0;

        resetGame();
    }

    public void resetGame(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
            {board[i][j] = EMPTY;}
        }
        currentPlayer = PLAYER_X; //by default PLAYER_X moves first
        gameOver = false;
    }

    public boolean makeMove(int row, int col){
        if(gameOver || !board[row][col].equals(EMPTY)){return false;}
        else{board[row][col] = currentPlayer;return true;}
    }

    public void switchPlayer(){
        currentPlayer = currentPlayer.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public void endGame(boolean isWin){
        gameOver = true;
        if(isWin){if (currentPlayer.equals(PLAYER_X)){xWins++;}else{oWins++;}}
    }

    public boolean checkWin(){
        //rows check
        for (int i = 0; i < board.length; i++){
            if (
                    board[i][0].equals(currentPlayer) &&
                    board[i][1].equals(currentPlayer) &&
                    board[i][2].equals(currentPlayer)
            ) {return true;}
        }
        //columns check
        for (int j = 0; j < board.length; j++){
            if (
                    board[0][j].equals(currentPlayer) &&
                    board[1][j].equals(currentPlayer) &&
                    board[2][j].equals(currentPlayer)
            ) {return true;}
        }
        //diagonals check
            if (
                    board[0][0].equals(currentPlayer) &&
                    board[1][1].equals(currentPlayer) &&
                    board[2][2].equals(currentPlayer)
            ) {return true;}
            if (
                    board[0][2].equals(currentPlayer) &&
                    board[1][1].equals(currentPlayer) &&
                    board[2][0].equals(currentPlayer)
            ) {return true;}
        return false;
    }

    public boolean checkDraw(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (!board[i][j].equals(EMPTY)){
                    return true;
                }
            }
        }
        return false;
    }
}
