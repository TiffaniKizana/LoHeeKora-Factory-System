
public class Internship extends Employee{
	private String supervisorID;
	private String supervisorName;
	private String uniOrigin;
	private int internDuration;
	
	public Internship(String employeeID, String fName, String gender, String dOB, String maritalStatus, String email,
			String phoneNum, String startDate, String department, int workHourPerDay, String worksShift,
			int totalDayOff, int baseSalary, String supervisorID, String supervisorName, String uniOrigin,
			int internDuration) {
		super(employeeID, fName, gender, dOB, maritalStatus, email, phoneNum, startDate, department, workHourPerDay,
				worksShift, totalDayOff, baseSalary);
		this.supervisorID = supervisorID;
		this.supervisorName = supervisorName;
		this.uniOrigin = uniOrigin;
		this.internDuration = internDuration;
	}

	public String getSupervisorID() {
		return supervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		this.supervisorID = supervisorID;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getUniOrigin() {
		return uniOrigin;
	}

	public void setUniOrigin(String uniOrigin) {
		this.uniOrigin = uniOrigin;
	}

	public int getInternDuration() {
		return internDuration;
	}

	public void setInternDuration(int internDuration) {
		this.internDuration = internDuration;
	}

	@Override
	public int salaryPerMonth() {
		return (getBaseSalary()*getWorkHourPerDay()*20);
	}
}
