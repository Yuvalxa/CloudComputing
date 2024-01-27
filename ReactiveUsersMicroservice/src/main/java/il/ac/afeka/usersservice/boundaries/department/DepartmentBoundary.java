package il.ac.afeka.usersservice.boundaries.department;

import il.ac.afeka.usersservice.data.DepartmentEntity;
import il.ac.afeka.usersservice.util.DateUtils;
import il.ac.afeka.usersservice.util.StringChecker;
import il.ac.afeka.usersservice.util.exceptions.InvalidInputException;

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
        if (StringChecker.isValidDepartmentName(departmentName))
            this.departmentName = departmentName;
        else
            throw new InvalidInputException("Invalid department name");
    }

    public void setCreationDate(String creationDate) {
        if (DateUtils.isValidDate(creationDate))
            this.creationDate = creationDate;
        else
            throw new InvalidInputException("Invalid creation date");
    }


    public DepartmentEntity toEntity() {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDeptId(this.getDeptId());
        entity.setDepartmentName(this.getDepartmentName());
        entity.setCreationDate(DateUtils.toValidDateString(LocalDate.now()));

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