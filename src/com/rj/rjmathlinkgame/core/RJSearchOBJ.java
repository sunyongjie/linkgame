package com.rj.rjmathlinkgame.core;

import android.graphics.Point;

public class RJSearchOBJ implements Comparable<RJSearchOBJ>{
	public static final int MAX_CHANGE = 2;
	public static final int FACTOR = 4;
	public static final int EXTEND_FACTOR = 100;
	enum Direction{
		LEFT,
		RIGHT,
		UP,
		DOWN
	};

	private RJSearchOBJ parent = null;
	private Point current;
	private Point source;
	private Point target;
	private int changeDirections = 0;
	private int gSum = 0;
	private Direction dire = null;
	
	public Direction getDire() {
		return dire;
	}

	public void setDire(Direction dire) {
		this.dire = dire;
	}

	public RJSearchOBJ(RJSearchOBJ father, Point cur, Point from, Point to) {
		parent = father;
		current = cur;
		source = from;
		target = to;
	}
	
	public void setGSum(int g) {
		gSum = g;
	}
	
	public int getGSum() {
		return gSum;
	}
	
	
	public RJSearchOBJ getParent() {
		return parent;
	}

	public void setParent(RJSearchOBJ parent) {
		this.parent = parent;
	}

	public Point getCurrent() {
		return current;
	}

	public void setCurrent(Point current) {
		this.current = current;
	}

	public int getChangeDirections() {
		return changeDirections;
	}

	public void setChangeDirections(int changeDirections) {
		this.changeDirections = changeDirections;
	}
	
	/**
	 * distance from source to current
	 * @param obj
	 * @return
	 */
	private int gFun() {
		int distance = Math.abs(current.x - source.x);
		distance += Math.abs(current.y - source.y);
		
		if (changeDirections > 0 ) {
			if (changeDirections <= MAX_CHANGE) 
				distance = FACTOR * changeDirections;
			else
				distance = EXTEND_FACTOR * changeDirections;
		}
		return distance;
	}
	
	/**
	 * distance from current to the target
	 * @param obj
	 * @return
	 */
	private int hFun() {
		int distance = Math.abs(current.x - target.x);
		distance += Math.abs(current.y - target.y);
		int willchange = 0;
		if (current.x != target.x && current.y != target.y) {
			willchange ++;
		}
		willchange += changeDirections; 
		if (willchange > 0 ) {
			if (willchange <= MAX_CHANGE) 
				distance = FACTOR * willchange;
			else
				distance = EXTEND_FACTOR * willchange;
		}
		return distance;
	}
	
	/**
	 * fFun for A* search 
	 * @param obj
	 * @return
	 */
	private int fFun() {
		return gFun() + hFun();
	}
	
	@Override
	public int compareTo(RJSearchOBJ another) {
		return (fFun() - another.fFun());
	}

	public Point getSource() {
		return source;
	}

	public void setSource(Point source) {
		this.source = source;
	}

	public Point getTarget() {
		return target;
	}

	public void setTarget(Point target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object another) {
		return current.equals(((RJSearchOBJ)another).getCurrent());
	}

	public boolean isTarget() {
		return current.equals(target) && isValid();
	}

	public boolean isValid() {
		return (this.changeDirections <= MAX_CHANGE);
	}
	
}
