import java.util.Random;

public class Board {

	private Square[][] squares; 
	private int rowCount;
	private int colCount; 
	
	public Board(int rowCount, int colCount) {
		this.rowCount = rowCount;
		this.colCount = colCount; 
		squares = new Square[colCount][rowCount];
		for(int y = 0; y < rowCount; y++) {
			for(int x = 0; x < colCount; x++) {
				Random rand = new Random();
				Square s;
				if(rand.nextInt(rowCount*colCount)%4 == 0) {
					s = new Square(true);
				}else {
					s = new Square(false);
				}
				squares[x][y] = s;
			}
		}
	}
	
	public void printBoard() {
		int aliveCount = 0;
		for(int y = 0; y < rowCount; y++) {
			for(int x = 0; x < colCount; x++) {
				if(getSquare(x,y).getIsAlive()) {
					System.out.print("X");
					aliveCount++;
				}else {
					System.out.print("O");
				}
			}
			System.out.print("\n");
		}
		System.out.print("Alive Count: ");
		System.out.print(aliveCount);
		System.out.print("\n");
		System.out.print("Total Count: ");
		System.out.print(rowCount*colCount);
		System.out.print("\n");
	}
	
	public Square getSquare(int x, int y) {
		return squares[x][y];
	}
	
	public void setSquare(int x, int y, Square s) {
		squares[x][y] = s;
	}

	public int getRowCount() {
		return rowCount;
	}
	
	public int getColCount() {
		return colCount;
	}
	
}
