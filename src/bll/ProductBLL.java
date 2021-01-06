package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import dao.ClientDAO;
import dao.ProductDAO;
import model.Product;

public class ProductBLL {


	public Product findProductById(int id) {
		Product product = ProductDAO.findById(id);
		if (product == null) {
			return null;
			//throw new NoSuchElementException("The product with id =" + id + " was not found!");
		}
		return product;
	}
	
	public ArrayList<Object> findAllProducts(){
		return ProductDAO.findAllProd();
	}
	
	public int insertProducts(Product product) {
		int id3=1;
		while(ProductDAO.findById(id3)!=null)
			id3++;

		ProductDAO.insert(product,id3);
		return id3;
	}
	
	public void updateProduct(int id,int stoc) {
		ProductDAO.update(id, stoc);
	}
	
	public void deleteProduct(int id) {
		ProductDAO.delete(id);
	}
}
