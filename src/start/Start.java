package start;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.View;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Order;
import model.Product;


/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {

		
		
		//Client client1 = new Client("client2","cl2",50);//daca pun mai amre ca 70 validaotr => exceptie
		/*Client od = new Client();
		ClientBLL client1 = new ClientBLL();
		int ll = client1.insertClient(client);
		client1.updtaeClient(ll, "Alin", "Pop", 20);
		if(ll > 0) {
			System.out.println((client1.findClientById(ll)).toString());
		}
		client1.deleteClient(59);
		System.out.println((client1.findClientById(ll)).toString());
		*/
		//ClientDAO.insert(client1, 2);
		/*ClientBLL ord = new ClientBLL();
		ArrayList<Object> client = ord.findAllClients();
		for(Object obj: client) {
			System.out.println(obj);
		}*/
		
		/*Product product = new Product(1,2,3,"bluza");
		ProductBLL produs = new ProductBLL();
		
		int l2=produs.insertProducts(product);
		produs.updateProduct(l2, 5, "tricou");
		if(l2>0) {
			System.out.println((produs.findProductById(l2)).toString());
		}
		produs.deleteProduct(l2);
		System.out.println((produs.findProductById(l2)).toString());*/
		
		/*Order order = new Order(1,2,3,20);
		OrderBLL ord = new OrderBLL();
		
		int l3 = ord.insertOrders(order);
		ord.updtaeOrder(l3, 30);
		if(l3>0) {
			System.out.println((ord.findOrderById(l3)).toString());
		}
		ord.deleteOrder(l3);
		System.out.println((ord.findOrderById(l3)).toString());
		*/
		//obtain field-value pairs for object through reflection
		//ReflectionExample.retrieveProperties(product);
	}
	
	

}
