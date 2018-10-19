import java.util.ArrayList;

public class RightList {
    public String obj;
    public String subj;
    public ArrayList<String> accessRights;
    
    public RightList(String subj, String obj) {
        this.obj = obj;
        this.subj = subj;
        this.accessRights = new ArrayList<>();
    }
    
    public RightList(String subj, String obj, ArrayList<String> accessRights) {
        this.obj = obj;
        this.subj = subj;
        this.accessRights = accessRights;
    }
    
    public RightList(String subj, String obj, String accessRight) {
        this.obj = obj;
        this.subj = subj;
        this.accessRights = new ArrayList<>();
        this.accessRights.add(accessRight);
    }
    
    public String getSubject() {
        return this.subj;
    }
    
    public ArrayList<String> getRights() {
        return this.accessRights;
    }
    
    public void addRight(String accessRight) {
        this.accessRights.add(accessRight);
    }
    
    public void deleteRight(String accessRight) {
        this.accessRights.remove(accessRight);
    }
    
    public void clearAllRights() {
        accessRights = new ArrayList<>();
    }
    @Override
    public String toString() {
        String rtnStr = "";
        for (String right : accessRights) {
            rtnStr = rtnStr + right;
        }
        
        return rtnStr;
    }

}
