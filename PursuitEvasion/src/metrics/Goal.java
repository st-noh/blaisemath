/*
 * Goal.java
 * Created on Aug 28, 2007, 10:26:56 AM
 */

package metrics;

import java.util.Vector;
import sequor.model.DoubleRangeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;
import scio.function.Function;
import sequor.Settings;
import sequor.model.StringRangeModel;
import sequor.SettingsProperty;
import simulation.Team;
import simulation.Agent;
import tasking.TaskGenerator;
import utility.DistanceTable;

/**
 * Represents a team level goal. Such a goal is measured by whether a value function
 * between two teams reaches a particular threshhold. For more specialized types of
 * goals, implement a child-class of this one.
 * <br><br>
 * All settings are encapsulated within a subclass GoalSettings.
 * <br><br>
 * @author Elisha Peterson
 */
public class Goal extends TaskGenerator {
        
    // PROPERTIES
    
    /** Static settings. */
    public GoalSettings gs;    
    /** Tasking class. */
    private TaskGenerator tasker;
    /** Whether or not the goal has been achieved. */
    private boolean achieved=false;    
    /** The agent which achieved the goal. */
    private Agent which=null;    
    
    
    // CONSTANTS
    
    public static final int SEEK=0;
    public static final int CAPTURE=1;
    public static final int FLEE=2;
    public static final String[] TYPE_STRINGS={"Seek","Capture","Flee"};
    
    
    // CONSTRUCTORS & INITIALIZERS   
    
    /** 
     * Default initializer requires several parameters.
     * @param weight    a constant factor (between 0 and 1) associated with the goal
     * @param owner     the team whose goal this is
     * @param target    the target of this goal
     * @param type      whether this is a "SEEK" goal or an "EVADE" goal
     * @param tasking   the type of tasking associated with this goal
     * @param thresh    specifies at what point the goal has been reached... sometimes a more generic parameter
     */
    public Goal(double weight,Team owner,Team target,int type,int tasking,double thresh){
        super(target,type);
        gs=new GoalSettings(weight,owner,target,type,tasking,thresh);
        tasker=gs.getTasking();
        addActionListener(owner);
    }
        
    /**
     * Resets the goal after each simulation is run.
     */
    public void reset(){
        achieved=false;
        which=null;
    }
    
    /**
     * Assigns tasks to team based upon the tasking algorithm.
     */
    public void assignTasks(DistanceTable table){
        if(tasker!=null){
            tasker.generate(gs.owner, table, getWeight());
        }
    }
    
    /** 
     * String representation of the goal.
     */
    @Override
    public String toString(){return gs.toString();}        
    
    
    // IMPLEMENT BASIC FUNCTIONALITY OF THE GOAL   
    
//    /** 
//     * Measures closeness to obtaining this particular goal. This is measured by the distance to
//     * close (or open up) between players in order for the goal to be reached.
//     * @param d     the global table of distances
//     * @return      numeric value indicating the closeness to the goal (positive if goal has been reached, otherwise negative) 
//     */
//    public Double getValue(DistanceTable d){
//        AgentPair result=d.min(getOwner().getActiveValueAgents(),getTarget().getActiveAgents());
//        if(result==null){return 0.0;}
//        double dist=getThreshhold()-result.getDistance();
//        if(dist>0&&!achieved){which=result.getFirst();}
//        return dist;
//    }    
    
//    /** 
//     * Returns whether or not goal has been achieved
//     * @param d     the global table of distances
//     * @return      true if goal achieved, false otherwise 
//     */
//    public boolean justAchieved(DistanceTable d){
//        if(!achieved&&getValue(d)>0){
//            achieved=true;
//            return true;
//        }
//        return false;
//    }  

    @Override
    public void generate(Vector<Agent> team, DistanceTable table, double priority) {
        tasker.generate(team, table, priority*getWeight());
    }
    
    
    // BEAN PATTERNS  
    
    public int getType(){return gs.type.getValue();}
    public double getWeight(){return gs.weight.getValue();}
    public double getThreshhold(){return gs.threshhold.getValue();}
    public Team getOwner(){return gs.owner;}
    public Team getTarget(){return gs.target;}
    public boolean isAchieved(){return achieved;}
    public Agent getAgent(){return which;}
 
