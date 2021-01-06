package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Order;
import model.Product;
public class View extends JFrame{


	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	JPanel client = new JPanel();
	JPanel product = new JPanel();
	JPanel order = new JPanel();
	JButton afisClienti=new JButton("afisCl");
	JButton afisProducts=new JButton("afisPr");
	JButton afisOrders=new JButton("afisOrd");
	JTable tab=new JTable();
	ClientBLL ord = new ClientBLL();
	OrderBLL ord1 = new OrderBLL();
	ProductBLL ord2 = new ProductBLL();
	JScrollPane sp;
	
	ArrayList<Object> client1 = ord.findAllClients();
	ArrayList<Object> order1 = ord1.findAllOrders();
	ArrayList<Object> product1 = ord2.findAllProducts();
	
	private int currentClientId;
	Client findClient=new Client();
	JButton selectClient = new JButton("selectClient");
	JButton addClient = new JButton("addClient");
	JButton editClient = new JButton("editClient");
	JButton deleteClient = new JButton("deleteClient");
	JLabel idCl = new JLabel("idClient:");
	JLabel numeCl = new JLabel("nume client:"); 
	JLabel prenumeCl = new JLabel("prenume client:"); 
	JLabel varstaCl = new JLabel("varsta client:");
	JTextField idC = new JTextField();
	JTextField numeC = new JTextField();
	JTextField prenumeC = new JTextField();
	JTextField varstaC = new JTextField();
	
	private int currentProductId;
	Product findProduct = new Product();
	JButton selectProduct = new JButton("selectProduct");
	JButton addProduct = new JButton("addProduct");
	JButton editProduct = new JButton("editProduct");
	JButton deleteProduct = new JButton("deleteProduct");
	JLabel idPr = new JLabel("idProduct:");
	JLabel pret = new JLabel("pret:"); 
	JLabel stoc = new JLabel("stoc:"); 
	JLabel numePr = new JLabel("nume produs:");
	JTextField idP = new JTextField();
	JTextField pretP = new JTextField();
	JTextField stocP = new JTextField();
	JTextField numeP = new JTextField();
	
	JButton createOrder = new JButton("createOrder");
	JLabel cantitateOrder = new JLabel("cantitate:");
	JTextField cantitateOrd = new JTextField();
		
	public static JTable createTable(ArrayList<Object> object, Object typeObj) {
		Object values[][]=new Object[object.size()][typeObj.getClass().getDeclaredFields().length];
		String[] columns=new String[typeObj.getClass().getDeclaredFields().length];
		int k=0;
		for(Field str:typeObj.getClass().getDeclaredFields()) {
			columns[k++]=str.getName();
		}
		int j=0;
		for(Object obj : object) {
			int i=0;
			for(Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value= new Object();
				try {
					value = field.get(obj);
					values[j][i]=value;
					//tabel.getModel().setValueAt(value, j, i);
					System.out.println(field.getName() + "=" + value);
					i++;

				} catch (IllegalArgumentException e) {
					//e.printStackTrace();
				} catch (IllegalAccessException e) {
					//e.printStackTrace();
				}
			}
			j++;
		}
		
		JTable tabel=new JTable(values,columns);
		return tabel;	
	}
	
