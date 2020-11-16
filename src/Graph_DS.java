

import java.util.*;

public class Graph_DS implements graph
{
    private HashMap<Integer, node_data> map;
    private int numOfChanges;
    private int numOfNodes;
    private int numOfEdges;


    public Graph_DS() {
       this.map= new HashMap<Integer, node_data>();
        this.numOfChanges=0;
        this.numOfNodes=0;
        this.numOfEdges=0;

    }

    @Override
    public node_data getNode(int key) {
        if (map.containsKey(key)==true)
            return map.get(key);
        else
            return null;

    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if (getNode(node1).hasNi(node2)==true && getNode(node2).hasNi(node1)==true)
            return true;
        return false;
    }

    @Override
    public void addNode(node_data n) {
        map.put(n.getKey(),n);
        numOfNodes++;
        numOfChanges++;

    }

    @Override
    public void connect(int node1, int node2) {
        if (node1!=node2 && hasEdge(node1,node2)==false )
        {
            getNode(node1).addNi(getNode(node2));
            getNode(node2).addNi(getNode(node1));
            this.numOfChanges++;
            this.numOfEdges++;
        }

    }

    @Override
    public Collection<node_data> getV() {
        return map.values();
    }

    @Override
    public Collection<node_data> getV(int node_id) {
        if (map.get(node_id)!=null)
            return getNode(node_id).getNi();
        return null;
    }

    @Override
    public node_data removeNode(int key) {
        if (getNode(key)!=null)
        {
            numOfChanges++;
            Collection<node_data> a = getNode(key).getNi();
            Iterator<node_data> it = a.iterator();
            while (it.hasNext()!=false)
            {
                it.next().removeNode(getNode(key));
                numOfEdges--;
            }
            numOfNodes--;
            return map.remove(key);
        }
        else
            return null;

    }

    @Override
    public void removeEdge(int node1, int node2) {
        if (getNode(node1)!=null && getNode(node2)!=null && hasEdge(node1,node2) )
        {
            getNode(node1).removeNode(getNode(node2));
            getNode(node2).removeNode(getNode(node1));
            numOfChanges++;
            numOfEdges--;
        }
    }

    @Override
    public int nodeSize() {
        return numOfNodes;
    }

    @Override
    public int edgeSize() {
        return numOfEdges;
    }

    @Override
    public int getMC() {
        return numOfChanges;
    }


}







