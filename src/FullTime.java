
public class FullTime extends Employee{
	private String position;
	private int totalOvertimeHour;
	private int insuranceAmount;
	
	public FullTime(String employeeID, String fName, String gender, String dOB, String maritalStatus, String email,
			String phoneNum, String startDate, String department, int workHourPerDay, String worksShift,
			int totalDayOff, int baseSalary, String position, int totalOvertimeHour, int insuranceAmount) {
		super(employeeID, fName, gender, dOB, maritalStatus, email, phoneNum, startDate, department, workHourPerDay,
				worksShift, totalDayOff, baseSalary);
		this.position = position;
		this.totalOvertimeHour = totalOvertimeHour;
		this.insuranceAmount = insuranceAmount;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getTotalOvertimeHour() {
		return totalOvertimeHour;
	}

	public void setTotalOvertimeHour(int totalOvertimeHour) {
		this.totalOvertimeHour = totalOvertimeHour;
	}

	public int getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(int insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	@Override
	public int salaryPerMonth() {
		return (getBaseSalary()*getWorkHourPerDay()*20) + ((3/2)*(1/173)*getTotalOvertimeHour()*(getBaseSalary()*getWorkHourPerDay()*20));
	}
}
