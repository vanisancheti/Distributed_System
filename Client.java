import java.util.*;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 

public class Client{
    public static void main(String args[]){
        try{
            if(args.length!=2)
            {
				System.out.println("Error in input format: Expected Format{ java Client <server-ip> <server-port> }"); System.exit(0);
            }
			String server_ip = args[0];            
            Integer server_port = Integer.parseInt(args[1]);
    
            Registry registry = LocateRegistry.getRegistry(server_port); 
            GraphInterface GB = (GraphInterface) registry.lookup("rmi://" + server_ip + "/" + server_port + "/graphcollection"); 
  
            Scanner in = new Scanner(System.in);
            while(in.hasNext()){
                String type_opt = in.next();
                if(type_opt.compareTo("add_graph")==0){
                    String graph_id = in.next();
                    int no_ver = in.nextInt();
                    GB.addgraph(graph_id, no_ver);
                }
                if(type_opt.compareTo("add_edge")==0){
                    String graph_id = in.next();
                    int v = in.nextInt();
                    int u = in.nextInt();
                    int w = in.nextInt();
                    GB.addGraphEdge(graph_id,v,u,w);
                }
                if(type_opt.compareTo("get_mst")==0){
                    String graph_id = in.next();
                    int weight = GB.findGraphMST(graph_id);
                    if(weight!=0){
                        System.out.println(weight);
                    }
                }
            }
            in.close();
        }
        catch (Exception e){
            System.err.println("Client side error " + e.getMessage());
        }
    }
}