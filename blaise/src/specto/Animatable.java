/*
 * Animator.java
 * Created on Sep 14, 2007, 8:30:26 AM
 */

package specto;

import sequor.component.RangeTimer;
import java.awt.Graphics2D;

/**
 * This interface handles animation of objects on the plot window. By default, most Plottable's
 * should be animatable. The methods required for this simply pass a timer into the
 * paint component method!
 * <br><br>
 * @author Elisha Peterson
 */
public interface Animatable<V extends Visometry> {    
    public abstract void paintComponent(Graphics2D g,RangeTimer t);    
}