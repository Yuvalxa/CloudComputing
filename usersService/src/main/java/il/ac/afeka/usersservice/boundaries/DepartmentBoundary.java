package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.data.DepartmentEntity;

import java.util.Arrays;

public class DepartmentBoundary {

    private String deptId ;
    private String departmentName;
    private String creationDate;

    public DepartmentBoundary(){}

    public DepartmentBoundary(DepartmentEntity dep) {
        this.deptId = dep.getDeptId();
        this.departmentName = dep.getDepartmentName();
        this.creationDate = dep.getCreationDate();
    }

    @Override
    public String toString() {
        return "UserBoundary {" +
                "department id='" + deptId + '\'' +
                ", department name=" + departmentName +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

}
