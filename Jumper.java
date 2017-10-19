import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

/**
 *  A <code>Jumper</code> is an actor that will jump over Rocks and Flowers and
 *  turn. A jumper can remove another jumper by jumping on them.
 * 
 *  @author  TODO Your Name(s)
 *  @version TODO Date
 *  @author  Period: TODO
 *  @author  Assignment: GridWorld_Part3_Jumper
 * 
 *  @author  Sources: TODO
 */
public class Jumper extends Bug implements Edible
{
    public void act()
    {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        Actor destination = gr.get( next );
        if (canMove()) {
            move();
            move();
            if (destination instanceof Edible) {
                destination.removeSelfFromGrid();
            }
        }
        else {
            turn();
        }
    }
  
    
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor destination = gr.get( next );
        
        return (destination == null) || (destination instanceof Edible);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }
}