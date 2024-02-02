/**
 * HONOR PLEDGE: All work here is honestly obtained and is my own. Signed:  Aman Haileyesus
 * @author haileyesusa
 * Date of Completion:  eg. 10/17/2022
 * Assignment:  Ch09 GameofLife Project
 * 
 * Attribution: Will Doster, Leah Dorrien, Becca Jose
 * 
 * General Description: Creates the functionality and employs the logic of Game of Life into the grid using 2d arrays
 * 
 * 
 * Advanced:  The array wraps around the left and right of the array to consider neighbors allowing for travel around
 * 
 * Errata:  I could not get the array to add the neighbors of the array when wrapping around the edge.
 * 			Instead, it acts as though there was no wrapping.
 * 			I tried with multiple different versions and variants but could not get the value to be added
 * 			, possibly because of a one-off error in my logic
 *
 */
public class LifeGrid {

	private int[][] grid;
	private final int DEAD = 0;
	private final int ALIVE = 1;

	public LifeGrid(int rows, int cols) {
		grid = new int[rows][cols];
	}

	public int getCell(int rows, int cols) {
		return grid[rows][cols];
	}

	public void setCell(int rows, int cols, int fill) {
		grid[rows][cols] = fill;

	}
	/**
	 * Evolves the current generation by one, applying the logic of the neighbors to each cell.
	 */
	public void evolve() {
		//Create a TEMP world with the same dimensions 
		int[][] temp = new int[grid.length][grid[0].length];

		//Visit every cell of the grid. ROW MAJOR order...
		for (int r = 0; r<grid.length; r++) {
			for (int c =0; c<grid[0].length; c++) {
				int count = countNeighbors(r, c);

				//if cell is currently dead 
				if (grid[r][c] == 0) {
					if (count == 3) {
						temp[r][c] = 1;
					}
					else 
						temp[r][c] = 0;
				}
				//if cell is currently alive
				if (grid[r][c] == 1) {
					if (count < 2 || count > 3) {
						temp[r][c] = 0;
					}
					else {
						temp[r][c] = 1;
					}
				}
			}
		}
		grid = temp;

	}
	/**
	 * This method counts the number of live neighbors
	 * for position (r,c) in the grid.
	 * (There are 8 possible neighbors, including the
	 * diagonals.)
	 * @param r
	 * @param c
	 * @return
	 */
	private int countNeighbors(int r, int c) {
		int sum = 0;	
		for (int i = r-1; i<=r+1; i++) { //loop around 3x3 grid of element
			for (int j =c-1; j<=c+1; j++) { 
				if (i>=0 && i<grid.length)
					
					if (j>=0 && j<grid[i].length){
						sum+=grid[i][j]; //add value of all elements in 3x3, subtracting value of element at end
					}
					else if (j<0) {
						sum+=grid[i][grid[i].length-1];
					}
					else if (j==grid[0].length) {
						sum+=grid[i][0];
					}
			}
		}




		return sum-grid[r][c];
	}

	public int getNumCols() {
		return grid.length;
	}

	public int getNumRows() {
		return grid[0].length;
	}

	/**
	 * This method counts the number of live neighbors
	 * for position (r,c) in the grid.
	 * (There are 8 possible neighbors, including the
	 * diagonals.)
	 * @param r
	 * @param c
	 * @return
	 */
	private int countNeighbors2(int r, int c) {
		int sum = 0;	
		//Loop around element in a 3x3 grid 

		for (int i = r-1; i<=r+1; i++) { 

			//Check if at first or last column and wrap around 
			if (i>=0 && i<grid.length) {




				if (c == 0) { //if at left column
					sum+=grid[i][grid[0].length-1]; 
				}
				else if (c == grid[0].length-1) { //if at right column
					sum+=grid[i][0];
				}
			}






			//If not in first or last 
			for (int j =c-1; j<=c+1; j++) { 

				if (!(i==r && j==c) && i>=0 && j>=0 && i<grid.length && j<grid[i].length){ //check if at center or if at edge
					sum+=grid[i][j]; 
				}

			}
		}
		return sum;
	}


}
