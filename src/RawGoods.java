
public class RawGoods extends Goods{
	private String supplierID;
	private String supplierName;
	
	public RawGoods(String iID, String iName, String type, int stock, String pIC, int price, String supplierID,
			String supplierName) {
		super(iID, iName, type, stock, pIC, price);
		this.supplierID = supplierID;
		this.supplierName = supplierName;
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}
