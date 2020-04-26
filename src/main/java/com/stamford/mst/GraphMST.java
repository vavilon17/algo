package com.stamford.mst;

import com.stamford.common.datastruc.Edge;
import com.stamford.common.datastruc.GraphPath;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class GraphMST extends GraphPath {

    private Set<EdgeMST> edgesOrdered = new TreeSet<EdgeMST>();

    public Set<EdgeMST> getEdgesOrdered() {
        return edgesOrdered;
    }

    public void setEdgesOrdered(Set<EdgeMST> edgesOrdered) {
        this.edgesOrdered = edgesOrdered;
    }

    @Override
    public List<Edge> getOutcoming(int tail) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Edge> getIncoming(int head) {
        throw new UnsupportedOperationException();
    }
}
