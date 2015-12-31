package com.brycemacinnis.bearwithme;

public class Node {
	public int x;
	public int y;
	
	public int fValue;
	public int gValue;
	public int hValue;
	
	public Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isCollider() {
		if (Map.tiles[x][y].isCollider)
			return true;
		else
			return false;
	}
}
