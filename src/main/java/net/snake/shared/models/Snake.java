/**
 * 
 */
package net.snake.shared.models;

import java.util.ArrayList;

import net.snake.server.Pivot;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * @author Tony.Benbrahim
 *
 */
public class Snake implements IsSerializable{
	
	private ArrayList<Cell> cells=new ArrayList<Cell>();
	private final ArrayList<Pivot> pivots=new ArrayList<Pivot>();
	
	public Snake(){
	}
	
	public Snake(final ArrayList<Cell> cells){
		this.cells=cells;
	}
	
	public void addPivot(final Pivot pivot) {
		pivots.add(pivot);
	}

	public ArrayList<Cell> getCells(){
		return cells;
	}

}
