package model;

public class Product {

	private int idProduct;
	private int pret;
	private int stoc;
	private String nume;
	
	public Product(int idProduct, int pret, int stoc, String nume) {
		super();
		this.idProduct = idProduct;
		this.pret = pret;
		this.stoc = stoc;
		this.nume = nume;
	}

	public Product(int pret, int stoc, String nume) {
		super();
		this.pret = pret;
		this.stoc = stoc;
		this.nume = nume;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public int getStoc() {
		return stoc;
	}

	public void setStoc(int stoc) {
		this.stoc = stoc;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", pret=" + pret + ", stoc=" + stoc + ", nume=" + nume + "]";
	}


	
	
}
