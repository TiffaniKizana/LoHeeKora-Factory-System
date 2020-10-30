import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

public class Main {

	Scanner scan = new Scanner(System.in);
	Vector<Employee> employeeList = new Vector<Employee>();
	Vector<Goods> itemList = new Vector<Goods>();
	
	public Main() {
		int mainMenu = 0;
		employeeList.add(addEmployeeData());
		employeeList.add(addEmployeeData2());
		itemList.add(addItemsData());
		do {
			mainMenu = mainMenu();
			switch (mainMenu) {
			case 1:
				employeeMenu();
				break;
			case 2:
				itemMenu();
				break;
			case 3:
				System.out.println("Thank you for using this program");
				System.exit(0);
				break;
			}
		} while (true);
	}

	private void itemMenu() {
		int chooseItem = 0;
		do {
			System.out.println("");
			System.out.println("");
			System.out.println("--------------------------");
			System.out.println("Item System Management");
			System.out.println("--------------------------");
			System.out.println("Please choose one option below :");
			System.out.println("1. View all item");
			System.out.println("2. Add new item");
			System.out.println("3. Remove item");
			System.out.println("4. Update item stock");
			System.out.println("5. Back to main page");
			System.out.print("Choose : ");
			try {
				chooseItem = scan.nextInt();
				while (chooseItem<1 || chooseItem>5) {
					scan.nextLine();
					System.out.println("Please input number between 1 and 5");
					System.out.print("Choose : ");
					chooseItem = scan.nextInt();
				}
			} catch (Exception e) {
				System.out.println("Please input in integer format");
			}
			scan.nextLine();

			switch (chooseItem) {
			case 1:
				viewItem();
				break;
			case 2:
				itemList.add(addItem());
				break;
			case 3:
				deleteItem();
				break;
			case 4 :
				updateStock();
				break;
			}
		} while (chooseItem != 5);		
	}

