package com.brycemacinnis.bearwithme;

public class Node {
	public int X;
	public int Y;
	
	public int destinationX;
	public int destinationY;
	
	public Node parent;
	public int cost;
	
	public Node(int originX, int originY, int destinationX, int destinationY) {
		this.X = originX;
		this.Y = originY;
		
		this.destinationX = destinationX;
		this.destinationY = destinationY;
		
		if (parent == null)
			cost = 10 + ManhattanDistance(X,Y, destinationX, destinationY);
		else
			cost = parent.cost + 10 + ManhattanDistance(X,Y, destinationX, destinationY);
	}
	
	public boolean isCollider() {
		if (Map.tiles[X][Y].isCollider)
			return true;
		else
			return false;
	}
	
	int ManhattanDistance(float originX, float originY, float destinationX, float destinationY) {
		return (int)(Math.abs(destinationX - originX) + Math.abs(destinationY - originY));
	}


}
