import java.rmi.RemoteException;
import java.util.*;

public class GraphObject implements GraphInterface {

    public GraphObject() throws java.rmi.RemoteException {
        super();
    }

    public class EdgeInfo implements Comparable<EdgeInfo>, GraphInterface.EdgeInfo {
        public int v;
        public int u;
        public int w;

        public EdgeInfo(int v, int u, int w) throws java.rmi.RemoteException {
            this.v = v;
            this.u = u;
            this.w = w;
        }

        public int getw() throws java.rmi.RemoteException {
            return this.w;
        }

        public int getv() throws java.rmi.RemoteException {
            return this.v;
        }

        public int getu() throws java.rmi.RemoteException {
            return this.u;
        }

        @Override
        public int compareTo(EdgeInfo edge) {

            try {
                if (this.getw() > edge.getw())
                    return 1;
                else
                    return -1;
            } catch (RemoteException e) {
                e.printStackTrace();
                return 1;
            }
        }
    }

    public class Graph implements GraphInterface.Graph{
        public String id;
        public int vertices;
        PriorityQueue<EdgeInfo> edges = new PriorityQueue<>();
        List<Integer> parent;
        List<Integer> height;

        public Graph(String id, int no_ver) throws java.rmi.RemoteException{
            this.id = id;
            this.vertices = no_ver;
            this.parent = new ArrayList<>();
            this.height = new ArrayList<>();

            intializeparameter();
        }

        public void intializeparameter() throws java.rmi.RemoteException{
            this.parent.clear();
            this.height.clear();
            for (int i = 0; i <= this.vertices; i++) {
                this.parent.add(-1);
                this.height.add(0);
            }
        }

        public int getparent(int x) throws java.rmi.RemoteException{
            while (this.parent.get(x) != -1) {
                x = this.parent.get(x);
            }
            return x;
        }

        public void dsu(int u, int v) throws java.rmi.RemoteException{
            int p1 = getparent(u);
            int p2 = getparent(v);

            if (height.get(u) > height.get(v)) {
                this.parent.set(p2, p1);
            }
            if (height.get(v) > height.get(u)) {
                this.parent.set(p1, p2);
            }
            if (height.get(v) == height.get(u)) {
                this.parent.set(p2, p1);
                height.set(p2, height.get(p1) + 1);
            }
        }

        public String getid() throws java.rmi.RemoteException{
            return this.id;
        }

        public void addEdge(int v, int u, int w) throws java.rmi.RemoteException{
            EdgeInfo temp_edge = new EdgeInfo(v, u, w);
            edges.add(temp_edge);
        }

        public int getmst() throws java.rmi.RemoteException{
            int num = 1;
            int weight = 0;
            int edge_no = edges.size();
            PriorityQueue<EdgeInfo> temp_edges = new PriorityQueue<>(edges);

            while (num != this.vertices && edge_no >= this.vertices - 1) {
                if(temp_edges.size()==0)
                {
                    weight = -1;
                    break;
                }
                EdgeInfo temp_edge = temp_edges.poll();
                int p1 = getparent(temp_edge.getu());
                int p2 = getparent(temp_edge.getv());
                if (p1 != p2) {
                    weight += temp_edge.getw();
                    dsu(p1, p2);
                    num += 1;
                }
            }

            if(weight == 0)
                weight = -1;

            intializeparameter();
            return weight;
        }
    }

    public static List<Graph> graphs = new ArrayList<Graph>();

    public void addgraph(String graph_id, int no_ver) throws java.rmi.RemoteException{
        Graph graph_temp = new Graph(graph_id, no_ver);
        graphs.add(graph_temp);
    }

    public Graph findgraph(String graph_id) throws java.rmi.RemoteException{
        Graph temp_graph;
        for (Graph graph : graphs) {
            if (graph.getid().compareTo(graph_id) == 0) {
                temp_graph = graph;
                return temp_graph;
            }
        }
        return null;
    }

    public void addGraphEdge(String graph_id, int v, int u, int w) throws java.rmi.RemoteException{
        GraphInterface.Graph temp = this.findgraph(graph_id);
        temp.addEdge(v, u, w);
    }

    public int findGraphMST(String graph_id) throws java.rmi.RemoteException{
        GraphInterface.Graph temp = this.findgraph(graph_id);
        if(temp == null)
        {
            System.out.println("Graph with id " + graph_id + " not found");
            return 0;
        }
        int weight = temp.getmst();
        return weight;
    }

}