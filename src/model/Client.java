package model;


public class Client {
	private int idClient;
	private String Nume;
	private String Prenume;
	private int Varsta;
	
	public Client(int idClient,String Nume,String Prenume,int Varsta) {
		super();
		this.idClient=idClient;
		this.Nume=Nume;
		this.Prenume=Prenume;
		this.Varsta=Varsta;
	}
	
	public Client(String Nume,String Prenume,int Varsta) {
		super();
		this.Nume=Nume;
		this.Prenume=Prenume;
		this.Varsta=Varsta;
	}
	
	public Client() {
		super();
	}

	public int getIdClient() {
		return this.idClient;
	}
	
	public void setIdClient(int id) {
		this.idClient=id;
	}
	
	public String getNume() {
		return this.Nume;
	}
	
	public void setNume(String nume) {
		this.Nume=nume;
	}
	
	public String getPrenume() {
		return this.Prenume;
	}
	
	public void setPrenume(String prenume) {
		this.Prenume=prenume;
	}
	
	public int getVarsta() {
		return this.Varsta;
	}
	
	public void setVarsta(int varsta) {
		this.Varsta=varsta;
	}
	
	public String toString() {
		return "Client [idClient=" + idClient + ", nume=" + Nume + ", prenume=" + Prenume + ", varsta=" + Varsta + "]";
	}
	
	//public static void main(String[] args) {
		//Client client=new Client(1,"Alin","Pop",20);
		//System.out.println(client.toString());
		//ClientDAO cl=new ClientDAO();
		//cl.findById(1);
	//}
}
