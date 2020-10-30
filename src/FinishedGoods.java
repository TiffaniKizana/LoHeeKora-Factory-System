
public class FinishedGoods extends Goods {
	private String machineID;
	private String createDate;
	
	public FinishedGoods(String iID, String iName, String type, int stock, String pIC, int price, String machineID,
			String createDate) {
		super(iID, iName, type, stock, pIC, price);
		this.machineID = machineID;
		this.createDate = createDate;
	}
	
	public String getMachineID() {
		return machineID;
	}
	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
