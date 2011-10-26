/**
 * 
 */
package net.snake.shared;

import java.util.ArrayList;


/**
 * @author Tony.Benbrahim
 *
 */
public class Snake {
	
	private ArrayList<Cell> cells;
	
	public Snake(){
	}
	
	public Snake(final ArrayList<Cell> cells){
		this.cells=cells;
	}
	
	public ArrayList<Cell> getCells(){
		return cells;
	}

}
