## Files Included
* GraphInterface.java : Interface for the remote object.
* GraphObject.java : Implements the remote object interface.
* Server.java : Creates a registry and bind the remote object to it.
* Client.java : Takes command from the user and manipulates the remote object accordingly.

## Algorithm
* GraphInterface consist of a list of graphs objects.
* Each Graph objects consist of a queue for edges.
* Krushkal algorithm has been used to determine the total weight of the minimum weight spanning tree.
* Server creates an instance of the remote object and binds it to the registry.
* Client searches the registry for the same remote object and manipulates it according to the input.
* Client can request for 
    * adding a new graph: add_graph <graph_id> <no_of_vertices>.
    * adding a new edge to a already existing graph: add_edge <graph_id> < vertex1 > < vertex2 > < weight >.
    * getting total weight of the MST for a already existing graph : get_mst <graph_id>.

## To Run
* javac *.java
* java Server < port >
* java Client < server_ip > < port >