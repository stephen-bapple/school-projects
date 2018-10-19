import java.util.ArrayList;

/**
 * A custom tree to implement role hierarchy.
 *
 * @author Stephen Bapple
 *
 */
public class HierarchyTree {
    /** This node's role. */
    private String role;

    /** This node's descendant. */
    private HierarchyTree descendant;

    /** The list of ascendants for this node. */
    private ArrayList<HierarchyTree> ascendants;
    
    /** The list of this roles total permissions. */
    //private ArrayList<String> totalRights;

    /** The list of this roles personal permissions. */
    //private ArrayList<String> roleRights;

    /** The list of this roles inherited permissions. */
    //private ArrayList<String> inheritedRights;

    /**
     * Create a tree with a given role and an empty list of ascendants
     * and descendants.
     * @param role this node's role.
     */
    public HierarchyTree(String role) {
        this.role = role;
        this.ascendants = new ArrayList<HierarchyTree>();
        this.descendant = null;
    }

    /**
     * Add an ascendent to this node.
     *
     * @param newAscendent
     */
    public void addAscendent(HierarchyTree newAscendant) {
        //System.out.println(newAscendant.getRole() +
        //        " added as ascendant to " + role);
        ascendants.add(newAscendant);
    }
    
    /**
     * Re assess the permissions 
     */
    public void reAssessPermissions() {
        System.out.println("TODO"); // TODO
    }
    
    /**
     * Set the descendant for this node.
     *
     * @param newDescendant the new descendant.
     */
    public void setDescendant(HierarchyTree newDescendant) {
        this.descendant = newDescendant;
    }

    /**
     * Get the descendant of this node, null if no such node.
     *
     * @return the descendant of this node, or null if there is none.
     */
    public HierarchyTree getDescendant() {
        return descendant;
    }
    
    public boolean isLeaf() {
        return ascendants.isEmpty();
    }
    
    /**
     * Get this node's role.
     * @return this node's role.
     */
    public String getRole() {
        return role;
    }
    
    public HierarchyTree getNodeFromValue(String value) {
    /*    if (getRole().equals(value)) {
            return this;
        }
        else {
            for (HierarchyTree a : ascendants) {
                if (a.getRole().equals(value)) {
                    return a;
                }
            }
            HierarchyTree rtn = null;
            for (HierarchyTree a : ascendants) {
                rtn = a.getNodeFromValue(value);
                if (rtn.getRole().equals(value)) {
                    return rtn;
                }
            }
            return new HierarchyTree("Role Not Found");
        } */
        ArrayList<HierarchyTree> thisTree = asList();
        HierarchyTree foundTree = null;
        for (HierarchyTree node : thisTree) {
            if (node.getRole().equals(value)) {
                foundTree = node;
            }
        }
        return foundTree;
    }
    /**
     * Print this tree out in a simplified diagram.
     */
    @Override
    public String toString() {
        String rtnStr = role + "" + listAscendants();
        for (HierarchyTree a : ascendants) {
            rtnStr = rtnStr + "\r\n" + a.toString();
        }
        return rtnStr;
    }
    
    /**
     * List all the ascendants of this node.
     * @return the String listing all the ascendants of this node.
     */
    public String listAscendants() {
        if (ascendants.isEmpty()) {
            return " <No ascendants>";
        }
        
        String rtnStr = "--->" + ascendants.get(0).getRole();
        for (int i = 1; i < ascendants.size(); i++) {
            rtnStr = rtnStr + ", " + ascendants.get(i).getRole();
        }
        return rtnStr;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<HierarchyTree> asList() {
        ArrayList<HierarchyTree> temp = new ArrayList<>();
        temp.add(this);
        temp = asList(temp);
        return temp;
    }
    
    public ArrayList<HierarchyTree> asList(ArrayList<HierarchyTree> temp) {
        for (HierarchyTree a : ascendants) {
            temp.add(a);
        }
        for (HierarchyTree a : ascendants) {
            temp = a.asList(temp);
        }
        return temp;
    }
}
