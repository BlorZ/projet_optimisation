package projet_opti;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		
		List<Objet> listObjet = new ArrayList<>();
		List<Incompatibilite> listIncompatibilite = new ArrayList<>();
		Sac sac = new Sac(listObjet);
				
System.out.println("Lecture du fichier.");
		
		File f = new File("../projet_opti/conf/1I1");	
		MainInit.litFichierEtInitObjets(f, listObjet, listIncompatibilite, sac);
		sac.setPoidsActuel(0);
		
		//Tri de la liste des objets
		MainInit.triObjets(listObjet);
		
		MainInit.generationIndiceIncompat(listObjet, listIncompatibilite);
		//MainInit.printObjets(listObjet);
				
System.out.println("... ok\n");
		
System.out.println("Création de la liste d'univers.");
		
		//Création de la liste d'univers
		List<Univers> listUnivers = new ArrayList<>();
		boolean diff = true;
		
		//Fonction de création d'un univers
		for(Objet o : listObjet) {
			diff = true;
			Univers u = new Univers();
			u = MainInit.generationUnivers(o, listObjet, listIncompatibilite);
			for(Univers u1 : listUnivers) {
				if(MainInit.isUniversTheSame(u1, u)) {
					diff = false;
				}
			}
			if(diff == true) {
				listUnivers.add(u);
				System.out.println("Nombre d'objets dans l'univers : " + u.getListObjets().size());
			}
		}
		System.out.println("Nombre d'univers générés : " + listUnivers.size());

System.out.println("... ok\n");
		
System.out.println("Tri de la liste d'objets dans chaque univers.");
		//Tri des objets dans l'univers
		for(Univers u : listUnivers) {
			MainInit.generationIndiceIncompat(u.getListObjets(), listIncompatibilite);
			MainInit.triObjets(u.getListObjets());
//			MainInit.printObjets(u.getListObjets());
		}

System.out.println("... ok\n");
		

System.out.println("Création du sac optimal.");
		//Remplissage du sac
//		Sac sac_tmp = new Sac();
//		sac_tmp.setPoidsMax(sac.getPoidsMax());
		
		for(Univers u : listUnivers) {
			Sac sac_tmp = new Sac();
			sac_tmp.setPoidsMax(sac.getPoidsMax());
			MainInit.rempliSacSansContraintes(sac_tmp, u.getListObjets());
			sac_tmp.majSac_sanscompat(sac_tmp.getListObjets());
			
			System.out.println("tmp " + sac_tmp.getPoidsActuel());
			System.out.println("tmp " + sac_tmp.getValeur());
			System.out.println("sac " + sac.getPoidsActuel());
			System.out.println("sac " + sac.getValeur());
			
			if(sac_tmp.getValeur() > sac.getValeur()) {
				sac = new Sac(sac_tmp.getListObjets());
				sac.setPoidsMax(sac_tmp.getPoidsMax());
								
				//MainInit.rempliSacSansContraintes(sac, u.getListObjets());
				sac.majSac_sanscompat(sac.getListObjets());
			}
			//System.out.println(sac.getValeur());
		}
		
System.out.println("... ok\n");
		
//		MainInit.triObjets(listObjet);
//		MainInit.rempliSacSansContraintes(sac, listObjet, listIncompatibilite);
//		sac.majSac(listIncompatibilite);
//		sac = MainInit.sacCompatible(sac, listIncompatibilite);
//		
//		sac = MainInit.rempliSacAvecContraintes(sac, listIncompatibilite, listObjet);
		
//		Solution s = new Solution(listObjet);
//		//System.out.println(s);
//		System.out.println(s.estRealisable(sac.getPoidsMax(), listIncompatibilite));

		Sac lastSac = new Sac();
		lastSac.setPoidsMax(sac.getPoidsMax());
				
		
		MainInit.rempliLastSac(lastSac, sac.getListObjets());
		lastSac.majSac_sanscompat(lastSac.getListObjets());
			
		for(Objet o : lastSac.getListObjets()) {
			if(o.estDansSac() == false)
				System.out.println("Objet : " +o.getId() + " est " + o.estDansSac());
		}
		
		System.out.println("Nombres d'incompatibilités : " + lastSac.compteIncompatibilite(listIncompatibilite));
		
		if(lastSac.compteIncompatibilite(listIncompatibilite) == 0) {
			long stop = System.currentTimeMillis();
			System.out.println("Solution trouvée en "+ (stop - start) + " ms");
			System.out.println(lastSac.toString());
		} else {
			long stop = System.currentTimeMillis();
			System.out.println("Gros naze trouvé en "+ (stop - start) + " ms");
			System.out.println(lastSac.toString());
		}
	}	
}

