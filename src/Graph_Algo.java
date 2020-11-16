

import java.util.*;

public class Graph_Algo implements graph_algorithms{

    private graph gr;

    public Graph_Algo()
    {

    }

    public Graph_Algo(graph gr)
    {
        this.gr=gr;
    }

    @Override
    public void init(graph g) {
        this.gr=g;
    }

    /**
     * This function creats a new graph. It adds the nodes from the original graph to the new graph
     * and also connects between nodes in the new graph as same as the connections in the original graph.
     * @return the copied graph
     */
    @Override
    public graph copy() {

        Iterator <node_data>it=this.gr.getV().iterator();
        graph newGraph=new Graph_DS();

        while(it.hasNext()!=false)
        {
            newGraph.addNode(it.next());
        }
        Iterator <node_data>newIt=this.gr.getV().iterator();
        while (newIt.hasNext()!=false)
        {
            node_data n=newIt.next();
            if (n.getNi()!=null)
            {
                Iterator <node_data>nighbors=n.getNi().iterator();
                while (nighbors.hasNext()!=false)
                {
                    node_data n1=nighbors.next();
                    newGraph.connect(n1.getKey(), n.getKey());
                }

            }

        }

        return newGraph;
    }

    /**
     *This function takes the first node in the graph and mark it as "visited".
     * It moves after all his nighbors and mark them as "visited" too and add them to the queue.
     * This method repeats until the queue is empty
     * @return true if I can reach from each node to every node in the graph.
     * @return false if I can't reach from each node to every node in the graph.
     */
    @Override
    public boolean isConnected()
    {
        boolean flag=true;
        Iterator <node_data>it=this.gr.getV().iterator();
        Queue<node_data> queue=new LinkedList();
        int numOfNodes = this.gr.nodeSize();
        node_data temp=new NodeData();
        if (numOfNodes<=1)
            return flag;
        else {
            temp= it.next();
            temp.setInfo("visited");
            queue.add(temp);

            while (queue.isEmpty()==false)
            {
                Iterator <node_data>nighbors=queue.peek().getNi().iterator();
                while (nighbors.hasNext()==true)
                {
                    node_data temp1=nighbors.next();
                    if (temp1.getInfo().equals("visited")==false)
                    {
                        temp1.setInfo("visited");
                        queue.add(temp1);
                    }
                }
                queue.poll();
            }

            Iterator <node_data>newIt=this.gr.getV().iterator();

            for (int i=0;i<numOfNodes;i++)
            {
                node_data nodes= newIt.next();
                if (nodes.getInfo().equals("visited")==false)
                    flag=false;
            }
            Iterator <node_data>it1=this.gr.getV().iterator();
            while (it1.hasNext()==true)
            {
                node_data temp1=it1.next();
                temp1.setTag(0);
                temp1.setInfo("");
            }
            return flag;

        }

    }


    /**
     * In each step, the function change the tag of the node +1.
     * There is a auxiliary function, it works in by recursive.
     * the auxiliary function calculate the distance between src to dest by changing the tag of the node.
     * @param src - start node
     * @param dest - end (target) node
     * @return 0 if the src and the dest are the same node.
     * @return -1 if there isn't a connection between src to dest.
     * @return the shortest path (shorted distance) between src to dest.
     */
    @Override
    public int shortestPathDist(int src, int dest) {
        Queue<node_data> queue=new LinkedList<node_data>();
        node_data temp=new NodeData();
        int dist =0;
        if (src==dest)
            return 0;
        else
        {
            calculateDist(gr.getNode(src),gr.getNode(dest),queue);
            dist=gr.getNode(dest).getTag();
            if (gr.getNode(dest).getTag()==0)
                return -1;
            else
            {
                dist=gr.getNode(dest).getTag();
                Iterator <node_data>it=this.gr.getV().iterator();

                while (it.hasNext()==true)
                {
                    node_data clear= it.next();
                    clear.setInfo("");
                    clear.setTag(0);
                }
                return dist;
            }


        }

    }
    public void calculateDist(node_data src,node_data dest, Queue <node_data> q)
    {
        if (src.getInfo().equals("visit")==false) {
            q.add(src);
            src.setInfo("visit");
        }
        Iterator<node_data> nighbors=src.getNi().iterator();
        node_data temp=new NodeData();
        while (!q.isEmpty())
        {
            while (nighbors.hasNext()==true)
            {
                temp=nighbors.next();
                if (temp.getInfo().equals("visit")==false)
                {
                    q.add(temp);
                    temp.setInfo("visit");
                    temp.setTag(src.getTag()+1);
                }
            }
            temp=q.peek();
            q.remove();
            if (q.peek()!=null)
                calculateDist(q.peek(),dest,q);
        }
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {

        if (src==dest)
            return null;
        Queue<node_data> queue=new LinkedList<node_data>();
        calculateDist(gr.getNode(src),gr.getNode(dest),queue);
        if (gr.getNode(dest).getTag()==0)
            return null;
        else
        {
            queue.add(gr.getNode(dest));
            Iterator<node_data> nighbors=gr.getNode(dest).getNi().iterator();
            List<node_data> list=new LinkedList<>();
            Stack<node_data> s=new Stack<>();
            s.push(queue.peek());
            while(queue.isEmpty()==false)
            {
                while (nighbors.hasNext()==true)
                {
                    node_data temp=nighbors.next();
                    if (temp.getTag()==queue.peek().getTag()-1)
                    {
                        queue.poll();
                        queue.add(temp);
                        s.push(temp);
                        nighbors=temp.getNi().iterator();
                    }
                }
                queue.poll();

            }
            while (s.isEmpty()==false)
            {
                list.add(s.pop());
            }
            return list;

        }

    }



}
