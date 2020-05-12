import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.server.UnicastRemoteObject; 


public class Server extends GraphObject{

    public Server() 
        throws java.rmi.RemoteException 
    { 
        super(); 
    } 

    public static void main(String args[]) {
        try {
            if(args.length!=1){
				System.out.println("Error in input format: Expected Format{ java Server <server-port> }"); System.exit(0);
			}
            Integer server_port = Integer.parseInt(args[0]);
            
            GraphObject obj = new GraphObject();
            GraphInterface GB = (GraphInterface) UnicastRemoteObject.exportObject(obj, 0);  
         
            Registry registry = LocateRegistry.createRegistry(server_port); 
            registry.bind("rmi://localhost/"+ server_port.toString()+"/graphcollection", GB);  
            System.err.println("Server ready");
        }
        catch (Exception e){
            System.err.println("Server side error " + e.toString());
        }
    }
}