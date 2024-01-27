package il.ac.afeka.usersservice.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")
public class DepartmentEntity {
    @Id
    private String deptId;
    private String departmentName;
    private String creationDate;

    public DepartmentEntity() {
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "DepartmentEntity {" +
                "deptId='" + deptId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
