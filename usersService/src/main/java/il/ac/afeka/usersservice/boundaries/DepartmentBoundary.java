package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.data.DepartmentEntity;

import java.time.LocalDate;

public class DepartmentBoundary {

    private String deptId;
    private String departmentName;
    private String creationDate;

    public DepartmentBoundary() {
    }

    public DepartmentBoundary(DepartmentEntity dep) {
        this.deptId = dep.getDeptId();
        this.departmentName = dep.getDepartmentName();
        this.creationDate = dep.getCreationDate();
    }

    public String getDeptId() {
        return deptId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public DepartmentEntity toEntity() {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDeptId(this.getDeptId());
        entity.setDepartmentName(this.getDepartmentName());
        entity.setCreationDate(LocalDate.parse("dd-MM-yyyy").toString());

        return entity;
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