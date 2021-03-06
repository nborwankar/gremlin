package com.tinkerpop.gremlin.functions.lme;

import com.tinkerpop.gremlin.functions.FunctionHelper;
import com.tinkerpop.gremlin.functions.GremlinFunctions;
import com.tinkerpop.gremlin.models.ggm.Element;
import com.tinkerpop.gremlin.statements.EvaluationException;
import org.apache.commons.jxpath.ExpressionContext;
import org.apache.commons.jxpath.Function;

import java.util.List;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GetFunction implements Function {

    public static final String FUNCTION_NAME = "get";

    public Object invoke(final ExpressionContext context, final Object[] parameters) {

        if (null != parameters && parameters.length == 2) {
            Object[] objects = FunctionHelper.nodeSetConversion(parameters);
            if (objects[0] instanceof Map) {
                return ((Map) objects[0]).get(objects[1]);
            } else if (objects[0] instanceof List && objects[1] instanceof Number) {
                return ((List) objects[0]).get(((Number) objects[1]).intValue() - 1);
            } else if (objects[0] instanceof Element && objects[1] instanceof String) {
                return ((Element) objects[0]).getProperty((String) objects[1]);
            }
        }

        throw EvaluationException.createException(FunctionHelper.makeFunctionName(GremlinFunctions.NAMESPACE_PREFIX, FUNCTION_NAME), EvaluationException.EvaluationErrorType.UNSUPPORTED_PARAMETERS);

    }


}