	private void updateStock() {
		if (employeeList.isEmpty())
			System.out.println("There is no data available to update the stock");
		else {
			viewItem();
			String iID;
			do {
				System.out.print("Input item ID to update the stock: ");
				iID = scan.nextLine();
			} while (iID.length()!=5 || !iID.startsWith("I"));
			
			int i;
			for (i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getiID().equals(iID)==true) {
					break;
				}
			}
			if (i == itemList.size()) {
				System.out.println("item with ID "+ iID +" not found");
				return;
			}
			int stock;
			System.out.print("Insert the new stock number: ");
			stock = scan.nextInt();
			scan.nextLine();
			itemList.get(i).setStock(stock);
			
			System.out.println("item with ID "+ iID +" successfuly updated");
		}
	}

	private void deleteItem() {
		if (employeeList.isEmpty())
			System.out.println("There is no data available");
		else {
			viewItem();
			String iID;
			do {
				System.out.print("Input item ID to delete: ");
				iID = scan.nextLine();
			} while (iID.length()!=5 || iID.startsWith("I"));
			
			int i;
			for (i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getiID().equals(iID)==true) {
					break;
				}
			}
			itemList.remove(i);
			System.out.println("item with ID "+ iID +" successfuly removed");
		}
	}

	private void viewItem() {
		if (itemList.isEmpty()) {
			System.out.println();
			System.out.println("No goods data available");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("===================================================================================================================");
			System.out.println("-------------------------------------------------------Item List---------------------------------------------------");
			System.out.println("===================================================================================================================");
			System.out.printf("| %-5s | %-30s | %-5s | %-10s | %-10s | %-5s | %-5s | %-30s | %-5s | %-10s |\n","ID","Name","Stock","Type","Price","PIC","SID","Supplier Name","MID","CreateDate");
			for (Goods item : itemList) {
				if (item instanceof RawGoods) {
					System.out.printf("| %-5s | %-30s | %-5d | %-10s | %-10d | %-5s | %-5s | %-30s | %-5s | %-10s |\n",item.getiID(),item.getiName(),item.getStock(),item.getType(),item.getPrice(),item.getPIC(),((RawGoods)item).getSupplierID(),((RawGoods)item).getSupplierName(),"-","-");
				}
				if (item instanceof FinishedGoods) {
					System.out.printf("| %-5s | %-30s | %-5d | %-10s | %-10d | %-5s | %-5s | %-30s | %-5s | %-10s |\n",item.getiID(),item.getiName(),item.getStock(),item.getType(),item.getPrice(),item.getPIC(),"-","-",((FinishedGoods)item).getMachineID(), ((FinishedGoods)item).getCreateDate() );
				}
			}
			System.out.println();
			System.out.println();
			System.out.println("Notes");
			System.out.println("SID : Supplier ID");
			System.out.println("MID : Machine ID");
			System.out.println();
			System.out.println();
		}
	}

	private Goods addItem() {
		String iID;
		String iName;
		String type;
		int stock;
		int price;
		String PIC;
		String supplierID;
		String supplierName;
		String machineID;
		String createDate;
		
		boolean isNewId=false;
		do {
			do {
				System.out.print("ID (start with 'I'): ");
				iID = scan.nextLine();
			} while (iID.length()!=5 || !iID.startsWith("I"));
			
			for (int i = 0; i < itemList.size(); i++) {
				if (itemList.get(i).getiID().equals(iID)) {
					System.out.println("There is another item with the same ID");
					break;
				} else {
					isNewId = true;
				}
			}
		} while (isNewId==false);
		
		do {
			System.out.print("Item Name (min 3 max 30 character): ");
			iName= scan.nextLine();
		} while (iName.length()<3 || iName.length()>30);
		
		do {
			System.out.print("Stock (pcs): ");
			stock= scan.nextInt();
			scan.nextLine();
		} while (stock<0);
		
		do {
			System.out.print("Price per pcs: ");
			price = scan.nextInt();
			scan.nextLine();
		} while (price<1000);
		
		boolean verifyPIC = false;
		do {
			do {
				System.out.print("PIC ID (Employee ID): ");
				PIC = scan.nextLine();
			} while (PIC.length()!=5);
			for (Employee employee : employeeList) {
				if (employee.getEmployeeID().equals(PIC)) {
					verifyPIC=true;
					break;
				}
			}
		} while (verifyPIC==false);
		
		do {
			System.out.print("Type [raw | finished]: ");
			type = scan.nextLine();
			type = type.toLowerCase();
		} while (!type.equals("raw") && !type.equals("finished"));
		
		if (type.equals("raw")) {
			do {
				System.out.print("Supplier ID (start with 'S'): ");
				supplierID = scan.nextLine();
			} while (supplierID.length()!=5 || !supplierID.startsWith("S"));
			do {
				System.out.print("Supplier Name (min 3 max 30 characters): ");
				supplierName = scan.nextLine();
			} while (supplierName.length()<3 || supplierName.length()>30);
			
			RawGoods newRaw = new RawGoods(iID, iName, type, stock, PIC, price, supplierID, supplierName);
			return newRaw;
			
		} else {
			do {
				System.out.print("Machine ID (start with 'M'): ");
				machineID = scan.nextLine();
			} while (machineID.length()!=5 || !machineID.startsWith("M"));
			
			boolean successCreateDate = false;
			do {
				Integer date, month, year;
				int thisYear = Calendar.getInstance().get(Calendar.YEAR);
				do {
					System.out.print("Create date [1-31]: ");
					date = scan.nextInt();
					scan.nextLine();
				} while (date<1 || date>31);
				do {
					System.out.print("Create month [1-12]: ");
					month = scan.nextInt();
					scan.nextLine();
				} while (month<1||month>12);
				do {
					System.out.print("Create year [yyyy]: ");
					year = scan.nextInt();
					scan.nextLine();
				} while (year>thisYear);
				createDate = date.toString() + '-' + month.toString() + '-' + year.toString();
				successCreateDate = true;
			} while (successCreateDate == false);
			
			FinishedGoods newFinishedGoods = new FinishedGoods(iID, iName, type, stock, PIC, price, machineID, createDate);
			return newFinishedGoods;
		}
//		return null;
	}

	private void employeeMenu() {
		int chooseEmployee = 0;
		do {
			System.out.println("");
			System.out.println("");
			System.out.println("--------------------------");
			System.out.println("Employee System Management");
			System.out.println("--------------------------");
			System.out.println("Please choose one option below :");
			System.out.println("1. View all employee");
			System.out.println("2. Add new employee");
			System.out.println("3. Change employee status");
			System.out.println("4. Remove employee");
			System.out.println("5. Back to main page");
			System.out.print("Choose : ");
			try {
				chooseEmployee = scan.nextInt();
				while (chooseEmployee<1 || chooseEmployee>5) {
					scan.nextLine();
					System.out.println("Please input number between 1 and 5");
					System.out.print("Choose : ");
					chooseEmployee = scan.nextInt();
				}
			} catch (Exception e) {
				System.out.println("Please input in integer format");
			}
			scan.nextLine();

			switch (chooseEmployee) {
			case 1:
				viewEmployee();
				break;
			case 2:
				employeeList.add(addEmployee());
				break;
			case 3:
				changeEmployeeStatus();
				break;
			case 4:
				deleteEmployee();
				break;
//			case 5 :
//				mainMenu();
//				break;
			}

		} while (chooseEmployee != 5);
	}

	private void deleteEmployee() {
		if (employeeList.isEmpty())
			System.out.println("There is no data available");
		else {
			viewEmployee();
			String employeeID;
			do {
				System.out.print("Input employee ID to delete: ");
				employeeID = scan.nextLine();
			} while (employeeID.length()!=5);
			
			int i;
			for (i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i).getEmployeeID().equals(employeeID)==true) {
					break;
				}
			}
			employeeList.remove(i);
			System.out.println("Successfully remove employee "+ employeeID);
		}
	}

	private void changeEmployeeStatus() {
		viewEmployee();
		String employeeID;
		do {
			System.out.print("Input employee ID to change the status (only for part time and internship employee): ");
			employeeID = scan.nextLine();
		} while (employeeID.length()!=5 && employeeID.startsWith("P")==false && employeeID.startsWith("I")==false);
		
		int i;
		for (i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getEmployeeID().equals(employeeID)==true) {
				break;
			}
		}
		if (i== employeeList.size()) {
			System.out.println("There is no employee with employee ID: "+ employeeID);
		}else {
			System.out.print("Are you sure want to change employee status for "+employeeList.get(i).getfName()+" to become fulltime? ");
			String check = scan.nextLine();
			
			if (check.toLowerCase().equals("yes") == true) {
				String position;
				int totalOvertimeHour, insuranceAmount;
				do {
					System.out.print("Position (max 20 characters): ");
					position = scan.nextLine();
				} while (position.length()<5 || position.length()>20);

				do {
					System.out.print("Total Overtime Hour(min 1 max 6): ");
					totalOvertimeHour = scan.nextInt();
					scan.nextLine();
				} while (totalOvertimeHour<1 ||totalOvertimeHour>6);

				do {
					System.out.print("Health insurance amount(min Rp.300.000) : ");
					insuranceAmount = scan.nextInt();
					scan.nextLine();
				} while (insuranceAmount<30000);
				if (employeeID.startsWith("P")) {
					FullTime changeStatus = new FullTime(employeeList.get(i).getEmployeeID().replaceFirst("P", "F"), employeeList.get(i).getfName(), employeeList.get(i).getGender(), employeeList.get(i).getDOB(), employeeList.get(i).getMaritalStatus(), employeeList.get(i).getEmail(), employeeList.get(i).getPhoneNum(), employeeList.get(i).getStartDate(), employeeList.get(i).getDepartment(), employeeList.get(i).getWorkHourPerDay(), employeeList.get(i).getWorksShift(), employeeList.get(i).getTotalDayOff(), 100000, position, totalOvertimeHour, insuranceAmount);
					employeeList.add(changeStatus);
					employeeList.remove(i);
				}
				if (employeeID.startsWith("I")){
					FullTime changeStatus = new FullTime(employeeList.get(i).getEmployeeID().replaceFirst("I", "F"), employeeList.get(i).getfName(), employeeList.get(i).getGender(), employeeList.get(i).getDOB(), employeeList.get(i).getMaritalStatus(), employeeList.get(i).getEmail(), employeeList.get(i).getPhoneNum(), employeeList.get(i).getStartDate(), employeeList.get(i).getDepartment(), employeeList.get(i).getWorkHourPerDay(), employeeList.get(i).getWorksShift(), employeeList.get(i).getTotalDayOff(), 100000, position, totalOvertimeHour, insuranceAmount);
					employeeList.add(changeStatus);
					employeeList.remove(i);
				}
				System.out.println("Status change success");
			} else {
				System.out.println("Change employee status canceled");
			}
		}
	}

	private Employee addEmployee() {
		int chooseAdd = 0;
		System.out.println();
		System.out.println("What kind of employment?");
		System.out.println("1. Fulltime");
		System.out.println("2. Parttime");
		System.out.println("3. Intern");
		try {
			System.out.print("Choose : ");
			chooseAdd = scan.nextInt();
			while(chooseAdd<1 ||chooseAdd>3) {
				scan.nextLine();
				System.out.println("Input must be between 1-4");
				System.out.print("Choose : ");
				chooseAdd = scan.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Input must in integer/number format");
		}
		scan.nextLine();

		String employeeID;
		String fullName = "";
		String gender = "";
		String DOB;
		String email;
		String phoneNum;
		String startDate;
		String workDepartment;
		String workShift;
		int workHourPerDay;
		int totalDayOff;
		String maritalStatus;
		
		do {
			System.out.print("Full Name : ");
			fullName= scan.nextLine();
		} while (fullName.length()<3);

		//		Employee id nya di random
		boolean uniqueID = true;
		double randID;
		do {
			randID = Math.floor(Math.random()*999);
			String indicator;
			if (chooseAdd == 1) {
				indicator = "F";
			} else if (chooseAdd == 2) {
				indicator = "P";
			} else {
				indicator = "I";
			}
			employeeID = indicator + fullName.toUpperCase().charAt(0) + (int)randID;
			for (int i = 0; i < employeeList.size(); i++) {
				if(employeeList.get(i).getEmployeeID().equals(employeeID)) {
					uniqueID = false;
				}
			}
		} while (!uniqueID);

		do {
			System.out.print("Gender [Male|Female]: ");
			gender = scan.nextLine();
		} while (!gender.equals("Male") && !gender.equals("Female"));

		do {
			System.out.print("Marital status [Single|Married]: ");
			maritalStatus = scan.nextLine();
		} while (!maritalStatus.equals("Single") && !maritalStatus.equals("Married"));

		do {
			System.out.print("Department [operational|quality control]: ");
			workDepartment = scan.nextLine();
		} while (workDepartment.toLowerCase().equals("operational")==false && workDepartment.toLowerCase().equals("quality control")==false);

		do {
			System.out.print("Working shift [Moning|Night|Normal]: ");
			workShift = scan.nextLine();
		} while (!workShift.toLowerCase().equals("morning") && !workShift.toLowerCase().equals("night") && !workShift.toLowerCase().equals("normal"));

		do {
			System.out.print("Work hour per day(min 4 max 8): ");
			workHourPerDay = scan.nextInt(); 
			scan.nextLine();
		} while (workHourPerDay <4 || workHourPerDay>8);

		do {
			System.out.print("Total day off: ");
			totalDayOff = scan.nextInt();
			scan.nextLine();
		} while (totalDayOff<0);

		boolean successDOB = false;
		do {
			Integer date, month, year;
			int thisYear = Calendar.getInstance().get(Calendar.YEAR);
			do {
				System.out.print("DOB date [1-31]: ");
				date = scan.nextInt();
				scan.nextLine();
			} while (date<1 || date>31);
			do {
				System.out.print("DOB month [1-12]: ");
				month = scan.nextInt();
				scan.nextLine();
			} while (month<1||month>12);
			do {
				System.out.print("DOB year [yyyy]: ");
				year = scan.nextInt();
				scan.nextLine();
			} while (year<1930 || year>(thisYear-17));
			DOB = date.toString() + '-' + month.toString() + '-' + year.toString();
			successDOB = true;
		} while (successDOB == false);

		do {
			System.out.print("Email : ");
			email=scan.nextLine();
		} while (!(isValid(email)));

		do {
			System.out.print("Phone number (min 10 digit): ");
			phoneNum = scan.nextLine();
		} while (phoneNum.chars().allMatch(Character::isDigit)==false || phoneNum.length()<10);

		boolean successStartDate = false;
		do {
			Integer date, month, year;
			do {
				System.out.print("start date [1-31]: ");
				date = scan.nextInt();
				scan.nextLine();
			} while (date<1 || date>31);
			do {
				System.out.print("start month [1-12]: ");
				month = scan.nextInt();
				scan.nextLine();
			} while (month<1||month>12);

			do {
				System.out.print("start year [yyyy]: ");
				year = scan.nextInt();
				scan.nextLine();
			} while (year<1930);
			startDate = date.toString() + '-' + month.toString() + '-' + year.toString();
			successStartDate= true;
		} while (successStartDate == false);

		if (chooseAdd == 1) {
			String position;
			int totalOvertimeHour;
			int insuranceAmount;
			int salaryPerMonth;

			do {
				System.out.print("Position: ");
				position = scan.nextLine();
			} while (position.length()<5 || position.length()>20);

			do {
				System.out.print("Total Overtime Hour(min 1 max 6): ");
				totalOvertimeHour = scan.nextInt();
				scan.nextLine();
			} while (totalOvertimeHour<1 ||totalOvertimeHour>6);

			do {
				System.out.print("Health insurance amount (min Rp.300.000): ");
				insuranceAmount = scan.nextInt();
				scan.nextLine();
			} while (insuranceAmount<300000);
			
			FullTime newFullTime = new FullTime(employeeID, fullName, gender, DOB, maritalStatus, email, phoneNum, startDate, workDepartment, workHourPerDay, workShift, totalDayOff, 100000, position, totalOvertimeHour, insuranceAmount);
			return newFullTime;
		} 
		else {
			String supervisorID;
			String supervisorName = "";
			boolean realSupervisor = false;
			boolean correctSupervisor = false;
			do {
				System.out.print("Supervisor ID : ");
				do {
					supervisorID = scan.nextLine();
					if (!(supervisorID.startsWith("F"))) {
						System.out.println("Wrong format for supervisor ID. Please reinput again");
					} else {
						realSupervisor = true;
						break;
					}
				} while (realSupervisor == false);
				
				int i;
				for (i = 0; i < employeeList.size(); i++) {
					if (employeeList.get(i).getEmployeeID() == supervisorID.toUpperCase()) {
						correctSupervisor = true;
						supervisorName = employeeList.get(i).getfName();
						break;
					}
				}
				if (i== employeeList.size()-1) {
					System.out.println("There is no match supervisor ID");
				} else {
					correctSupervisor = true;
				}
			} while (correctSupervisor== false);

			if (chooseAdd == 2) {
				int totalDayPerWeek;
				int partTimeContract;

				do {
					System.out.print("Total Day Per Week: ");
					totalDayPerWeek = scan.nextInt();
					scan.nextLine();
				} while (totalDayPerWeek<3 || totalDayPerWeek>5);

				do {
					System.out.print("Contract (in month): ");
					partTimeContract = scan.nextInt();
					scan.nextLine();
				} while (partTimeContract<1 || partTimeContract>6);
				PartTime nPartTime = new PartTime(employeeID, fullName, gender, DOB, maritalStatus, email, phoneNum, startDate, workDepartment, workHourPerDay, workShift, totalDayOff, 70000, supervisorID, 	supervisorName, totalDayPerWeek, partTimeContract);
				return nPartTime;
			} else {
				String uniOrigin;
				int internDuration;

				do {
					System.out.print("University origin: ");
					uniOrigin = scan.nextLine();
				} while (uniOrigin.length()<5 || uniOrigin.length()>20);

				do {
					System.out.print("Intern duration: ");
					internDuration =scan.nextInt();
					scan.nextLine();
				} while (internDuration<3 || internDuration>12);
				Internship nInternship = new Internship(employeeID, fullName, gender, DOB, maritalStatus, email, phoneNum, startDate, workDepartment, workHourPerDay, workShift, totalDayOff, 50000, supervisorID, supervisorName, uniOrigin, internDuration);
				return nInternship;
			}
		}
	}

	static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
	
	private void viewEmployee() {
		if (employeeList.isEmpty()) {
			System.out.println("There is no employee data available");
		} else {
			System.out.println();
			System.out.println();
			System.out.println("====================================================================================================================================================================================================================");
			System.out.println("---------------------------------------------------------------------------------------------Employees Data---------------------------------------------------------------------------------------------------------");
			System.out.println("====================================================================================================================================================================================================================");
			System.out.printf("| %-5s | %-30s | %-6s | %-20s | %-7s | %-4s | %-4s | %-10s | %-30s | %-14s | %-20s | %-4s | %-10s | %-5s | %-4s | %-8s | %-20s | %-15s |\n", "ID", "Name","Gender","Department","Shift","WH","DO","Start Date","Email","Phone No","Position","OTH","IA","SID","DPW","Contract","UO","Salary");

			for (Employee employee : employeeList) {
				if (employee instanceof FullTime) {
					System.out.printf("| %-5s | %-30s | %-6s | %-20s | %-7s | %-4d | %-4d | %-10s | %-30s | %-14s | %-20s | %-4d | %-10d | %-5s | %-4d | %-8s | %-20s | %-15d |\n", employee.getEmployeeID(), employee.getfName(),employee.getGender(),employee.getDepartment(),employee.getWorksShift(),employee.getWorkHourPerDay(),employee.getTotalDayOff(),employee.getStartDate(),employee.getEmail(),employee.getPhoneNum(),((FullTime)employee).getPosition(),((FullTime)employee).getTotalOvertimeHour(),((FullTime)employee).getInsuranceAmount(),"-",5,"-","-",((FullTime)employee).salaryPerMonth());
				}
				if (employee instanceof PartTime) {
					System.out.printf("| %-5s | %-30s | %-6s | %-20s | %-7s | %-4d | %-4d | %-10s | %-30s | %-14s | %-20s | %-4d | %-10d | %-5s | %-4d | %-8d | %-20s | %-15d |\n", employee.getEmployeeID(), employee.getfName(),employee.getGender(),employee.getDepartment(),employee.getWorksShift(),employee.getWorkHourPerDay(),employee.getTotalDayOff(),employee.getStartDate(),employee.getEmail(),employee.getPhoneNum(),"-",0,0,((PartTime)employee).getSupervisorID(),((PartTime)employee).getTotalDayPerWeek(),((PartTime)employee).getPartTimeContract(),"-",((PartTime)employee).salaryPerMonth());
				}
				if (employee instanceof Internship) {
					System.out.printf("| %-5s | %-30s | %-6s | %-20s | %-7s | %-4d | %-4d | %-10s | %-30s | %-14s | %-20s | %-4d | %-10d | %-5s | %-4d | %-8d | %-20s | %-15d |\n", employee.getEmployeeID(), employee.getfName(),employee.getGender(),employee.getDepartment(),employee.getWorksShift(),employee.getWorkHourPerDay(),employee.getTotalDayOff(),employee.getStartDate(),employee.getEmail(),employee.getPhoneNum(),"-",0,0,((Internship)employee).getSupervisorID(),5,((Internship)employee).getInternDuration(),((Internship)employee).getUniOrigin(),((Internship)employee).salaryPerMonth());
				}
			}
			System.out.println();
			System.out.println("Notes :");
			System.out.println("WH : Work Hour Per Day");
			System.out.println("DO : Total Day Off");
			System.out.println("OTH : Total Overtime Hour");
			System.out.println("IA : Insurance Amount");
			System.out.println("SID : Supervisor ID");
			System.out.println("DPW : total day per week ");
			System.out.println("UO : University Origin");
			System.out.println();
			System.out.println();
		}
	}

	private Employee addEmployeeData() {
		Internship nInternship = new Internship("IQ435", "qweqwe", "Female", "1-1-2000", "Single", "qwe@qew.qwe", "4564564564", "5-5-2019", "operational", 5, "Normal", 4, 50000, "FA213", "Andi Satria", "SUNIB", 3);
		return nInternship;
	}
	
	private Employee addEmployeeData2() {
		FullTime newFullTime = new FullTime("FA213", "Andi Satria", "Male", "2-2-1950", "Married", "andi@andi.com", "1231231231", "2-2-2000", "operational", 6, "Night", 2, 100000, "HEads", 2, 300000);
		return newFullTime;
	}
	
	private Goods addItemsData() {
		RawGoods newRaw = new RawGoods("I1231", "Glukose (bottle)", "raw", 524, "IQ435", 500000, "S4444", "PT. Sugaria Indonesia");
		return newRaw;
	}
	
	private int mainMenu() {
		int chooseMain=0;
		System.out.println();
		System.out.println();
		System.out.println("======================================");
		System.out.println("=== Welcome to Kora Factory System ===");
		System.out.println("======================================");
		System.out.println("Please choose what you want to modify:");
		System.out.println("1. Employee");
		System.out.println("2. Goods/Items");
		System.out.println("3. Exit");
		System.out.print("Choose : ");

		try {
			chooseMain = scan.nextInt();
			while (chooseMain<1 || chooseMain>3) {
				scan.nextLine();
				System.out.println("Please input number between 1 and 3");
				System.out.print("Choose : ");
				chooseMain = scan.nextInt();
			}
		} catch (Exception e) {
			System.out.println("Please input in integer format");
		}
		scan.nextLine();
		return chooseMain;
	}

	public static void main(String[] args) {
		new Main();
	}
}
