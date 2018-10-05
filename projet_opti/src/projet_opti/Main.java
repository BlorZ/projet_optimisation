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
		Sac sac = new Sac();
		File f = new File("../projet_opti/conf/1I1");	
		MainInit.litFichierEtInitObjets(f, listObjet, listIncompatibilite, sac);
		long stop = System.currentTimeMillis();
		System.out.println("Solution trouvée en "+ (stop - start) + " ms");
		MainInit.triObjets(listObjet);
	}	
	
}

