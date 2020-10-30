
public class Goods {
	private String iID;
	private String iName;
	private String type;
	private int stock;
	private String PIC;
	private int price;
	
	public Goods(String iID, String iName, String type, int stock, String pIC, int price) {
		super();
		this.iID = iID;
		this.iName = iName;
		this.type = type;
		this.stock = stock;
		PIC = pIC;
		this.price = price;
	}

	public String getiID() {
		return iID;
	}

	public void setiID(String iID) {
		this.iID = iID;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getPIC() {
		return PIC;
	}

	public void setPIC(String pIC) {
		PIC = pIC;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
