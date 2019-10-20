package checkersresit.controllers;


import checkersresit.views.BoardAndCommandBox;
import javafx.scene.paint.Color;


public class BoardController {
    private GameController gameController;
    private BoardAndCommandBox boardAndCommandBox;
    private double checkerRadius;

    public BoardController(double checkerRadius, GameController gameController, BoardAndCommandBox boardAndCommandBox) {
        this.checkerRadius = checkerRadius;
        this.gameController = gameController;
        this.boardAndCommandBox = boardAndCommandBox;
    }

    public void setup() {
        boardAndCommandBox.getSquare(1,5).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(3,5).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(5,5).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(7,5).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(0,6).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(2,6).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(4,6).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(6,6).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(1,7).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(3,7).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(5,7).addChecker(Color.WHITE, checkerRadius, 0);
        boardAndCommandBox.getSquare(7,7).addChecker(Color.WHITE, checkerRadius, 0);

        boardAndCommandBox.getSquare(0,0).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(2,0).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(4,0).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(6,0).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(1,1).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(3,1).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(5,1).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(7,1).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(0,2).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(2,2).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(4,2).addChecker(Color.BLACK, checkerRadius, 1);
        boardAndCommandBox.getSquare(6,2).addChecker(Color.BLACK, checkerRadius, 1);
        gameController.setCurrentPlayer(0);
    }

