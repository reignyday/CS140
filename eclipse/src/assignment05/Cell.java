package assignment05;
/**
 * Individual Cell in the grid of the Game of Life. It has a state called
 * "alive" which indicates whether it should appear as a black square or a
 * white square in the graphic
 * @author CS 140
 */
public class Cell {
	private boolean alive;
	private int myRow;
	private int myCol;
	private Cell[] neighbors;
	/**
	 * Initialize the position and "alive" status of the Cell
	 * @param row the row location of this cell
	 * @param col the column location of this cell
	 */
	public Cell(int row, int col) {
		alive = false;
		myRow = row;
		myCol = col;
		neighbors = new Cell[8];
	}
	/**
	 * Called by CellGrid at the beginning of the simulation to set up the
	 * list of neighbors of each Cell
	 * @param cells
	 */
	public void populateNeighbors(Cell[][] cells) {
		int limitHoriz = cells[0].length;
		int limitVert = cells.length;
		if (myRow > 0 && myCol > 0) 
			neighbors[0] = cells[myRow-1][myCol-1];
// TODO		
// fill in neighbors[1] through neighbors[6]
		
		if (myRow < limitVert - 1 && myCol < limitHoriz - 1)
			neighbors[7] =cells[myRow+1][myCol+1];
	}
	/**
	 * Gets the "alive" state of this cell
	 * @return the alive value
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * Sets the "alive" status of this cell
	 * @param alive the value of alive to be set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	/**
	 * This is the key to the whole Game of Life. 
	 * Calculate the number of non-null and live neighbors, call that "count"
	 * If this cell is alive and "count" is 0,1,4,5,6,7,8 return false
	 *   (the cell dies of loneliness (count = 0,1) or dies of starvation 
	 *   (count = 4,5,6,7,8)
	 * If this cell is not alive and "count" is _exactly_ 3, return true
	 *   a new cell is born
	 * In all other cases return "alive" (i.e. the current state of the cell).
	 * @return true if this Cell will be alive in next generation
	 */
	public boolean aliveNextTime() {

// TODO
// fill in here
		
		return alive;
	}
}
