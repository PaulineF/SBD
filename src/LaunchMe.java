import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;


public class LaunchMe {
	//Cardinalité n
	static int n;
	
	//borne min du premier QID
	static int QID1Min;
	
	//borne max du premier QID
	static int QID1Max;
	
	//borne min du deuxieme QID
	static int QID2Min;
	
	//borne max du deuxieme QID
	static int QID2Max;
	
	//nombre de données sensible
	static int nbSD;
	
	//Paramètre de confidentialité k
	static int k;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		n = 10;
		QID1Min = 0;
		QID1Max = 10;
		QID2Min = 30;
		QID2Max = 50;
		nbSD = 3;
		k = 2;
		
		//Initialisation des données sensible
		String[] sd = new String[nbSD];
		for (int i =0; i<nbSD; i++){
			sd[i] = UUID.randomUUID().toString().substring(1, 6);
		}
		
		//initialisation des n-uplet
		Data[] datas = new Data[n];
		Random rand = new Random();
		for(int i = 0; i<n ; i++){
			//premier quasi-identifiant généré aléatoirement
			int qid1 = rand.nextInt((QID1Max - QID1Min) + 1) + QID1Min;
			//deuxieme quasi-identifiant généré aléatoirement
			int qid2 = rand.nextInt((QID2Max - QID2Min) + 1) + QID2Min;
			//choix de la donnée sensible aléatoire
			int numsd = rand.nextInt(nbSD);
			
			//Création du n-uplet
			datas[i] = new Data(qid1, qid2,sd[numsd]);
			
		}
		
		//Ecriture dans un fichier csv
		/*BufferedWriter bw;
		try {
			//Creation du fichier
			bw = new BufferedWriter(
					new FileWriter(
					new File ( "./monJeuDeDonnees.csv" ),
					false
					)
					);
			//Ecriture des données dans le fichier.
			for(Data d : datas){
				bw.append ( d.getQID1() +" "+ d.getQID2()+" "+ d.getSd() +" \n" );
			}
			
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				*/

		
		//Affichage
		for(Data d : datas){
			System.out.println(d.toString());
		}

	}

	public void mondrian(Data[] datas, int k){
		if(datas.length<2*k){
			//trouve!!
		}else{
			int dim = chooseDimension(datas);
			Map<Integer, Integer> frequency = frequencySet(datas, dim);
			int splitVal = findMedian(frequency);
		}
		
	}
	
	private int findMedian(Map<Integer, Integer> frequency) {
		
		return 0;
	}

	public int chooseDimension(Data[] datas){
		int qid1min = Integer.MAX_VALUE;
		int qid1max = Integer.MIN_VALUE;
		int qid2min = Integer.MAX_VALUE;
		int qid2max = Integer.MIN_VALUE;
		
		for (Data d : datas){
			if(d.getQID1() < qid1min)
				qid1min = d.getQID1();
			
			if(d.getQID1() > qid1max)
				qid1max = d.getQID1();
			
			if(d.getQID2() < qid2min)
				qid2min = d.getQID2();
			
			if(d.getQID2() > qid2max)
				qid2max = d.getQID2();
		}
		
		int dif1= qid1max - qid1min;
		int dif2 =qid2max - qid2min;
		
		if(dif1> dif2)
			return 1;
		else
			return 2;
	}
	
	public Map<Integer, Integer> frequencySet(Data[] datas, int dim){
		HashMap<Integer, Integer> frequency = new HashMap<Integer,Integer>();
		if(dim ==1){
			for(Data d : datas){
				int nb = 0;
				if(frequency.containsKey(d.getQID1())){
					nb = frequency.get(d.getQID1());					
				}
				frequency.put(d.getQID1(), nb+1);
			}
		}else{
			for(Data d : datas){
				int nb = 0;
				if(frequency.containsKey(d.getQID2())){
					nb = frequency.get(d.getQID2());					
				}
				frequency.put(d.getQID2(), nb+1);
			}
		}
		return new TreeMap<Integer, Integer>(frequency);
	}
}
