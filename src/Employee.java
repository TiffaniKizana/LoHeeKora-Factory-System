
public abstract class Employee {
	private String employeeID;
	private String fName;
	private String gender;
	private String DOB;
	private String maritalStatus;
	private String email;
	private String phoneNum;
	private String startDate;
	private String department;
	private int workHourPerDay;
	private String worksShift;
	private int totalDayOff;
	private int baseSalary;
	public abstract int salaryPerMonth();
	
	public Employee(String employeeID, String fName, String gender, String dOB, String maritalStatus, String email,
			String phoneNum, String startDate, String department, int workHourPerDay, String worksShift,
			int totalDayOff, int baseSalary) {
		super();
		this.employeeID = employeeID;
		this.fName = fName;
		this.gender = gender;
		this.DOB = dOB;
		this.maritalStatus = maritalStatus;
		this.email = email;
		this.phoneNum = phoneNum;
		this.startDate = startDate;
		this.department = department;
		this.workHourPerDay = workHourPerDay;
		this.worksShift = worksShift;
		this.totalDayOff = totalDayOff;
		this.baseSalary = baseSalary;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getWorkHourPerDay() {
		return workHourPerDay;
	}
	public void setWorkHourPerDay(int workHourPerDay) {
		this.workHourPerDay = workHourPerDay;
	}
	public String getWorksShift() {
		return worksShift;
	}
	public void setWorksShift(String worksShift) {
		this.worksShift = worksShift;
	}
	public int getTotalDayOff() {
		return totalDayOff;
	}
	public void setTotalDayOff(int totalDayOff) {
		this.totalDayOff = totalDayOff;
	}
	public int getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	
}
