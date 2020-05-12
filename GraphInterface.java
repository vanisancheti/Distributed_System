import java.util.*;

public interface GraphInterface extends java.rmi.Remote{
    public interface EdgeInfo{
        public int v=0;
        public int u=0;
        public int w=0;
        public int getw() throws java.rmi.RemoteException;
        public int getv() throws java.rmi.RemoteException;
        public int getu() throws java.rmi.RemoteException;
    }
    public interface Graph {
        public String id = "0";
        public int vertices = 0;
        PriorityQueue<EdgeInfo> edges = new PriorityQueue<>();
        List<Integer> parent = new ArrayList<>();;
        List<Integer> height = new ArrayList<>();;
        public void intializeparameter() throws java.rmi.RemoteException; 
        public int getparent(int x) throws java.rmi.RemoteException; 
        public void dsu(int u, int v) throws java.rmi.RemoteException; 
        public String getid() throws java.rmi.RemoteException; 
        public void addEdge(int v, int u, int w) throws java.rmi.RemoteException; 
        public int getmst() throws java.rmi.RemoteException; 
    }
    public static List<Graph> graphs = new ArrayList<Graph>();
    public void addgraph(String graph_id, int no_ver) throws java.rmi.RemoteException; 
    public Graph findgraph(String graph_id) throws java.rmi.RemoteException;
    public void addGraphEdge(String graph_id, int v, int u, int w) throws java.rmi.RemoteException;
    public int findGraphMST(String graph_id) throws java.rmi.RemoteException;
}