package day22;

import java.util.Objects;

public class NodePair {
    Node node1;
    Node node2;

    public NodePair(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NodePair other = (NodePair) obj;
        if((!Objects.equals(this.node1, other.node2) || !Objects.equals(this.node2, other.node1)) && (!Objects.equals(this.node1, other.node1) || !Objects.equals(this.node2, other.node2))) {
            return false;
        }
        
        
        return true;
    }
    
    
    
}
