/**
 * ParsedUnivariateRealFunction.java
 * Created on Dec 1, 2009
 */

package org.bm.blaise.scribo.parser.real;

import java.util.HashMap;
import java.util.List;
import org.bm.blaise.scribo.parser.SemanticNode;
import org.bm.blaise.scribo.parser.SemanticTreeEvaluationException;
import org.bm.blaise.scribo.parser.semantic.SemanticTreeUtils;
import org.bm.blaise.scribo.parser.ParseException;

/**
 * <p>
 *    This class uses a String to represent a real function of one variable.
 *    Changes to the String result in a change of the function.
 * </p>
 * @author Elisha Peterson
 */
public class ParsedUnivariateRealFunction {

    String function;
    transient String variable;
    transient HashMap<String,Double> variableTable;
    transient SemanticNode functionTree;

    public ParsedUnivariateRealFunction(String function) throws ParseException {
        setFunctionString(function);
    }

    public String getFunctionString() {
        return function;
    }

    /** 
     * Sets up the function for the given string, along with doing some error checking and building the underlying tree.
     *
     * @param function  the String representation of the function
     * @throws ParseException  if the String cannot be parsed
     * @throws IllegalArgumentException  if the String function has too many unknowns
     */
    public void setFunctionString(String function) throws ParseException {
        SemanticNode newTree = RealTreeBuilder.INSTANCE.buildTree(function);
        List<String> vars = newTree.unknowns();
        if (vars.size() > 1) {
            throw new IllegalArgumentException("Cannot construct a UnivariateRealFunction: the function " + function + " has more than 1 input.");
        }
        variableTable = new HashMap<String,Double>();
        variable = vars.size() == 1 ? vars.get(0) : "dummy";
        variableTable.put(variable, null);
        this.function = function;
        this.functionTree = newTree;
    }

    @Override
    public String toString() {
        return function;
    }

    public double value(double x) throws SemanticTreeEvaluationException {
        variableTable.put(variable, x);
        SemanticTreeUtils.assignVariables(variableTable, functionTree);
        return (Double) functionTree.value();
    }

}
