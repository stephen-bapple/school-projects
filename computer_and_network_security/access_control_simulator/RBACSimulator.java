import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class runs a routine that simulates Role Based Access Control by
 * creating an access control data structure and repeatedly prompting the user
 * to query the database via command line.
 *
 * @author Stephen Bapple
 *
 */
public class RBACSimulator {
    /** The default filename to read the roles from. */
    private String HIERARCHY = "roleHierarchy.txt";

    /** The default filename to read the users and their roles from. */
    private String USERS_TO_ROLES = "userRoles.txt";

    /** The default filename to read the SSD restrictions from. */
    private String SSD = "roleSetsSSD.txt";

    /** The default filename to read the permissions. */
    private String PERMISSIONS = "permissionsToRoles.txt";

    /** The default filename to read the objects from. */
    private String OBJECTS = "resourceObjects.txt";

    /** The default filename to write the results to. */
    //private String RESULTS = "testResults.txt";

    /** The tree that holds all roles in this RBAC simulation. */
    private HierarchyTree roleTree;

    /** The "matrix" for the roles and their access rights. */
    private AccessMatrix accessRights;

    /** The "matrix" for the users and their roles. */
    private AccessMatrix userRoles;

    /** The list of constraints on the roles. */
    private ArrayList<Constraint> roleConstraints;

    /** All the users. */
    private ArrayList<String> globalUsers;

    /** All the roles. */
    private ArrayList<String> globalRoles;

    /** All the objects. */
    private ArrayList<String> globalObjects;

    /**
     * The main method simply creates a new RBAC simulator and calls the
     * appropriate methods to set up the simulation.
     */
    public static void main(String[] args) throws IOException {
        RBACSimulator program = new RBACSimulator();
        program.setUpRoleHierarchy();
        program.setUpRoleAccessMatrix();
        program.setUpPermissions();
        program.setUpConstraints();
        program.setUpUsers();
        program.query();
    }

    /**
     * Read the role hierarchy from a file, check it for invalid entries, prompt
     * the user to fix the file if an invalid entry is found, then lastly
     * display the tree.
     */
    public void setUpRoleHierarchy() throws IOException {
        Scanner inputFile = new Scanner(new File(HIERARCHY));
        ArrayList<String> ascendantList = new ArrayList<>();
        HashMap<String, HierarchyTree> nodeFinder = new HashMap<>();
        int lineNumber = 1;
        String input = "";
        boolean invalidInput = false;
        HierarchyTree aNode = null;

        // Read the entire file.
        while (inputFile.hasNext()) {
            // Read a line
            input = inputFile.nextLine();
            String ascendant = (input.split("\t+| +"))[0];
            String descendant = (input.split("\t+| +"))[1];

            // Validate the input.
            if (ascendantList.contains(ascendant)) {
                invalidInput = true;
                break; // Break out of the while loop.
            } else {
                ascendantList.add(ascendant);
                HierarchyTree dNode;

                if (nodeFinder.containsKey(descendant)) {
                    dNode = nodeFinder.get(descendant);
                } else {
                    dNode = new HierarchyTree(descendant);
                    nodeFinder.put(descendant, dNode);
                }

                if (nodeFinder.containsKey(ascendant)) {
                    aNode = nodeFinder.get(ascendant);
                } else {
                    aNode = new HierarchyTree(ascendant);
                }

                aNode.setDescendant(dNode);
                dNode.addAscendent(aNode);
                // System.out.println("Test: " + dNode.getRole()
                // + dNode.listAscendants());
            }

            ++lineNumber;
        }

        // Always close the input file.
        inputFile.close();

        // Re-run the method if invalid input is found,
        // otherwise print the tree.
        if (invalidInput) {
            System.out.println("Invalid line is found in " + HIERARCHY
                    + " line " + lineNumber + ":" + "\r\n" + input + "\r\n"
                    + "Please fix the line,"
                    + " and press enter to read the file again.");
            (new Scanner(System.in)).nextLine();
            setUpRoleHierarchy();
        } else {
            roleTree = aNode;
            while (roleTree.getDescendant() != null) {
                roleTree = roleTree.getDescendant();
            }

            System.out.println("The resulting hierarchy tree is: " + "\r\n"
                    + roleTree.toString());
        }
    }

