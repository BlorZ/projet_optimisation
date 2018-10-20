package projet_opti;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainInit {
	/**
	 * Méthode permettant de trier les objets de la liste en entré en fonction des ratios d'objet
	 * @param listObjet
	 */
	static void triObjets(List<Objet> listObjet) {
		Collections.sort(listObjet, new Comparator<Objet>() {
			
			@Override
			public int compare(Objet o1, Objet o2) {
				int value1 = o1.getRatio().compareTo(o2.getRatio());
				if(value1 == 0) {
					int value2 = o1.getValeur().compareTo(o2.getValeur());
					if(value2 == 0) {
						return o2.getIndiceCompat().compareTo(o1.getIndiceCompat());
					} else return value2;
				}
				return value1;
			}
					
		});
		Collections.reverse(listObjet);
	}
	
	
	static void printObjets(List<Objet> listObjet) {
		List<Objet> list = new ArrayList<>();
		list = listObjet;
		for(Objet o : list) {
			System.out.println(o.getIndiceCompat());
		}
	}

	/**
	 * Méthode permettant de lire le fichier en entrée et initialise les objets, le sac et les incompatibilités
	 * @param f
	 * @param listObjet
	 * @param listIncompatibilite
	 * @param sac
	 * @throws IOException
	 */
	static void litFichierEtInitObjets(File f, List<Objet> listObjet, List<Incompatibilite> listIncompatibilite, Sac sac) throws IOException {
		InputStream fichier = new FileInputStream(f);
		InputStreamReader lecture = new InputStreamReader(fichier);
		BufferedReader buff=new BufferedReader(lecture);
		String ligne;
		String separateur = " ";
		List<Integer> datasPoids = new ArrayList<>();
		List<Integer> datasValeurs = new ArrayList<>();
		int compteur_incomp = 0;
		int compteur = 0; // compteur nous permettant de savoir dans quel bloc nous nous trouvons
		while ((ligne=buff.readLine())!=null){
			String[] valeurs = ligne.split(separateur);
			if(ligne.equals("")) {
				compteur++; //on passe dans le bloc suivant
				continue;
			}
			//premiere ligne du fichier
			if(compteur == 0) {
				//initialisation des objets
				for(int i = 0; i < Integer.parseInt(valeurs[1]); i++) {
					Objet objet = new Objet();
					listObjet.add(objet);
				}

				//initialisation des incompatibilite
				for(int i = 0; i < Integer.parseInt(valeurs[2]); i++) {
					Incompatibilite incompatibilite = new Incompatibilite();
					listIncompatibilite.add(incompatibilite);
				}

				sac.setPoidsMax(Integer.parseInt(valeurs[3]));
			}

			if(compteur == 1) {// on est dans le 1er bloc => poids des objets
				for(int i= 0; i < valeurs.length; i++) {
					datasPoids.add(Integer.parseInt(valeurs[i]));
				}
			}

			if(compteur == 2) {// on est dans le 2e bloc => valeurs des objets
				for(int i= 0; i < valeurs.length; i++) {
					datasValeurs.add(Integer.parseInt(valeurs[i]));
				}
			}

			if(compteur == 3) {// on est dans le 3e bloc => incompatibilités
				listIncompatibilite.get(compteur_incomp).setObjet1(Integer.parseInt(valeurs[1])-1);
				listIncompatibilite.get(compteur_incomp).setObjet2(Integer.parseInt(valeurs[2])-1);
				compteur_incomp++;
			}
		}
		//on set le poids des objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setPoids(datasPoids.get(i));
		}
		//on set la valeur des objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setValeur(datasValeurs.get(i));
		}
		
		//on set le ratio des objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setRatio(listObjet.get(i).getValeur() / listObjet.get(i).getPoids());
		}
		
		//on set les id d'objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setId(i);
		}
		buff.close(); 
	}

	/**
	 * Méthode permettant de remplir le sac une première fois avec les objets ayant la plus grande valeur
	 * @param sac
	 * @param listObjet
	 */
	static void rempliSacSansContraintes(Sac sac, List<Objet> listObjet) {
		int compteur = 0;
		while(sac.getPoidsActuel() < sac.getPoidsMax() && compteur < listObjet.size()) {
			if(sac.getPoidsActuel() + listObjet.get(compteur).getPoids() <= sac.getPoidsMax()) {
				sac.getListObjets().add(listObjet.get(compteur));
				sac.setPoidsActuel(sac.getPoidsActuel() + listObjet.get(compteur).getPoids());
				sac.setValeur(sac.getValeur() + listObjet.get(compteur).getValeur());
			}
			compteur ++;
		}
	}

	/**
	 * Méthode permettant de voir si deux objets sont compatibles ou non
	 * @param o
	 * @param o2
	 * @param listIncompatibilite
	 * @return
	 */
	static boolean checkIncompatibilite(Objet o, Objet o2, List<Incompatibilite> listIncompatibilite) {
		for(Incompatibilite i : listIncompatibilite) {
			if((o.getId() == i.getObjet1() && o2.getId() == i.getObjet2())
					|| (o.getId() == i.getObjet2() && o2.getId() == i.getObjet1())){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Méthode permettant de d'enlever les incompatibilités du sac en ne gardant que les meilleures valeurs
	 * @param sac
	 * @param listIncompatibilite
	 */
	public static Sac sacCompatible(Sac sac, List<Incompatibilite> listIncompatibilite) {
		int valeur = Integer.MIN_VALUE;
		while(sac.getValeur() > valeur && sac.compteIncompatibilite(listIncompatibilite) != 0) {
			Objet o = new Objet();
			Sac sacTmp = new Sac(sac.getListObjets());
			sacTmp.setPoidsMax(sac.getPoidsMax());
			sacTmp.majSac(listIncompatibilite);
			for(int i = 0; i< sac.getListObjets().size(); i++) {
				o = sacTmp.getListObjets().get(i);
				sacTmp.getListObjets().remove(o);
				sacTmp.majSac(listIncompatibilite);
				if(sacTmp.getValeur() > sac.getValeur()) {
					valeur = sac.getValeur();
					sac = new Sac(sacTmp.getListObjets());
					sac.setPoidsMax(sacTmp.getPoidsMax());
					sac.majSac(listIncompatibilite);
				}
				sacTmp.getListObjets().add(o);
				sacTmp.majSac(listIncompatibilite);
			}
		}		
		return sac;
	}

	public static Sac rempliSacAvecContraintes(Sac sac, List<Incompatibilite> listIncompatibilite, List<Objet> listObjets) {
		for(int i=0; i< listObjets.size(); i++) {
			if((!listObjets.get(i).estDansSac()) && (sac.getPoidsActuel() + listObjets.get(i).getPoids() <= sac.getPoidsMax())) {
				sac.getListObjets().add(listObjets.get(i));
				listObjets.get(i).setDansSac(true);
				sac.majSac(listIncompatibilite);
				if(sac.compteIncompatibilite(listIncompatibilite) != 0) {
					sac.getListObjets().remove(listObjets.get(i));
					listObjets.get(i).setDansSac(false);
					sac.majSac(listIncompatibilite);
				}
			}
		}
		return sac;
	}
	
	
	public static void generationIndiceIncompat(List<Objet> listObjets, List<Incompatibilite> listIncompatibilite) {
		
		for(Objet o : listObjets) {
			for(Objet o1 : listObjets) {
				if(checkIncompatibilite(o, o1, listIncompatibilite) && !o.equals(o1)) {
					o.setIndiceCompat(o.getIndiceCompat() + 1);
				}
			}
		}
		
	}
	
	
	
	public static Univers generationUnivers(Objet o, List<Objet> listObjets, List<Incompatibilite> listIncompatibilite) {
		
		boolean compat;
		Univers univers = new Univers();
		univers.getListObjets().add(o);
						
		for(Objet o2 : listObjets) {
			compat = true;
			for(Objet o1 : univers.getListObjets()) {
				if(checkIncompatibilite(o1, o2, listIncompatibilite) || univers.getListObjets().contains(o2))
					compat = false;
			}
			
			if(compat == true)
				univers.getListObjets().add(o2);
		}
		
		return univers;
	}
	
	
	public static boolean isUniversTheSame(Univers u1, Univers u2) {
		
		if(u1.getListObjets().size() != u2.getListObjets().size())
			return false;
		
		for(Objet o : u1.getListObjets()) {
			if(!u2.getListObjets().contains(o))
				return false;
		}
		return true;
	}
	
	static void rempliLastSac(Sac sac, List<Objet> listObjet) {
		
		for(Objet o : listObjet) {
			sac.getListObjets().add(o);
			sac.setPoidsActuel(sac.getPoidsActuel() + o.getPoids());
			sac.setValeur(sac.getValeur() + o.getValeur());
			o.setDansSac(true);
		}
		
	}
	
	
}

