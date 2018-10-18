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
		sac.setPoidsActuel(0);
		File f = new File("../projet_opti/conf/1I1");	
		MainInit.litFichierEtInitObjets(f, listObjet, listIncompatibilite, sac);
		MainInit.triObjets(listObjet);
		MainInit.rempliSacSansContraintes(sac, listObjet, listIncompatibilite);
		sac.majSac(listIncompatibilite);
		sac = MainInit.sacCompatible(sac, listIncompatibilite);
		
		sac = MainInit.rempliSacAvecContraintes(sac, listIncompatibilite, listObjet);
		
//		Solution s = new Solution(listObjet);
//		//System.out.println(s);
//		System.out.println(s.estRealisable(sac.getPoidsMax(), listIncompatibilite));

		long stop = System.currentTimeMillis();
		System.out.println("Solution trouvée en "+ (stop - start) + " ms");
		System.out.println(sac.toString());
	}	
}

