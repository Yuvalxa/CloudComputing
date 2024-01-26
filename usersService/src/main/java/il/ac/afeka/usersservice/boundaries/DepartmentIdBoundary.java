package il.ac.afeka.usersservice.boundaries;

public class DepartmentIdBoundary {

	private String depId ;

	public DepartmentIdBoundary() {}

	public DepartmentIdBoundary(String depId) {
		this.depId = depId;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Override
	public String toString() {
		return "DepartmentIdBoundary{" +
				"depId='" + depId + '\'' +
				'}';
	}
}
