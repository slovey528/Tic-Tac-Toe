package ca.uwaterloo.tictactoe;

import java.util.Observable;

import android.util.Log;

public class Model extends Observable{
	private boolean gameStart;
	private boolean inGame;
    private boolean isInvalid;
    private boolean hasWinner;
    private boolean columWin;
    private boolean rowWin;
    private boolean crossWin;
    private boolean xWin;
    private boolean oWin;
    private boolean cleanBoard;
    private String xPlayer;
    private String oPlayer;
	private String curTurn;
	private String nextTurn;
	private String[] buttonText = {"", "", "", "", "", "", "", "", ""};
    private int curButton;
    private int moveCounter;
    private int winIndex;
    private int xWinCounter;
    private int oWinCounter;
    private int totalPlayed;
	
    Model() {
    	xPlayer = "Player 1";
    	oPlayer = "Player 2";
    	curTurn = "X";
    	moveCounter = 0;
    }
    
	public void newGame() {
		inGame = false;
		hasWinner = false;
		isInvalid = false;
		cleanBoard = true;
    	xWin = false;
    	oWin = false;
    	columWin = false;
    	rowWin = false;
    	crossWin = false;
    	nextTurn = curTurn;
    	curButton = -1;
    	moveCounter = 0;
    	xWinCounter = 0;
    	oWinCounter = 0;
    	totalPlayed = 0;
    	for (int i = 0; i < 9; i++) {
			buttonText[i] = "";
		}
    	setChanged();
		notifyObservers();
	}
	
	public void playAgain() {
		inGame = false;
		hasWinner = false;
		isInvalid = false;
		cleanBoard = true;
    	xWin = false;
    	oWin = false;
    	columWin = false;
    	rowWin = false;
    	crossWin = false;
    	curTurn = nextTurn;
    	curButton = -1;
    	moveCounter = 0;
    	for (int i = 0; i < 9; i++) {
			buttonText[i] = "";
		}
    	setChanged();
		notifyObservers();
	}

	public boolean isInGame() {
		return inGame;
	}
	
	public String getCurTurn() {
		return curTurn;
	}

	public void setNextTurn(String nextTurn) {
		this.nextTurn = nextTurn;
	}
	
	public int getMoveCounter() {
		return moveCounter;
	}

	public int getCurButton() {
		return curButton;
	}
	
	public void setCurButton(int curButton) {
		inGame = true;
		cleanBoard = false;
		this.curButton = curButton;
		curTurn = nextTurn;
		if (nextTurn == "X") nextTurn = "O";
		else nextTurn = "X";
		if (buttonText[this.curButton] != "") isInvalid = true;
		else {
			buttonText[this.curButton] = curTurn;
			moveCounter++;
		}
		setChanged();
		notifyObservers();
		
		checkColWin();
		if (!hasWinner) checkRowWin();
		if (!hasWinner) checkCrossWin();
		if (!hasWinner && moveCounter == 9) {
			totalPlayed++;
			inGame = false;
		}
		setChanged();
		notifyObservers();
		if (isInvalid) isInvalid = false;
	}

	private void checkColWin() {		
        for (int i = 0; i < 3; i++) {
            if (buttonText[i] == "X" || buttonText[i] == "O") {
                if (buttonText[i] ==  buttonText[i+3] && buttonText[i] ==  buttonText[i+6]) {
                    if (curTurn == "X") {
                    	xWin = true;
                    	xWinCounter++;
                    }
                    else {
                    	oWin = true;
                    	oWinCounter++;
                    }
                    totalPlayed++;
                    hasWinner = true;
                    columWin = true;
                    inGame = false;
                    winIndex = i;
                }
            }
        }
    }
	
	private void checkRowWin() {		
        for (int i = 0; i < 7; i+=3) {
            if (buttonText[i] == "X" || buttonText[i] == "O") {
                if (buttonText[i] ==  buttonText[i+1] && buttonText[i] ==  buttonText[i+2]) {
                	if (curTurn == "X") {
                    	xWin = true;
                    	xWinCounter++;
                    }
                    else {
                    	oWin = true;
                    	oWinCounter++;
                    }
                	totalPlayed++;
                    hasWinner = true;
                    rowWin = true;
                    inGame = false;
                    winIndex = i;
                }
            }
        }
    }
	
	private void checkCrossWin() {		
		if (buttonText[0] == "X" || buttonText[0] == "O") {
            if (buttonText[0] ==  buttonText[4] && buttonText[0] ==  buttonText[8]) {
            	if (curTurn == "X") {
                	xWin = true;
                	xWinCounter++;
                }
                else {
                	oWin = true;
                	oWinCounter++;
                }
            	totalPlayed++;
                hasWinner = true;
                crossWin = true;
                inGame = false;
                winIndex = 0;
            }
        }
		if (buttonText[2] == "X" || buttonText[2] == "O") {
            if (buttonText[2] ==  buttonText[4] && buttonText[2] ==  buttonText[6]) {
            	if (curTurn == "X") {
                	xWin = true;
                	xWinCounter++;
                }
                else {
                	oWin = true;
                	oWinCounter++;
                }
            	totalPlayed++;
                hasWinner = true;
                crossWin = true;
                inGame = false;
                winIndex = 2;
            }
        }
    }
	
	public int getWinIndex() {
		return winIndex;
	}

	public boolean isHasWinner() {
		return hasWinner;
	}

	public boolean isInvalid() {
		return isInvalid;
	}
	
	public boolean isColumWin() {
		return columWin;
	}

	public boolean isRowWin() {
		return rowWin;
	}

	public boolean isCrossWin() {
		return crossWin;
	}
	
	public boolean isxWin() {
		return xWin;
	}

	public boolean isoWin() {
		return oWin;
	}
	
	public boolean isCleanBoard() {
		return cleanBoard;
	}
	
	public void setCurTurn(String curTurn) {
		this.curTurn = curTurn;
	}
	
	public String getNextTurn() {
		return nextTurn;
	}
	
	public String getxPlayer() {
		return xPlayer;
	}

	public void setxPlayer(String xPlayer) {
		this.xPlayer = xPlayer;
	}

	public String getoPlayer() {
		return oPlayer;
	}

	public void setoPlayer(String oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	public int getxWinCounter() {
		return xWinCounter;
	}

	public int getoWinCounter() {
		return oWinCounter;
	}
	
	public int getTotalPlayed() {
		return totalPlayed;
	}

	@Override
	public void notifyObservers() {
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}
}
