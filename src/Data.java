
public class Data {
	
	//Premiere quasi identifiant
	public int QID1;
	//Deuxime quasi identifiant
	public int QID2;
	//Donnee sensible
	public String sd;
	
	//Constructeur qui permet d'initializer les attributs de la classe Data
	public Data(int qID1, int qID2, String sd) {
		super();
		QID1 = qID1;
		QID2 = qID2;
		this.sd = sd;
	}
	
	public int getQID1() {
		return QID1;
	}
	public void setQID1(int qID1) {
		QID1 = qID1;
	}
	
	public int getQID2() {
		return QID2;
	}
	public void setQID2(int qID2) {
		QID2 = qID2;
	}
	
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	
	//Methode qui retourne la chaine de caracteres a afficher
	public String toString(){
		return "QID1 : "+QID1+" // QID2 : "+ QID2 +"// sd : "+sd;
	}
}
