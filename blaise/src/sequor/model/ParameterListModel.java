/*
 * ParameterListModel.java
 * Created on Feb 19, 2008
 */

package sequor.model;

import java.beans.PropertyChangeEvent;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import scribo.tree.Variable;
import sequor.component.Settings;

/**
 * This class is a data model for a list of parameters (doubles). It maps Strings (or Variables) to DoubleRangeModel's which control their values,
 * and will fire ChangeEvents if any of the values is changed. It can be used in conjunction with ParameterPanel to allow for automatically controlling
 * parameter inputs to a function.
 * 
 * @author ae3263
 */
public class ParameterListModel extends FiresChangeEvents implements ChangeListener {
    TreeMap<Variable,DoubleRangeModel> values;
    
    public ParameterListModel(){
        values=new TreeMap<Variable,DoubleRangeModel>();
    }
    public ParameterListModel(TreeMap<Variable,DoubleRangeModel> values){
        this.values=new TreeMap<Variable,DoubleRangeModel>();
        for(Variable v:values.keySet()){
            this.values.put(v,values.get(v));
        }
    }
    
    
    // PARAMETER METHODS

    /** Adds a model with a particular range of values. */
    public void addModel(Variable v,DoubleRangeModel drm){
        if(drm!=null){
            values.put(v,drm);
            drm.addChangeListener(this);
            changeEvent=new ChangeEvent("add");
            fireStateChanged();
            changeEvent=null;
        }
    }

    /** Populates a list of parameters according to the specified values. */
    public TreeMap<Variable,Double> getParameterList(){
        TreeMap<Variable,Double> result=new TreeMap<Variable,Double>();
        for(Variable v:values.keySet()){
            result.put(v,values.get(v).getValue());
        }
        return result;
    }
    /** Sets a parameter value. */
    public boolean setParameterValue(Variable v,Double d){
        if(values.containsKey(v)){
            if(values.get(v).getValue()==d){
                return false;
            }
            values.get(v).setValue(d);
        } else {
            addModel(v, defaultDoubleRangeModel(d));
        }
        return true;
    }
    /** Sets a parameter value with a given string. */
    public boolean setParameterValue(String s,Double d){return setParameterValue(new Variable(s),d);}
    /** Removes a particular variable. */
    public void removeParameter(Variable v){
        if(values.containsKey(v)){
            values.get(v).removeChangeListener(this);
            values.remove(v);
        }
    }        
    
    
    // GUI INPUT/OUTPUTS
    
    /** Returns panel containing all the parameters. */
    public JPanel getPanel(){
        Settings s=new Settings();
        for(Variable v:values.keySet()){
            s.addProperty(v.toString(),values.get(v),Settings.EDIT_DOUBLE);
        }        
        s.addProperty("",this,Settings.EDIT_PARAMETER);
        return s.getPanel();
    }
    
    /** Updates a panel to contain the settings here. */
    public void updatePanel(JPanel p){
        Settings s=new Settings();
        for(Variable v:values.keySet()){
            s.addProperty(v.toString(),values.get(v),Settings.EDIT_DOUBLE);
        }        
        s.addProperty("",this,Settings.EDIT_PARAMETER);
        s.initPanel(p);
    }
    // INTERFACE METHODS

    @Override
    public FiresChangeEvents clone() {return new ParameterListModel(values);}
    @Override
    public void copyValuesFrom(FiresChangeEvents parent) {        
        values.clear();
        for(Variable v:((ParameterListModel)parent).values.keySet()){
            this.values.put(v,((ParameterListModel)parent).values.get(v));
        }
    }
    @Override
    public void setValue(String s) {throw new UnsupportedOperationException("Not supported yet.");}
    @Override
    public String toLongString() {throw new UnsupportedOperationException("Not supported yet.");}
    @Override
    public PropertyChangeEvent getChangeEvent(String s) {throw new UnsupportedOperationException("Not supported yet.");}

    @Override
    public void stateChanged(ChangeEvent e) {fireStateChanged();}

    
    // UTILITY METHODS
    
    /** Returns a default DoubleRangeModel */
    public static DoubleRangeModel defaultDoubleRangeModel(double d){
        return new DoubleRangeModel(d,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY,.1);
    }
}
