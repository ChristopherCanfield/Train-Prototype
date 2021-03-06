package com.divergentthoughtsgames.rts.nav;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.divergentthoughtsgames.rts.App;
import com.divergentthoughtsgames.rts.util.Coords;

public class Node
{
	public static int SIZE = 24;
	public static int HALF_SIZE = SIZE / 2;
	
	public static final Color DEFAULT_COLOR = Color.DARK_GRAY;
	
	protected List<Edge> edges = new ArrayList<Edge>(8);
	
	private final int x;
	private final int y;
	
	private final Rectangle rect;
	
	private boolean passable;
	
	/**
	 * Constructs a navigation graph node.
	 * @param x the center x point of the node.
	 * @param y the center y point of the node.
	 */
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, SIZE, SIZE);
		this.passable = true;
	}
	
	/**
	 * Adds the specified edge, if it is not already connected to this node. 
	 * Also calls edge.addNode to add this edge to the node, if it doesn't already exist.
	 * @param edge the edge to connect to this node.
	 * @return reference to this node.
	 */
	public Node addEdge(Edge edge)
	{
		if (!edges.contains(edge))
		{
			edges.add(edge);
			edge.addNode(this);
		}
		return this;
	}
	
	public List<Edge> getEdges()
	{
		return edges;
	}
	
	/**
	 * Returns the center x value of the node.
	 * @return the center x value of the node.
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Returns the center y value of the node.
	 * @return the center y value of the node.
	 */
	public int getY()
	{
		return y;
	}
	
	public int getCenterX()
	{
		return x + HALF_SIZE;
	}
	
	public int getCenterY()
	{
		return y + HALF_SIZE;
	}
	
	public boolean overlaps(Rectangle otherRect)
	{
		return rect.overlaps(otherRect);
	}
	
	public int getRowIndex()
	{
		return y / SIZE;
	}
	
	public int getColumnIndex()
	{
		return x / SIZE;
	}
	
	/**
	 * Sets this node, and all connected edges, to passable (if true) or impassable (if false).
	 * @param val
	 */
	public void setPassable(boolean val)
	{
		if (val != passable)
		{
			for (final Edge edge : edges)
			{
				edge.setPassable(val);
			}
			passable = val;
		}
	}
	
	public boolean isPassable()
	{
		return passable;
	}

	public void draw(ShapeRenderer renderer)
	{
		Color color = passable ? DEFAULT_COLOR : Color.RED;
		draw(renderer, color);
	}
	
	public void draw(ShapeRenderer renderer, Color color)
	{
		Vector3 screen = Coords.worldToScreen(getX(), getY());
		renderer.setColor(color);
		float zoom = App.graphics.getZoom();
		renderer.circle(screen.x + HALF_SIZE / zoom, screen.y + HALF_SIZE / zoom, 5 / zoom);
		renderer.rect(screen.x, screen.y, rect.width / zoom, rect.height / zoom);
		for (final Edge edge : edges)
		{
			edge.draw(renderer);
		}
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Node))
		{
			return false;
		}
		Node otherNode = (Node)other;
		return (x == otherNode.x &&
				y == otherNode.y);
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(x, y);
	}
	
	@Override
	public String toString()
	{
		return "(" + x + "," + y + ")";
	}
}
