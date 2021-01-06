package model;

public class Order {

	private int idOrder;
	private int idClient;
	private int idProduct;
	private int cantitate;
	
	public Order(int idO,int idC,int idP,int cantitate) {
		this.idOrder=idO;
		this.idClient=idC;
		this.idProduct=idP;
		this.cantitate=cantitate;
	}
	
	public Order(int idC,int idP,int cantitate) {
		this.idClient=idC;
		this.idProduct=idP;
		this.cantitate=cantitate;
	}

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", idClient=" + idClient + ", idProduct=" + idProduct + ", cantitate="
				+ cantitate + "]";
	}
	
	
}
