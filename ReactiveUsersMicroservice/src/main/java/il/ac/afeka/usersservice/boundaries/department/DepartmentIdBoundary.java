package il.ac.afeka.usersservice.boundaries.department;

public class DepartmentIdBoundary {

    private String deptId;

    public DepartmentIdBoundary() {
    }

    public DepartmentIdBoundary(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "DepartmentIdBoundary{" +
                "depId='" + deptId + '\'' +
                '}';
    }
}
