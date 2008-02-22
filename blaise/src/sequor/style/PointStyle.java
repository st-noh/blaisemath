/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sequor.style;

import java.awt.Color;
import sequor.model.FiresChangeEvents;

/**
 *
 * @author ae3263
 */
public class PointStyle extends VisualStyle {
    
    // GENERAL STYLE OPTIONS
    
    protected Color color=Color.RED;
    protected int style=LARGE;
    protected double size=1.0;
    
    // STYLE CONSTANTS
    
    public static final int SMALL=0;
    public static final int MEDIUM=1;
    public static final int LARGE=2;
    public static final int CONCENTRIC=3;
        
    // GETTERS AND SETTERS
    
    public Color getColor(){return color;}
    public void setColor(Color newValue){if(color!=newValue){color=newValue;fireStateChanged();}}
    public int getStyle(){return style;}
    public void setStyle(int newValue){if(newValue!=style&&newValue>=0&&newValue<=4){style=newValue;fireStateChanged();}}
    public void cycleStyle(){style=(style+1)%4;fireStateChanged();}
    public double getSize(){return size;}
    public void setSize(double newValue){if(newValue!=size&&newValue>0&&newValue<=10){size=newValue;fireStateChanged();}}
    
    // IMPLEMENTING ABSTRACT METHODS

    @Override
    public FiresChangeEvents clone() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void copyValuesFrom(FiresChangeEvents parent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
