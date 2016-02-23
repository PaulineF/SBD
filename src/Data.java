
public class Data {
	
	public int QID1;
	public int QID2;
	public String sd;
	
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
	
	
	public String toString(){
		return "QID1 : "+QID1+" // QID2 : "+ QID2 +"// sd : "+sd;
	}
}
