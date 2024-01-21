package il.ac.afeka.usersservice.data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "departments")

public class DepartmentEntity {
    @Id
    private String deptId;
    private String departmentName;
    private String creationDate;

    public DepartmentEntity() {}
    public String getDeptId() {
        return deptId;
    }

    public void getDeptId(String email) {
        this.deptId = email;
    }
    public String getDepartmentName() {
        return departmentName;
    }

    public void getDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getCreationDate() {
        return creationDate;
    }

    public void getCreationDate(String lastname) {
        this.creationDate = creationDate;
    }
}
