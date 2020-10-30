
public class PartTime extends Employee{
	private String supervisorID;
	private String supervisorName;
	private int totalDayPerWeek;
	private int partTimeContract;
	
	public PartTime(String employeeID, String fName, String gender, String dOB, String maritalStatus, String email,
			String phoneNum, String startDate, String department, int workHourPerDay, String worksShift,
			int totalDayOff, int baseSalary, String supervisorID, String supervisorName, int totalDayPerWeek,
			int partTimeContract) {
		super(employeeID, fName, gender, dOB, maritalStatus, email, phoneNum, startDate, department, workHourPerDay,
				worksShift, totalDayOff, baseSalary);
		this.supervisorID = supervisorID;
		this.supervisorName = supervisorName;
		this.totalDayPerWeek = totalDayPerWeek;
		this.partTimeContract = partTimeContract;
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

	public int getTotalDayPerWeek() {
		return totalDayPerWeek;
	}

	public void setTotalDayPerWeek(int totalDayPerWeek) {
		this.totalDayPerWeek = totalDayPerWeek;
	}

	public int getPartTimeContract() {
		return partTimeContract;
	}

	public void setPartTimeContract(int partTimeContract) {
		this.partTimeContract = partTimeContract;
	}

	@Override
	public int salaryPerMonth() {
		return (getBaseSalary()*getWorkHourPerDay()*getTotalDayPerWeek()*4);
	}
	
}
