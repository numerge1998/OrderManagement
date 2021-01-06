package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;
import java.util.Random; 



public class ClientBLL {
	
	
	private List<Validator<Client>> validators;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientAgeValidator());
	}
	
	public Client findClientById(int id) {
		Client client = ClientDAO.findById(id);
		if (client == null) {
			//throw new NoSuchElementException("The client with id =" + id + " was not found!");
			return null;
		}
		return client;
	}
	
	public ArrayList<Object> findAllClients(){
		return ClientDAO.findAll();
	}
	
	public int insertClient(Client client) {
		int id2 = 1;
		for (Validator<Client> v : validators) {
			v.validate(client);
		}
		while(ClientDAO.findById(id2)!=null)
			id2++;
		ClientDAO.insert(client,id2);
		return id2;
	}
	
	public void updtaeClient(int id,String nume,String prenume,int varsta) {
		ClientDAO.update(id, nume, prenume, varsta);
	}
	
	public void deleteClient(int id) {
		ClientDAO.delete(id);
	}
}
