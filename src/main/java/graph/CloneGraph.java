package graph;

import java.util.HashMap;

public class CloneGraph {
    HashMap<Node, Node> oldToNew;
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        oldToNew = new HashMap<>();
        return dfs(node);
    }

    private Node dfs(Node node) {
        if(oldToNew.containsKey(node)){
            return oldToNew.get(node);
        }
        Node copyNode = new Node(node.val);
        oldToNew.put(node, copyNode);
        for(Node n : node.neighbors){
            copyNode.neighbors.add( dfs(n));
        }
        return copyNode;
    }
}
