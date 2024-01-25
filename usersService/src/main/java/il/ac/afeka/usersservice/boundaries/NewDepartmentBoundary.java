package il.ac.afeka.usersservice.boundaries;

public class NewDepartmentBoundary extends DepartmentBoundary {

    private String deptId;

    public NewDepartmentBoundary() {
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        String userDepartmentString = super.toString();
        return "New" +
                userDepartmentString.substring(0, userDepartmentString.length() - 1) + // removes '}' in parent's toString()
                ", department Id=" + this.deptId +
                '}';
    }
}
