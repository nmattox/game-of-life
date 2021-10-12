import java.awt.*;    
import javax.swing.*;    

public class Game {

	// maintains game state and history
	// array of all the past boards, plus current board 
	// holds onto the current round number 
	// has a function for playing a round 
	// can load the game into a UI 
	private Board gameHistory[];
	private int currentRound;
	private int numOfRounds;
	private int gameRowCount; 
	private int gameColCount;
	private JFrame gameUI;
	
	public Game(int gameRows, int gameCols, int numOfRounds) {
		this.numOfRounds = numOfRounds;
		this.currentRound = 0;
		this.gameRowCount = gameRows;
		this.gameColCount = gameCols;
		this.gameHistory = new Board[numOfRounds];
		Board initialBoard = new Board(gameRows, gameCols);
		this.gameHistory[this.currentRound] = initialBoard;
		this.gameUI = new JFrame();
		gameUI.setLayout(new GridLayout(gameRows,gameCols));    
		gameUI.setSize(1000, 1000); 
		this.updateUI();
		//initialBoard.printBoard();
	}
	
	public void startGame() {
		for(int i = 1; i < numOfRounds; i++) {
			currentRound++;
			gameHistory[i] = this.playRound();
			//gameHistory[i].printBoard();
			this.updateUI();
		}
	}
	
	public Board playRound() {
		Board updatedBoard = new Board(gameHistory[currentRound-1].getRowCount(),gameHistory[currentRound-1].getColCount());
		for(int y = 0; y < this.gameRowCount; y++) {
			for(int x = 0; x < this.gameColCount; x++) {
				int aliveCount = 0;
				
				// check left
				if(checkIfAlive(x-1, y)) {
					aliveCount++;
				}
				
				// check left-up
				if(checkIfAlive(x-1, y+1)) {
					aliveCount++;
				}
				
				// check up
				if(checkIfAlive(x, y+1)) {
					aliveCount++;
				}
				
				// check up-right
				if(checkIfAlive(x+1, y+1)) {
					aliveCount++;
				}
				
				// check right
				if(checkIfAlive(x+1, y)) {
					aliveCount++;
				}
				
				// check right-down
				if(checkIfAlive(x+1, y-1)) {
					aliveCount++;
				}
				
				// check down
				if(checkIfAlive(x, y-1)) {
					aliveCount++;
				}
				
				// check down-left
				if(checkIfAlive(x-1, y-1)) {
					aliveCount++;
				}
				
				if(gameHistory[currentRound-1].getSquare(x, y).getIsAlive()) {
					if(aliveCount == 2 || aliveCount == 3) {
						updatedBoard.setSquare(x, y, new Square(true));
					}else {
						updatedBoard.setSquare(x, y, new Square(false));
					}
				}else {
					if(aliveCount == 3) {
						updatedBoard.setSquare(x, y, new Square(true));
					}else {
						updatedBoard.setSquare(x, y, new Square(false));
					}
				}
			}
		}
		return updatedBoard;
	}
	
	public boolean checkIfAlive(int x, int y) {
		if(x < 0 || x > gameColCount-1 || y < 0 || y > gameRowCount-1) {
			return false; 
		}else {
			return gameHistory[currentRound - 1].getSquare(x, y).getIsAlive();
		}
	}
	
	public void updateUI() {
		gameUI.getContentPane().removeAll();
		for(int y = 0; y < gameRowCount; y++) {
			for(int x = 0; x < gameColCount; x++) {
				JPanel uiSquare = new JPanel();
				if(gameHistory[currentRound].getSquare(x, y).getIsAlive()) {
					uiSquare.setBackground(Color.BLACK);
				}else {
					uiSquare.setBackground(Color.white);
				}
				gameUI.add(uiSquare);
			}
		}
		gameUI.repaint();
		gameUI.setVisible(true);    
	}
}