	public View() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,600);
		client.setLayout(null);
		
		afisClienti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				client1 = ord.findAllClients();
				tab=createTable(client1,new Client());
				client.remove(sp);
				sp=new JScrollPane(tab);
				sp.setBounds(10, 35, 320, 500);
				client.add(sp);
				
			}
			
		});
		
		afisProducts.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				product1 = ord2.findAllProducts();
				tab=createTable(product1,new Product());
				client.remove(sp);
				sp=new JScrollPane(tab);
				sp.setBounds(10, 35, 320, 500);
				client.add(sp);
				
			}
			
		});
		
		afisOrders.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				order1 = ord1.findAllOrders();
				tab=createTable(order1,new Order());
				client.remove(sp);
				sp=new JScrollPane(tab);
				sp.setBounds(10, 35, 320, 500);
				client.add(sp);
				
			}
			
		});
		
		addClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int varsta=Integer.parseInt(varstaC.getText());
				Client s1 = new Client(numeC.getText(),prenumeC.getText(),varsta);
				currentClientId=ord.insertClient(s1);
				System.out.println(currentClientId);
				idC.setText(currentClientId+"");
			}
			
		});
		
		editClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idc=Integer.parseInt(idC.getText());
				int varsta=Integer.parseInt(varstaC.getText());
				ord.updtaeClient(idc, numeC.getText(), prenumeC.getText(), varsta);
				
			}
			
		});
		
		deleteClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idc=Integer.parseInt(idC.getText());
				ord.deleteClient(idc);
				
			}
			
		});
		
		selectClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idc=Integer.parseInt(idC.getText());
				findClient=ord.findClientById(idc);
				if(findClient == null)
					JOptionPane.showMessageDialog(null, "Client inexistent", "ERROR", JOptionPane.ERROR_MESSAGE);
				//System.out.println(findClient.toString());
			}
			
		});
		
		addProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int pret=Integer.parseInt(pretP.getText());
				int stoc=Integer.parseInt(stocP.getText());
				Product p1 = new Product(pret,stoc,numeP.getText());
				currentProductId=ord2.insertProducts(p1);
				System.out.println(currentProductId);
				idP.setText(currentProductId+"");
			}
			
		});
		
		editProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idc=Integer.parseInt(idP.getText());
				int stoc=Integer.parseInt(stocP.getText());
				ord2.updateProduct(idc, stoc);
				
			}
			
		});
		
		deleteProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idp=Integer.parseInt(idP.getText());
				ord2.deleteProduct(idp);
				
			}
			
		});
		
		selectProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int idp=Integer.parseInt(idP.getText());
				findProduct=ord2.findProductById(idp);
				if (findProduct == null)
					JOptionPane.showMessageDialog(null, "Produs inexistent", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println(findProduct.toString());
			}
			
		});
		
		createOrder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(findProduct.toString());
				System.out.println(findClient.toString());
				
				int idp=Integer.parseInt(idP.getText());
				findProduct = ord2.findProductById(idp);
				
				int idc=Integer.parseInt(idC.getText());
				findClient=ord.findClientById(idc);
				
				
				int cantitate = Integer.parseInt(cantitateOrd.getText());
				if(cantitate > findProduct.getStoc())
					JOptionPane.showMessageDialog(null, "Stoc insuficient", "ERROR", JOptionPane.ERROR_MESSAGE);
				else {
					Order order = new Order(findClient.getIdClient(),findProduct.getIdProduct(),cantitate);
					int l=ord1.insertOrders(order);
					
					Writer writer = null;

					try {
					    writer = new BufferedWriter(new OutputStreamWriter(
					          new FileOutputStream("Bon.txt"), "utf-8"));
					    writer.write("Comanda cu nr " + l +" clientul cu id-ul " + findClient.getIdClient() + " a cumparat " + cantitate+ " produse de tip " + findProduct.getNume() + " in valoare de " + cantitate*findProduct.getPret());
					} catch (IOException ex) {
					    // Report
					} finally {
					   try {writer.close();} catch (Exception ex) {/*ignore*/}
					}
					
					System.out.println(l);
					int nouStoc = findProduct.getStoc()-cantitate;
					ord2.updateProduct(findProduct.getIdProduct(), nouStoc);
				}
			}
			
		});
		
		
		afisClienti.setBounds(10, 10, 100, 20);
		afisProducts.setBounds(120, 10, 100, 20);
		afisOrders.setBounds(230, 10, 100, 20);
		addClient.setBounds(340, 10, 100, 20);
		editClient.setBounds(340, 40, 100, 20);
		deleteClient.setBounds(340, 70, 100, 20);
		selectClient.setBounds(340, 100, 100, 20);
		idCl.setBounds(450,10,100,20);
		numeCl.setBounds(450,40,100,20);
		prenumeCl.setBounds(450,70,100,20);
		varstaCl.setBounds(450,100,100,20);
		idC.setBounds(560,10,100,20);
		numeC.setBounds(560,40,100,20);
		prenumeC.setBounds(560,70,100,20);
		varstaC.setBounds(560,100,100,20);
		
		addProduct.setBounds(340, 150, 100, 20);
		editProduct.setBounds(340, 180, 100, 20);
		deleteProduct.setBounds(340, 210, 100, 20);
		selectProduct.setBounds(340, 240, 100, 20);
		idPr.setBounds(450,150,100,20);
		pret.setBounds(450,180,100,20);
		stoc.setBounds(450,210,100,20);
		numePr.setBounds(450,240,100,20);
		idP.setBounds(560,150,100,20);
		pretP.setBounds(560,180,100,20);
		stocP.setBounds(560,210,100,20);
		numeP.setBounds(560,240,100,20);
		
		createOrder.setBounds(340, 290, 100, 20);
		cantitateOrder.setBounds(450,290,100,20);
		cantitateOrd.setBounds(560,290,100,20);
		
		client.add(afisClienti);
		client.add(afisProducts);
		client.add(afisOrders);
		
		client.add(addClient);
		client.add(editClient);
		client.add(deleteClient);
		client.add(selectClient);
		client.add(idCl);
		client.add(numeCl);
		client.add(prenumeCl);
		client.add(varstaCl);
		client.add(idC);
		client.add(numeC);
		client.add(prenumeC);
		client.add(varstaC);
		
		client.add(addProduct);
		client.add(editProduct);
		client.add(deleteProduct);
		client.add(selectProduct);
		client.add(idPr);
		client.add(pret);
		client.add(stoc);
		client.add(numePr);
		client.add(idP);
		client.add(numeP);
		client.add(pretP);
		client.add(stocP);
		
		client.add(createOrder);
		client.add(cantitateOrder);
		client.add(cantitateOrd);
		
		sp=new JScrollPane(tab);
		sp.setBounds(10, 35, 320, 500);
		client.add(sp);
		
		frame.setContentPane(client);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		/*ClientBLL ord = new ClientBLL();
		View s=new View();
		ArrayList<Object> client = ord.findAllClients();
		s.createTable(client);
		*/
		
		new View();
		//System.out.println(find.toString());
	}
}
