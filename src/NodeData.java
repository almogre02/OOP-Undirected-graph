

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class NodeData implements node_data {
    private static int index;
    private int key;
    private String str;
    private int tag;
    private HashMap<Integer, node_data> nodeData;

    public NodeData() {
        this.key=index;
        this.str = "";
        this.tag = 0;
        this.nodeData=new HashMap<Integer, node_data>();
        index++;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public Collection<node_data> getNi() {
        return this.nodeData.values();
    }

    @Override
    public boolean hasNi(int key) {
        if (this.nodeData.get(key)!=null)
            return true;
        return false;
    }

    @Override
    public void addNi(node_data t)
    {
        if ((t!=null))
        {
            this.nodeData.put(t.getKey(), t);

        }

    }

    @Override
    public void removeNode(node_data node) {
        this.nodeData.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return this.str;
    }

    @Override
    public void setInfo(String s) {
        this.str = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;

    }

}
