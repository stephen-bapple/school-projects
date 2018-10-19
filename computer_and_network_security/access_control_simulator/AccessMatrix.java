import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An access that matrix.
 * 
 * @author Stephen Bapple
 *
 */
public class AccessMatrix {
    private ArrayList<String> objects;
    private ArrayList<String> subjects;
    private ArrayList<LinkedList<RightList>> controlLists;

    public AccessMatrix(ArrayList<String> subjects, ArrayList<String> objects) {
        this.subjects = subjects;
        this.objects = new ArrayList<>();

        for (String subj : subjects) {
            this.objects.add(subj);
        }

        for (String obj : objects) {
            this.objects.add(obj);
        }

        this.controlLists = new ArrayList<>();
        fillMatrix();
    }
    
    public AccessMatrix(ArrayList<String> subjects, ArrayList<String> objects,
            int flag) {
        this.subjects = subjects;
        this.objects = objects;
        this.controlLists = new ArrayList<>();
        fillMatrix();
    }
    
    

    private void fillMatrix() {
        for (String object : objects) {
            // System.out.println("Filling in rights for access to " + object);
            LinkedList<RightList> rights = new LinkedList<>();
            for (String subject : subjects) {
                // System.out.println("Added entry for " + subject + " : " +
                // object);
                rights.add(new RightList(subject, object));
            }
            controlLists.add(rights);
        }
    }

    public void addRight(String subj, String obj, String accessRight) {
        //System.out.println(subj + " : " + obj + " : " + accessRight);
        int index;
        for (index = 0; index < controlLists.size(); index++) {
            // System.out.println(controlLists.get(index).get(0).obj);
            if (controlLists.get(index).get(0).obj.equals(obj)) {
                //System.out
                //      .println("Found " + obj + "'s list entry at " + index);
                break;
            }
        }
        if (index == controlLists.size()) {
            index--;
        }
        LinkedList<RightList> objectRights = controlLists.get(index);

        for (RightList right : objectRights) {
            //System.out.println("Searching object rights for " + obj);
            //System.out.println(right.subj);
            if (right.subj.equals(subj)) {
                //System.out.println("Found subject " + subj);
                if (right.getRights().contains(accessRight)) {
                    //System.out.println("Duplicate right not added.");
                    // Everything is fine, we don't need any duplicates.
                } else {
                    //System.out.println("About to add: " + subj + " : " + obj
                    //        + " : " + accessRight);
                    right.addRight(accessRight);
                    break;
                }
            }
        }

    }

    public void removeRight(String subj, String obj, String toRemove) {
        int index;

        // Find the list for the object in question
        for (index = 0; index < controlLists.size(); index++) {
            if (controlLists.get(index).get(0).obj.equals(obj)) {
                break;
            }
        }

        // Get the linked list of rights for that object
        if (index == controlLists.size()) {
            index--;
        }
        LinkedList<RightList> objectRights = controlLists.get(index);

        // Find the access rights for the subject in question
        for (RightList right : objectRights) {
            if (right.subj.equals(subj)) {
                right.deleteRight(toRemove);
            }
        }

        // Otherwise, right must have not been in the matrix.
    }

    public boolean hasRight(String subj, String obj, String right) {
        return (getRights(subj, obj).contains(right));
    }

    public ArrayList<String> getRights(String subj, String obj) {
        int index;

        // Find the list for the object in question
        for (index = 0; index < controlLists.size(); index++) {
            if (controlLists.get(index).get(0).obj.equals(obj)) {
                break;
            }
        }
        // Get the linked list of rights for that object
        if (index == controlLists.size()) {
            index--;
        }
        LinkedList<RightList> objectRights = controlLists.get(index);

        // Find the access rights for the subject in question
        for (RightList right : objectRights) {
            if (right.subj.equals(subj)) {
                return right.getRights();
            }
        }
        return new ArrayList<>();
    }

    public String getRightsAsString(String subj, String obj) {
        int index;

        // Find the list for the object in question
        for (index = 0; index < controlLists.size(); index++) {
            if (controlLists.get(index).get(0).obj.equals(obj)) {
                break;
            }
        }
        // Get the linked list of rights for that object
        if (index == controlLists.size()) {
            index--;
        }
        LinkedList<RightList> objectRights = controlLists.get(index);

        // Find the access rights for the subject in question
        for (RightList right : objectRights) {
            if (right.subj.equals(subj)) {
                return right.toString();
            }
        }
        return "";
    }

    public String toString() {
        String rtnStr = "\t";

        // Add the objects in the top row
        for (String object : objects) {
            rtnStr = rtnStr + object + "\t";
        }
        rtnStr = rtnStr + "\r\n";

        // Add the the rest of the rows
        for (String subject : subjects) {
            rtnStr = rtnStr + subject;
            for (String object : objects) {
                // Add all the rights for every object
                rtnStr = rtnStr + "\t" + getRightsAsString(subject, object);
            }
            rtnStr = rtnStr + "\r\n" + "\r\n";
        }

        // return it
        return rtnStr;
    }

    public void grantDefaultControl() {
        for (String subject : subjects) {
            // System.out.println("Granting control of " + subject + " to "
            // + subject);
            addRight(subject, subject, "ctrl");

        }
    }

    public void grantOwnershipToDescendents(HierarchyTree tree) {
        for (String subject : subjects) {
            HierarchyTree a = tree.getNodeFromValue(subject);

            if (!a.getRole().equals(subject))
                System.out.println("Something is wrong."
                        + " Make sure the role returned is correct.");
            if (!a.isLeaf()) {
                ArrayList<HierarchyTree> ascendants = a
                        .asList(new ArrayList<HierarchyTree>());
                for (HierarchyTree ascendant : ascendants) {
                    addRight(subject, ascendant.getRole(), "own");
                }
            }
        }

    }

    /**/
    public void assessPermissions(HierarchyTree tree) {
        for (String subject : subjects) {
            HierarchyTree a = tree.getNodeFromValue(subject);
            if (!a.getRole().equals(subject))
                System.out.println("Something is wrong."
                        + " Make sure the role returned is correct.");
            // do {
            if (!a.isLeaf()) {
                //System.out.println("Adding inherited permissions for "
                //        + a.getRole());
                ArrayList<HierarchyTree> ascendants = a
                        .asList(new ArrayList<HierarchyTree>());
                for (HierarchyTree ascendant : ascendants) {
                    for (String object : objects) {
                        ArrayList<String> rightsToAdd = getRights(
                                ascendant.getRole(), object);
                        for (String right : rightsToAdd) {
                            //System.out.println(subject + " inherits right: "
                            //        + right);
                            // addRight ensures duplicates will not be added
                            addRight(subject, object, right);
                        }
                        //System.out.println();
                    }
                }
            }
            // a = a.getDescendant();
            // a = null;
            // } while (a != null);
        }
    }/**/

    // public void assessPermissions(HierarchyTree tree) {

    // }
}