    public void setType(int newValue){gs.type.setValue(newValue);}
    public void setWeight(double newValue){gs.weight.setValue(newValue);}
    public void setThreshhold(double newValue){gs.threshhold.setValue(newValue);}
    public void setOwner(Team newValue){gs.setOwner(newValue);}
    public void setTarget(Team newValue){gs.setTarget(newValue);}
    
    public JPanel getPanel(){return gs.getPanel();}
    
    
    // EVENT HANDLING
    
    boolean editing;
    // Remaining code deals with action listening
    protected EventListenerList listenerList = new EventListenerList();
    public void addActionListener(ActionListener l){listenerList.add(ActionListener.class, l);}
    public void removeActionListener(ActionListener l){listenerList.remove(ActionListener.class, l);}
    protected void fireActionPerformed(String s){
        if(editing){return;}
        Object[] listeners=listenerList.getListenerList();
        for(int i=listeners.length-2;i>=0;i-=2){
            if(listeners[i]==ActionListener.class){
                ((ActionListener)listeners[i+1]).actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,s));
            }
        }
    }
    
    
    // SUB-CLASS GoalSettings
    
    /** Wrapper for the static settings of the goal. */
    private class GoalSettings extends Settings {
        
        /** The team owning this goal. */
        private Team owner;  
        /** The target of this goal. */
        private Team target;
    
        /** Specifies the weighting/priority of the goal. */
        private DoubleRangeModel weight=new DoubleRangeModel(1,0,1,.01);
        /** Specifies whether goal is pursuit (0) or evade (1). */
        private StringRangeModel type=new StringRangeModel(TYPE_STRINGS,SEEK,0,2);
        /** 
         * Another parameter to use in specifying the goal. Usually the "target"
         * distance required to reach the goal.
         */
        private DoubleRangeModel threshhold=new DoubleRangeModel(1,0,1000,.1);

        /** The team's tasking algorithm default */
        private StringRangeModel tasking=new StringRangeModel(
                TaskGenerator.TASKING_STRINGS, TaskGenerator.AUTO_CLOSEST, TaskGenerator.FIRST, TaskGenerator.LAST);

        /** Specifies the function describing whether the goal has been achieved. */
        private Function<DistanceTable,Double> value;
        
        public GoalSettings(double weight,Team owner,Team target,int type,int tasking,double threshhold){
            setName("Tasking");
            this.weight.setValue(weight);
            this.owner=owner;
            this.target=target;
            this.type.setValue(type);
            this.tasking.setValue(tasking);
            this.threshhold.setValue(threshhold);
            add(new SettingsProperty("Goal Type",this.type,Settings.EDIT_COMBO));
            add(new SettingsProperty("Goal Weight",this.weight,Settings.EDIT_DOUBLE));
            add(new SettingsProperty("Goal Cutoff",this.threshhold,Settings.EDIT_DOUBLE));
            add(new SettingsProperty("Tasking",this.tasking,Settings.EDIT_COMBO));
        }
        
        /** Listens for changes to settings */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String ac=null;
            if(evt.getSource()==weight){                                    ac="teamAgentsChange";
            } else if(evt.getSource()==type){                               ac="teamSetupChange";
            } else if(evt.getSource()==threshhold){                         ac="teamSetupChange";
            } else if(evt.getSource()==tasking){    tasker=getTasking();    ac="teamSetupChange";}
            fireActionPerformed(ac);
        }
        
        private TaskGenerator getTasking(){
            return TaskGenerator.getTasking(target, type.getValue(), tasking.getValue());
        }
        public void setOwner(Team newValue){
            if(newValue!=null&&!newValue.equals(owner)){
                owner=newValue;
                pcs.firePropertyChange("goalOwner",null,newValue);
            }
        }
        public void setTarget(Team newValue){
            if(newValue!=null&&!newValue.equals(target)){
                target=newValue;
                pcs.firePropertyChange("goalTarget",null,newValue);
            }
        }

    } // SUB-CLASS GoalSettings
}
