import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.AbstractDocument.LeafElement;

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
		nbSD = 6;
		k = 3;
		
		//Initialisation des données sensible
		String[] sd = new String[nbSD];
		for (int i =0; i<nbSD; i++){
			sd[i] = UUID.randomUUID().toString().substring(1, 6);
		}
		
		//initialisation des n-uplet
		ArrayList<Data> datas = new ArrayList<Data>();
		Random rand = new Random();
		for(int i = 0; i<n ; i++){
			//premier quasi-identifiant généré aléatoirement
			int qid1 = rand.nextInt((QID1Max - QID1Min) + 1) + QID1Min;
			//deuxieme quasi-identifiant généré aléatoirement
			int qid2 = rand.nextInt((QID2Max - QID2Min) + 1) + QID2Min;
			//choix de la donnée sensible aléatoire
			int numsd = rand.nextInt(nbSD);
			
			//Création du n-uplet
			datas.add(new Data(qid1, qid2,sd[numsd]));
			
			
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
		mondrian(datas,k);
		
		//Affichage
		/*for(Data d : datas){
			System.out.println(d.toString());
		}*/

	}

	public static void mondrian(ArrayList<Data> datas, int k){
		if(datas.size()<=2*k){
			System.out.println("Groupe :");
			for (Data d: datas){
				System.out.println(d.toString());
			}
		}else{
			int dim = chooseDimension(datas);
			TreeMap<Integer, Integer> frequency = frequencySet(datas, dim);
			int splitVal = findMedian(frequency, datas.size());
			
			ArrayList<Data> L = new ArrayList<Data>();
			ArrayList<Data> R = new ArrayList<Data>();
			if(dim == 1){
				for (Data d: datas){
					if(d.getQID1() <= splitVal){
						L.add(d);
					}else{
						R.add(d);
					}
				}
			}else{
				for (Data d: datas){
					if(d.getQID2() <= splitVal){
						L.add(d);
					}else{
						R.add(d);
					}
				}
			}
			mondrian(L, k);
			mondrian(R,k);
			
		}
		
	}
	
	private static int findMedian(TreeMap<Integer, Integer> frequency, int length) {
		/*for (Entry<Integer, Integer> entree : frequency.entrySet()) {
			System.out.println("Cl� : "+entree.getKey()+" Valeur : "+entree.getValue());
		}*/
		
		int keyMadian = -1;
		int sum = 0;
		Iterator<Entry<Integer, Integer>> it = frequency.entrySet().iterator();
		while (it.hasNext() && sum<=length/2){
			Entry<Integer, Integer> entry = it.next();
			keyMadian = entry.getKey();
			sum += entry.getValue();
		}
		return keyMadian;
	}

	public static int chooseDimension(ArrayList<Data> datas){
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
	
	public static TreeMap<Integer, Integer> frequencySet(ArrayList<Data> datas, int dim){
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
>>>>>>> branch 'master' of https://github.com/PaulineF/SBD.git
