package il.ac.afeka.usersservice.boundaries;

import il.ac.afeka.usersservice.data.DepartmentEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public static String changeDateFormat(String inputDate) {
        // Parse the input date string to a LocalDate object
        LocalDate localDate = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);

        // Format the LocalDate object to the desired output format
        String outputDate = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return outputDate;
    }

    public DepartmentEntity toEntity() {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDeptId(this.getDeptId());
        entity.setDepartmentName(this.getDepartmentName());
        entity.setCreationDate(changeDateFormat(LocalDate.now().toString()));

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