    public void clear() {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                boardAndCommandBox.getSquare(x, y).deleteChecker();
            }
        }
    }

    public String moveChecker(String src, String dest, int player) {
        char srcXChar = src.toLowerCase().charAt(0);
        int srcX = srcXChar - 97;
        int srcY = src.charAt(src.length() - 1) - 49;

        char destXChar = dest.toLowerCase().charAt(0);
        int destX = destXChar - 97;
        int destY = dest.charAt(dest.length() - 1) - 49;

        if(checkJumpMove(srcX, srcY, destX, destY, player)) {
            jumpChecker(srcX, srcY, destX, destY, player);
            if(gameController.getJumped() == 0) {
                if (gameController.checkWinner() != 2) {
                    return "Congratulations, Player " + player + "!";
                }
                String[] secondJumps = checkDoubleJump(destX, destY, destX, destY, player, srcX, srcY);
                String[] secondJumpsNoNull = new String[4];
                if (secondJumps[0] != null || secondJumps[1] != null || secondJumps[2] != null || secondJumps[3] != null) {
                    gameController.setCurrentPlayer(player);
                    gameController.getHistoryController().addToHistory("Possible double jumps:");
                    int j = 1;
                    int k = 0;
                    for (int i = 0; i < 4; i++) {
                        if (secondJumps[i] != null) {
                            secondJumpsNoNull[k] = secondJumps[i];
                            gameController.getHistoryController().addToHistory("" + j + ": " + secondJumpsNoNull[k].toUpperCase());
                            j++;
                            k++;
                        }
                    }
                    gameController.setPossJumps(secondJumpsNoNull);
                    gameController.setJumped(1);
                    return "Choose a second jump.";
                }
            }
            gameController.setJumped(0);
            return "Player " + (player+1) + "'s checker jumped from " + src.toUpperCase() + " to " + dest.toUpperCase() + ".";
        }
        if(!checkSlideMove(srcX, srcY, destX, destY, player)) {
            return "Invalid move!";
        }

        if(player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLACK) {
            if(destY == 7) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.RED, checkerRadius, 1);
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            } else {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLACK, checkerRadius, 1);
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            }
            gameController.setCurrentPlayer(0);
        } else if(player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.RED) {
            boardAndCommandBox.getSquare(destX, destY).addChecker(Color.RED, checkerRadius, 1);
            boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            gameController.setCurrentPlayer(0);
        }
        else {
            if(destY == 0) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLUE, checkerRadius, 1);
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            }
            else if(boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLUE) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLUE, checkerRadius, 1);
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            }
            else {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.WHITE, checkerRadius, 1);
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            }
            gameController.setCurrentPlayer(1);
        }
        return "Player " + (player+1) + "'s checker moved from " + src.toUpperCase() + " to " + dest.toUpperCase() + ".";
    }

    public void jumpChecker(int srcX, int srcY, int destX, int destY, int player) {
        //Black
        if (player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLACK) {
            if (destY == 7) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.RED, checkerRadius, 1);
                boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).deleteChecker();
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
                gameController.setWhiteCheckers(gameController.getWhiteCheckers() - 1);
            } else {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLACK, checkerRadius, 1);
                boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).deleteChecker();
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
                gameController.setWhiteCheckers(gameController.getWhiteCheckers() - 1);
            }
            gameController.setCurrentPlayer(0);
        }
        //Red
        else if (player ==1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.RED) {
            boardAndCommandBox.getSquare(destX, destY).addChecker(Color.RED, checkerRadius, 1);
            if(destY > srcY) {
                boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).deleteChecker();
            } else { boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).deleteChecker(); }
            boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
            gameController.setWhiteCheckers(gameController.getWhiteCheckers() - 1);
            gameController.setCurrentPlayer(0);
        }
        //White
        else if (player == 0 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.WHITE) {
            if (destY == 0) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLUE, checkerRadius, 1);
                boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).deleteChecker();
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
                gameController.setBlackCheckers(gameController.getBlackCheckers() - 1);
            } else {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.WHITE, checkerRadius, 1);
                boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).deleteChecker();
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
                gameController.setBlackCheckers(gameController.getBlackCheckers() - 1);
            }
            gameController.setCurrentPlayer(1);
        }
        //Blue
        else if (player == 0 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLUE) {
                boardAndCommandBox.getSquare(destX, destY).addChecker(Color.BLUE, checkerRadius, 1);
                if(destY > srcY) {
                    boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).deleteChecker();
                } else { boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).deleteChecker(); }
                boardAndCommandBox.getSquare(srcX, srcY).deleteChecker();
                gameController.setBlackCheckers(gameController.getBlackCheckers()-1);
                gameController.setCurrentPlayer(1);
        }
    }


    public boolean checkSlideMove(int srcX, int srcY, int destX, int destY, int player) {
        if(destX == srcX) {
            return false;
        }

        else if(destY == srcY) {
            return false;
        }
        //Checks for moving checkers that aren't yours
        else if((player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.WHITE) || (player == 0 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.RED)) {
            return false;
        }

        else if((player == 0 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLACK) || (player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLUE)) {
            return false;
        }
        //Checks for moving backwards with non-kinged pieces
        else if((player == 1 && boardAndCommandBox.getSquare(srcX, srcY).getColor() != Color.RED && destY < srcY) || (player == 0 && boardAndCommandBox.getSquare(srcX, srcY).getColor() != Color.BLUE && destY > srcY)) {
            return false;
        }
        //Checks for moving into an occupied space
        else if(boardAndCommandBox.getSquare(destX, destY).checkEmptySquare() == 1) {
            return false;
        }
        //Checks for moving from empty space
        else if(boardAndCommandBox.getSquare(srcX, srcY).checkEmptySquare() == 0) {
            return false;
        }

        else if(Math.abs(srcX - destX) != 1 || Math.abs(srcY - destY) != 1) {
            return false;
        }
        else { return true; }
    }

    public boolean checkJumpMove(int srcX, int srcY, int destX, int destY, int player) {
        if(boardAndCommandBox.getSquare(destX, destY).checkEmptySquare() == 1) {
            return false;
        }

        else if(Math.abs(srcX - destX) != 2 || Math.abs(srcY - destY) != 2) {
            return false;
        }

        else if(srcY != 7 && srcY != 0) {
            if ((srcY < destY && player == 1 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.WHITE && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.BLUE) || (srcY > destY && player == 0 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.BLACK && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.RED)) {
                return false;
            }
            else if ((srcY > destY && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.RED && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.WHITE && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.BLUE) || (srcY < destY && boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLUE && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.BLACK && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.RED)) {
                return false;
            }
        }
        else if (srcY != 0) {
            if(boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).checkEmptySquare() == 0 && (player == 1 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.WHITE && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.BLUE ) || (player == 0 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.BLACK && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY - 1).getColor() != Color.RED)) {
                return false;
            }
        }
        else if (boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).checkEmptySquare() == 0 && (player == 1 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.WHITE && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.BLUE ) || (player == 0 && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.BLACK && boardAndCommandBox.getSquare((srcX + destX) / 2, srcY + 1).getColor() != Color.RED)) {
            return false;
        }
        return true;
    }

    public String[] checkDoubleJump(int srcX, int srcY, int destX, int destY, int player, int origSrcX, int origSrcY) {
        String[] possibleJumps = new String[4];
        char destXChar;
        char srcXChar;
        if( (destX - 2 >= 0 && destY - 2 >= 0) && !(destX - 2 == origSrcX && destY - 2 == origSrcY) ) {
            if(boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLACK) {
                possibleJumps[0] = null;
            }
            else if (checkJumpMove(srcX, srcY, destX - 2, destY - 2, player)) {
                destXChar = (char)destX;
                destXChar += 95;

                 srcXChar = (char)srcX;
                 srcXChar += 97;
                 possibleJumps[0] = "" + srcXChar + (srcY + 1) + " " + destXChar + (destY - 1);
            }
        }
        if( (destX + 2 <= 7 && destY - 2 >= 0) && !(destX + 2 == origSrcX && destY - 2 == origSrcY) ) {
            if(boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.BLACK) {
                possibleJumps[1] = null;
            }
            else if(boardAndCommandBox.getSquare(srcX,srcY).getColor() != Color.BLACK && checkJumpMove(srcX, srcY, destX + 2, destY - 2, player)) {
                destXChar = (char)destX;
                destXChar += 99;

                srcXChar = (char)srcX;
                srcXChar += 97;
                possibleJumps[1] = "" + srcXChar + (srcY + 1) + " " + destXChar + (destY - 1);
            }
        }
        if( (destX - 2 >= 0 && destY + 2 <= 7) && !(destX - 2 == origSrcX && destY + 2 == origSrcY) ) {
            if(boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.WHITE) {
                possibleJumps[2] = null;
            }
            else if(checkJumpMove(srcX, srcY, destX - 2, destY + 2, player)) {
                destXChar = (char)destX;
                destXChar += 95;

                srcXChar = (char)srcX;
                srcXChar += 97;
                possibleJumps[2] = "" + srcXChar + (srcY + 1) + " " + destXChar + (destY + 3);
            }
        }
        if( (destX + 2 <= 7 && destY + 2 <= 7) && !(destX + 2 == origSrcX && destY + 2 == origSrcY) ) {
            if(boardAndCommandBox.getSquare(srcX, srcY).getColor() == Color.WHITE) {
                possibleJumps[3] = null;
            }
            else if(checkJumpMove(srcX, srcY, destX + 2, destY + 2, player)) {
                destXChar = (char)destX;
                destXChar += 99;

                srcXChar = (char)srcX;
                srcXChar += 97;
                possibleJumps[3] = "" + srcXChar + (srcY + 1) + " " + destXChar + (destY + 3);
            }
        }
        return possibleJumps;
    }
}
