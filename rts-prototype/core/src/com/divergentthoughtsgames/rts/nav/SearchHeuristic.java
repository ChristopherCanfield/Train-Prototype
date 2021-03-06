package com.divergentthoughtsgames.rts.nav;

/**
 * Interface for A* search heuristics.
 * @author Christopher D. Canfield
 */
public interface SearchHeuristic
{
	/**
	 * Calculates the distance cost between two nodes.
	 * @param start The start node.
	 * @param target The end node.
	 * @return
	 */
	public double cost(Node start, Node end);
}
