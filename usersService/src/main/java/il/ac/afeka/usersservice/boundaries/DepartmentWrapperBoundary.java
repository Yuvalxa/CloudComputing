package il.ac.afeka.usersservice.boundaries;

public class DepartmentWrapperBoundary {
	private DepartmentIdBoundary department;

	public DepartmentWrapperBoundary() {
	}

	public DepartmentWrapperBoundary(DepartmentIdBoundary department) {
		this.department = department;
	}

	public DepartmentIdBoundary getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentIdBoundary department) {
		this.department = department;
	}
}
