/*
 * CenterOfMass.java
 * Created on Sep 4, 2007, 2:17:30 PM
 */

package behavior.autonomy;

import simulation.Agent;
import simulation.Team;
import java.util.ArrayList;
import behavior.Goal;
import utility.DistanceTable;

/**
 * Player seeks/flees the center-of-mass of the enemy team.
 * <br><br>
 * @author Elisha Peterson
 */
public class CenterOfMass extends Autonomy {

    public CenterOfMass(){}
        
    /** Performs tasking based on a preset goal.
     * @param team the team to assign tasks to
     * @param goal the goal used for task assignment */
    public void assign(ArrayList<Agent>  team,Goal goal){
        Agent bCOM=new Agent();
        bCOM.setPoint(goal.getTarget().getCenterOfMass());
        for(Agent p:team){p.assignTask(bCOM,goal.getType());}
    }
}