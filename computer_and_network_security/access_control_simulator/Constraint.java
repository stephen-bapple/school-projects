import java.util.ArrayList;

public class Constraint {
    public int label;
    public int cardinality;
    public ArrayList<String> roleSet;

    public Constraint(int label, int cardinality, ArrayList<String> roleSet) {
        this.label = label;
        this.cardinality = cardinality;
        this.roleSet = roleSet;
    }

    public boolean violatesConstraint(ArrayList<String> rolesHeld) {
        int counter = 0;
        for (String role : rolesHeld) {
            if (roleSet.contains(role)) {
                ++counter;
            }
        }
        return counter >= cardinality;
    }

    @Override
    public String toString() {
        String roles = roleSet.get(0);
        for (int i = 1; i < roleSet.size(); i++) {
            roles = roles + ", " + roleSet.get(i);
        }
        return "Constraint " + label + " n = " + cardinality
                + ", set of roles = {" + roles + "}";
    }
}
