package com.tinkerpop.gremlin.paths;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public interface Path {
    public String getPathName();

    public Object invoke(Object root);
}