    /**
     * 
     */
    public void setUpRoleAccessMatrix() throws IOException {
        Scanner inputFile = new Scanner(new File(OBJECTS));
        ArrayList<String> objectList = new ArrayList<>();
        boolean invalidInput = false;
        String temp = "";

        // Read the file while checking for invalid entries
        while (inputFile.hasNext()) {
            temp = inputFile.next();
            if (objectList.contains(temp)) {
                invalidInput = true;
                break;
            } else {
                objectList.add(temp);
            }
        }

        // Always close the input file.
        inputFile.close();

        // Re run the method if invalid input is found,
        // otherwise create and display the access matrix
        if (invalidInput) {
            System.out.println("Duplicate object is found in " + OBJECTS + ": "
                    + temp + "\r\n" + "Please delete or fix the object,"
                    + " and press enter to read the file again.");
            (new Scanner(System.in)).nextLine();
            setUpRoleAccessMatrix();
        } else {
            ArrayList<HierarchyTree> nodes = roleTree.asList();
            ArrayList<String> roles = new ArrayList<>();
            for (HierarchyTree a : nodes) {
                // System.out.print(a.getRole() + " ");
                roles.add(a.getRole());
            }
            // System.out.println();
            globalRoles = roles;
            globalObjects = objectList;
            accessRights = new AccessMatrix(roles, objectList);
            System.out.println("\r\n" + "The current access matrix is: "
                    + "\r\n" + accessRights.toString());
        }
    }

    /**
     * Add all the permissions from a file.
     */
    public void setUpPermissions() throws IOException {
        ArrayList<String> accessRightList = new ArrayList<>();
        accessRights.grantDefaultControl();
        accessRights.grantOwnershipToDescendents(roleTree);
        Scanner inputFile = new Scanner(new File(PERMISSIONS));

        // System.out.println("Adding rights from file: \r\n\r\n");
        while (inputFile.hasNext()) {
            String input = inputFile.nextLine();

            // Add the access right if it is not a duplicate
            if (!accessRightList.contains(input)) {
                accessRightList.add(input);
                // System.out.println(input);
                String subj = input.split("\t+| +")[0];
                String right = input.split("\t+| +")[1];
                String obj = input.split("\t+| +")[2];

                // System.out.println("Adding right...");
                accessRights.addRight(subj, obj, right);
            }
        }
        // Always close the file
        inputFile.close();

        // Debug the right list
        // System.out.println("***************************************" + "\r\n"
        // + "***************************************" + "\r\n"
        // + "***************************************" + "\r\n");
        // System.out.println("\r\n" + "The current access matrix is: " + "\r\n"
        // + accessRights.toString());

        // Inherit all the rights.
        accessRights.assessPermissions(roleTree);

        // Print the access matrix
        // System.out.println("Access rights added");
        // System.out.println(accessRights.getRightsAsString("R3", "P2"));
        System.out.println("The updated access matrix is: " + "\r\n"
                + accessRights.toString());
        // System.out.println(accessRights.hasRight("R3", "wake", "P2"));
        // System.out.println(accessRights.getRightsAsString("R3", "P2"));
    }

    /**
     * Read all the constraints from a file.
     */
    public void setUpConstraints() throws IOException {
        int lineNumber = 1;
        boolean invalidInput = false;

        roleConstraints = new ArrayList<>();
        Scanner inputFile = new Scanner(new File(SSD));
        int cardinality = 0;
        while (inputFile.hasNext()) {
            cardinality = inputFile.nextInt();
            String[] temp = inputFile.nextLine().replaceFirst("\t+| +", "")
                    .split("\t+| +");
            ArrayList<String> roleSet = new ArrayList<>(Arrays.asList(temp));
            roleConstraints
                    .add(new Constraint(lineNumber, cardinality, roleSet));
            if (cardinality < 2) {
                invalidInput = true;
                break;
            }
            ++lineNumber;
        }

        // Always close the input file.
        inputFile.close();

        // Re run the method if input is invalid
        if (invalidInput) {
            System.out.println("Invalid line is found in " + SSD + " line "
                    + lineNumber + ":" + "\r\n" + cardinality + " < 2" + "\r\n"
                    + "Please fix the line,"
                    + " and press enter to read the file again.");
            (new Scanner(System.in)).nextLine();
            setUpConstraints();
        } else {
            for (Constraint constraint : roleConstraints) {
                System.out.println(constraint.toString());
            }
            System.out.println();
        }
    }

