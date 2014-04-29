//I'm not sure if this actually worked because i couldn't get grid world to run
/*1.)
	a.) turn
	b.) remove from board
	c.) turn
	d.) turn
	e.) just keep going
	f.) probably but i can’t think of any right now

2.)
	a.) bug
	b.) bug
	c.) it can use the same constructors as bug
	d.) canMove and move should be overridden
	e.) none
	f.) running the code and making it move around in all the cases in part 1
	*/




package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;


public class Jumper extends Bug
{

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location temp = loc.getAdjacentLocation(getDirection());
        Location next = temp.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
 
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location temp = loc.getAdjacentLocation(getDirection());
	Location next = temp.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}
