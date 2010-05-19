/**
 * SpaceParametricCurve.java
 * Created on Aug 9, 2009
 */
package visometry.space;

import coordinate.RealIntervalUniformSampler;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import org.apache.commons.math.FunctionEvaluationException;
import org.apache.commons.math.analysis.UnivariateVectorialFunction;
import scio.coordinate.Point3D;
import scio.coordinate.sample.SampleSet;
import scio.function.utils.DemoCurve3D;
import util.ChangeBroadcaster;
import visometry.plottable.VSegment;

/**
 * <p>
 *   <code>SpaceParametricCurve</code> plots a curve depending on a single parameter.
 * </p>
 *
 * @author Elisha Peterson
 */
public class SpaceParametricCurve extends SpacePathPlottable {

    /** The underlying function */
    UnivariateVectorialFunction func;
    /** Domain of function */
    SampleSet<Double> sampler;

    /** Segment-representation of the domain of the plottable. */
    VSegment<Double> domainPlottable;


    /**
     * Initializes with an underlying function and min/max domain
     */
    public SpaceParametricCurve() {
        this(new UnivariateVectorialFunction(){ public double[] value(double x) { return new double[] { Math.cos(x), Math.sin(x), x }; } },
                0, 2*Math.PI, 100);
    }

    /**
     * Initializes with an underlying function and min/max domain
     * @param func the function
     * @param min the min value in domain
     * @param max the max value in domain
     */
    public SpaceParametricCurve(UnivariateVectorialFunction func, double min, double max) {
        this(func, min, max, 100);
    }

    /**
     * Initializes with an underlying function and a step rate for going through parameter values.
     * @param func the function
     * @param min the min value in domain
     * @param max the max value in domain
     * @param numSamples the number of samples
     */
    public SpaceParametricCurve(UnivariateVectorialFunction func, double min, double max, int numSamples) {
        setFunction(func);
        setDomain(new RealIntervalUniformSampler(min, max, numSamples));
    }

    @Override
    public String toString() {
        return "Parametric Curve 3D";
    }

    //
    // FUNCTION METHODS
    //

    DemoCurve3D demo = DemoCurve3D.CINQUEFOIL_TOROIDAL_SPIRAL;

    /** @return the function for the parametric curve */
    public UnivariateVectorialFunction getFunction() {
        return demo == demo.NONE ? func : demo;
    }

    /** Sets the function for the parametric curve. */
    public void setFunction(UnivariateVectorialFunction func) {
        if (func != null && this.func != func) {
            this.func = func;
            demo = demo.NONE;
            firePlottableChanged();
        }
    }
    
    /** @return currently set demo curve */
    public DemoCurve3D getDemoCurve() {
        return demo;
    }

    /** Sets the underlying function to be specified demo curve */
    public void setDemoCurve(DemoCurve3D demo) {
        this.demo = demo;
        if (demo != DemoCurve3D.NONE) {
            setDomain(new RealIntervalUniformSampler(demo.t0, demo.t1, 200));
            domainPlottable.setPoint1(demo.t0);
            domainPlottable.setPoint2(demo.t1);
        }
        firePlottableChanged();
    }

    @Override
    protected void recompute() {
        UnivariateVectorialFunction useFunc = demo == DemoCurve3D.NONE ? func : demo;
        try {
            List<Double> tt = sampler.getSamples();
            entry.local = path = new Point3D[tt.size()];
            double[] coords = useFunc.value(tt.get(0));
            int i = 0;
            for (double x : tt) {
                coords = useFunc.value(x);
                path[i++] = new Point3D(coords[0], coords[1], coords[2]);
            }
        } catch (FunctionEvaluationException ex) {
            Logger.getLogger(SpaceParametricCurve.class.getName()).log(Level.SEVERE, null, ex);
        }
        entry.needsConversion = true;
    }

    //
    // DOMAIN METHODS
    //

    /** @return representation of the curve's domain */
    public VSegment<Double> getDomainPlottable() {
        return domainPlottable;
    }
    
    /** @return domain of the curve's parameter */
    public SampleSet<Double> getDomain() {
        return sampler;
    }

    /** Sets domain of the curve's parameter. */
    public void setDomain(SampleSet<Double> newDomain) {
        if (sampler != newDomain && newDomain != null) {
            if (sampler instanceof ChangeBroadcaster)
                ((ChangeBroadcaster) sampler).removeChangeListener(this);
            sampler = newDomain;
            if (sampler instanceof ChangeBroadcaster)
                ((ChangeBroadcaster) sampler).addChangeListener(this);
            updateDomainPlottable();
            firePlottableChanged();
        }
    }

    /** Updates the domain plottable based upon the sampler. */
    void updateDomainPlottable() {
        adjusting = true;
        RealIntervalUniformSampler rius = (RealIntervalUniformSampler) sampler;
        if (domainPlottable == null) {
            domainPlottable = new VSegment<Double>( rius.getMinimum(), rius.getMaximum() );
            domainPlottable.addChangeListener(this);
        } else {
            domainPlottable.setPoint1(rius.getMinimum());
            domainPlottable.setPoint2(rius.getMaximum());
        }
        adjusting = false;
    }

    /** Updates the samplers based upon changes in the domain plottable. */
    void updateSamplers() {
        adjusting = true;
        RealIntervalUniformSampler rius = (RealIntervalUniformSampler) sampler;
        rius.setMinimum( domainPlottable.getPoint1() );
        rius.setMaximum( domainPlottable.getPoint2() );
        adjusting = false;
    }

    //
    // CHANGE HANDLING
    //

    transient boolean adjusting = false;

    @Override
    public void stateChanged(ChangeEvent e) {
        if (adjusting)
            return;
        if (e.getSource() == domainPlottable)
            updateSamplers();
        else if (e.getSource() == sampler)
            updateDomainPlottable();
        super.stateChanged(e);
    }

}