package com.tinkerpop.gremlin.functions.graph;

import com.tinkerpop.gremlin.functions.FunctionHelper;
import com.tinkerpop.gremlin.functions.GremlinFunctions;
import com.tinkerpop.gremlin.models.ggm.Edge;
import com.tinkerpop.gremlin.models.ggm.Element;
import com.tinkerpop.gremlin.models.ggm.Graph;
import com.tinkerpop.gremlin.models.ggm.Vertex;
import com.tinkerpop.gremlin.statements.EvaluationException;
import org.apache.commons.jxpath.ExpressionContext;
import org.apache.commons.jxpath.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class RemoveVertexEdgeFunction implements Function {

    public static final String FUNCTION_NAME = "remove-ve";

    public Boolean invoke(final ExpressionContext context, final Object[] parameters) {

        if (parameters != null) {
            Graph graph = GraphFunctionHelper.getGraph(context, parameters);
            Object[] objects = FunctionHelper.nodeSetConversion(parameters);

            if (objects.length == 2 && FunctionHelper.assertTypes(objects, new Class[]{Graph.class, Element.class})) {
                if (objects[1] instanceof Vertex)
                    graph.removeVertex((Vertex) objects[1]);
                else if (objects[1] instanceof Edge)
                    graph.removeEdge((Edge) objects[1]);
                return Boolean.TRUE;
            } else if (objects.length == 1 && objects[0] instanceof Element) {
                if (objects[0] instanceof Vertex)
                    graph.removeVertex((Vertex) objects[0]);
                else if (objects[0] instanceof Edge)
                    graph.removeEdge((Edge) objects[0]);
                return Boolean.TRUE;
            }
        }
        throw EvaluationException.createException(FunctionHelper.makeFunctionName(GremlinFunctions.NAMESPACE_PREFIX, FUNCTION_NAME), EvaluationException.EvaluationErrorType.UNSUPPORTED_PARAMETERS);

    }

}
