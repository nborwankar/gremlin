package com.tinkerpop.gremlin.functions.lme;

import com.tinkerpop.gremlin.XPathEvaluator;
import com.tinkerpop.gremlin.models.ggm.Vertex;
import com.tinkerpop.gremlin.models.ggm.impls.tg.TinkerGraphFactory;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class KeysFunctionTest extends TestCase {

    public void testKeysFunctionMap() {
        XPathEvaluator xe = new XPathEvaluator();
        Map map = new HashMap();
        map.put("marko", 1);
        map.put("josh", 2);
        map.put(3, "peter");
        xe.getVariables().declareVariable("$m", map);
        assertEquals(xe.evaluateList("g:keys($m)").size(), 3);
        assertTrue(xe.evaluateList("g:keys($m)").contains("marko"));
        assertTrue(xe.evaluateList("g:keys($m)").contains("josh"));
        assertTrue(xe.evaluateList("g:keys($m)").contains(3));
        assertFalse(xe.evaluateList("g:keys($m)").contains("peter"));
    }

    public void testKeysFunctionElement() {
        XPathEvaluator xe = new XPathEvaluator();
        Vertex marko = TinkerGraphFactory.createTinkerGraph().getVertex("1");
        xe.getVariables().declareVariable("$v", marko);
        assertEquals(xe.evaluateList("g:keys($v)").size(), 2);
        assertTrue(xe.evaluateList("g:keys($v)").contains("age"));
        assertTrue(xe.evaluateList("g:keys($v)").contains("name"));
        assertFalse(xe.evaluateList("g:keys($v)").contains(3));
        assertFalse(xe.evaluateList("g:keys($v)").contains("marko"));
    }


}