    /**
     * 
     */
    public void setUpUsers() throws IOException {
        Scanner inputFile = new Scanner(new File(USERS_TO_ROLES));
        ArrayList<String> users = new ArrayList<>();
        ArrayList<ArrayList<String>> usersRoleLists = new ArrayList<>();
        boolean invalidInput = false;
        String invalidMessage = "";
        int lineNumber = 1;
        int invalidLineNumber = lineNumber;

        while (inputFile.hasNext() && !invalidInput) {
            String user = inputFile.next();
            String[] temp = inputFile.nextLine().replaceFirst("\t+| +", "")
                    .split("\t+| +");
            ArrayList<String> roleSet = new ArrayList<>(Arrays.asList(temp));
            usersRoleLists.add(roleSet);
            if (users.contains(user)) {
                invalidInput = true;
                invalidMessage = "duplicate user " + user;
                invalidLineNumber = lineNumber;
            } else {
                users.add(user);
                for (Constraint constraint : roleConstraints) {
                    if (constraint.violatesConstraint(roleSet)) {
                        invalidInput = true;
                        invalidMessage = "constraint #" + constraint.label;
                        invalidLineNumber = lineNumber;
                        break;
                    }
                }
            }
            ++lineNumber;
        }

        // Always close the input file.
        inputFile.close();

        if (invalidInput) {
            System.out.println("Invalid line is found in " + USERS_TO_ROLES
                    + " line " + invalidLineNumber + ":" + "\r\n"
                    + invalidMessage + "\r\n" + "Please fix the line,"
                    + " and press enter to read the file again.");
            (new Scanner(System.in)).nextLine();
            setUpUsers();
        } else {
            ArrayList<HierarchyTree> nodes = roleTree.asList();
            ArrayList<String> roles = new ArrayList<>();
            for (HierarchyTree node : nodes) {
                roles.add(node.getRole());
            }
            globalUsers = users;
            userRoles = new AccessMatrix(users, roles, 5);

            for (int i = 0; i < usersRoleLists.size(); i++) {
                ArrayList<String> roleList = usersRoleLists.get(i);
                String user = users.get(i);
                for (String role : roleList) {
                    userRoles.addRight(user, role, "+");
                }
            }
            System.out.println("The user-role matrix is: " + "\r\n"
                    + userRoles.toString());
        }
    }

    /**
     * Repeatedly query the user to simulate a RBAC system.
     */
    public void query() {
        System.out.println(accessRights.hasRight("R4", "P2", "wake"));
        Scanner keyboard = new Scanner(System.in);
        boolean validInput = false;
        boolean repeatQuery = true;

        while (!validInput && repeatQuery) {
            System.out.print("Please enter the user in your query: ");
            String user = keyboard.nextLine();
            System.out.print("Please enter the object in your query "
                    + "(hit enter if it's for any): ");
            String object = keyboard.nextLine();
            System.out.print("Please enter the access right in your query"
                    + "(hit enter if it's for any): ");
            String accessRight = keyboard.nextLine();

            if (!globalUsers.contains(user)) {
                System.out.println("Invalid user, try again" + "r\n");
                // keyboard.nextLine();
                validInput = false;
            } else if (!globalObjects.contains(object) && !object.equals("")) {
                System.out.println("Invalid object, try again" + "\r\n");
                // keyboard.nextLine();
                validInput = false;
            } else {
                validInput = true;
            }

            if (repeatQuery && validInput) {
                // Get all the roles this user has
                ArrayList<String> rolesToQuery = new ArrayList<>();
                for (String role : globalRoles) {
                    if (userRoles.hasRight(user, role, "+")) {
                        rolesToQuery.add(role);
                        System.out
                                .println("User " + user + " has role " + role);
                    }
                }

                // Decide if we are querying for one object or all of them.
                String oQMode = "";
                ArrayList<String> obToQuery = new ArrayList<>();
                ArrayList<String> rightsToList = new ArrayList<String>();
                if (object.equals("")) {
                    oQMode = "any of the objects";
                    for (String temp : globalRoles) {
                        obToQuery.add(temp);
                    }
                    for (String temp : globalObjects) {
                        obToQuery.add(temp);
                    }
                    // obToQuery = globalObjects;
                } else {
                    oQMode = "the object: " + object;
                    obToQuery.add(object);
                }

                // Decide if we are listing rights or querying a specific one.
                String aQMode = "";
                if (accessRight.equals("")) {
                    for (String obj : obToQuery) {
                        for (String role : rolesToQuery) {
                            String tempr = accessRights.getRightsAsString(role,
                                    obj);
                            if (!tempr.equals("")) {
                                rightsToList.add("Rights to object " + obj
                                        + ": " + tempr);
                            }
                        }
                    }
                    aQMode = " any access rights user " + user + " has for ";
                } else {
                    for (String obj : obToQuery) {
                        for (String role : rolesToQuery) {
                            if (accessRights.hasRight(role, obj, accessRight)) {
                                rightsToList.add("User has the right "
                                        + accessRight + " to " + obj);
                                break;
                            } else {
                                rightsToList
                                        .add("User does NOT have the right "
                                                + accessRight + " to " + obj);
                            }
                        }
                    }
                    aQMode = " if the user " + user + " has the access right "
                            + "\"" + accessRight + "\"" + " to ";
                }

                System.out.println("Listing" + aQMode + oQMode);
                for (String right : rightsToList) {
                    System.out.println(right);
                }

                // Ask user if they want to continue.
                System.out.print("Would you like to continue"
                        + " for the next query? ");
                String response = keyboard.nextLine();
                if (!response.toLowerCase().equals("yes")) {
                    repeatQuery = false;
                    validInput = true;
                } else {
                    validInput = false;
                }
            }
        }

        // Close the input stream.
        keyboard.close();

        // We're done here.
    }
}
