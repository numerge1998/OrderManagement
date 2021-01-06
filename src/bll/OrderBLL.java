package bll;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import dao.ClientDAO;
import dao.OrderDAO;
import model.Order;

public class OrderBLL {

	public Order findOrderById(int id) {
		Order order = OrderDAO.findById(id);
		if (order == null) {
			throw new NoSuchElementException("The order with id =" + id + " was not found!");
		}
		return order;
	}
	
	public ArrayList<Object> findAllOrders(){
		return OrderDAO.findAllOrd();
	}
	
	public int insertOrders(Order order) {
		int id2=1;
		while(OrderDAO.findById(id2)!=null)
			id2++;

		OrderDAO.insert(order,id2);
		return id2;
	}
	
	public void updtaeOrder(int id,int cantitate) {
		OrderDAO.update(id, cantitate);
	}
	
	public void deleteOrder(int id) {
		OrderDAO.delete(id);
	}
}
