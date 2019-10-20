package checkersresit.controllers;


import checkersresit.views.BoardAndCommandBox;



public class GameController {
    private CommandBoxController commandBoxController;
    private HistoryController historyController;
    private BoardAndCommandBox boardAndCommandBox;
    private BoardController boardController;
    private String player1, player2;
    private int currentPlayer;
    private int whiteCheckers;
    private int blackCheckers;
    String[] possibleJumps = new String[4];
    int jumped = 0;

    public GameController(double checkerRadius, String player1, String player2, BoardAndCommandBox boardAndCommandBox) {
        historyController = new HistoryController();
        this.player1 = player1;
        this.player2 = player2;
        this.boardAndCommandBox = boardAndCommandBox;
        commandBoxController = new CommandBoxController(this);
        boardController = new BoardController(checkerRadius, this, boardAndCommandBox);
        currentPlayer = 0;





        historyController.addToHistory("---------------------");
        historyController.addToHistory("**    Checkers     **");
        historyController.addToHistory("---------------------");
        historyController.addToHistory("BLUE = White King | RED = Black King");
        historyController.addToHistory("Commands Available");
        historyController.addToHistory(" - start ");
        historyController.addToHistory(" - restart ");
        historyController.addToHistory(" - move \nEx. move B6 A5");
        historyController.addToHistory(" - move \nEx. move B6 D4 for jumps");
        historyController.addToHistory(" - quit (TO EXIT) ");
        historyController.addToHistory("----------------");
        historyController.addToHistory("To start a game enter \"Start\" ");
    }

    public HistoryController getHistoryController() {
        return historyController;
    }

    public CommandBoxController CommandBoxController() { return commandBoxController; }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) { this.currentPlayer = currentPlayer; }

    public int getWhiteCheckers() { return this.whiteCheckers; }

    public void setWhiteCheckers(int whiteCheckers) { this.whiteCheckers = whiteCheckers; }

    public int getBlackCheckers() { return  this.blackCheckers; }

    public void setBlackCheckers(int blackCheckers) { this.blackCheckers = blackCheckers; }

    public void setPossJumps(String[] possJumps) { this.possibleJumps = possJumps; }

    public int getJumped() { return this.jumped; }

    public void setJumped(int jumped) { this.jumped = jumped; }

    public void checkCommand(String inCommand) {
        String command = inCommand.toLowerCase();

        if (command.equals("quit")) {
            System.exit(0);
        } else if (command.equals("start")) {
            if(checkWinner() != 2) {
                boardController.setup();
                this.whiteCheckers = 12;
                this.blackCheckers = 12;
                historyController.addToHistory("Board initialised. Player 1, make your move.");
            } else { historyController.addToHistory("A game is already in progress."); }
        } else if (command.equals("restart")) {
            if(this.checkWinner() != 2) {
                boardController.clear();
                this.whiteCheckers = 0;
                this.blackCheckers = 0;
                boardController.setup();
                this.whiteCheckers = 12;
                this.blackCheckers = 12;
                historyController.addToHistory("Board initialised. Player 1, make your move.");
            } else { historyController.addToHistory("This command can only be used when the game is finished."); }
        } else if (command.contains("move")) {
            String[] move = command.split(" ");
            historyController.addToHistory(boardController.moveChecker(move[1], move[2], currentPlayer));
            if(checkWinner() == 1) {
                historyController.addToHistory("Player 2 Wins!");
            } else if(checkWinner() == 0) { historyController.addToHistory("Player 1 Wins!"); }
        } else if (command.equals("1") || command.equals("2") || command.equals("3")) {
            int commandInt = Integer.parseInt(command) - 1;
            String[] jump = this.possibleJumps[commandInt].split(" ");
            historyController.addToHistory(boardController.moveChecker(jump[0], jump[1], currentPlayer));
        }
        else {
            historyController.addToHistory("Invalid Command: " + command);
        }
    }

    public int checkWinner() {
        if(this.whiteCheckers == 0) {
            return 1;
        } else if(this.blackCheckers == 0) {
            return 0;
        }
        return 2;
    }
}
