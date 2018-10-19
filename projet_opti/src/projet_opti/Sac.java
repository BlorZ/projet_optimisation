package projet_opti;

import java.util.ArrayList;
import java.util.List;

public class Sac {
	private List<Objet> listObjets;
	private int valeur;
	private int poidsActuel;
	private int poidsMax;

	public Sac(List<Objet> _listObjets) {
		super();
		this.listObjets = new ArrayList<Objet>(_listObjets);
	}
	
	public Sac() {
		super();
		listObjets = new ArrayList<>();
		valeur = 0;
		poidsActuel = 0;
		poidsMax = 0;
	}

	public List<Objet> getListObjets() {
		return listObjets;
	}

	public void setListObjets(List<Objet> listObjets) {
		this.listObjets = listObjets;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public int getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}

	public int getPoidsActuel() {
		return poidsActuel;
	}

	public void setPoidsActuel(int poidsActuel) {
		this.poidsActuel = poidsActuel;
	}

	int calculPoids() {
		int p = 0;
		for (Objet o : this.listObjets) {
//			if (o.estDansSac())
			p += o.getPoids();
		}
		return p;
	}

	int calculValeur() {
		int v = 0;
		for (Objet o : this.listObjets) {
			//if (o.estDansSac())
				v += o.getValeur();
		}
		return v;
	}
	
	int calculPoids_list(List<Objet> listObjets) {
		int p = 0;
		for (Objet o : listObjets) {
//			if (o.estDansSac())
			p += o.getPoids();
		}
		return p;
	}
	
	int calculValeur_list(List<Objet> listObjets) {
		int v = 0;
		for (Objet o : listObjets) {
			//if (o.estDansSac())
				v += o.getValeur();
		}
		return v;
	}

	void majSac(List<Incompatibilite> listIncompatibilite) {
		this.poidsActuel = calculPoids();
		this.valeur = calculValeur();
		//on repère les incompatibilites
		for(Objet o : this.listObjets) {
			for(Objet o2 : this.listObjets) {
				if(MainInit.checkIncompatibilite(o, o2, listIncompatibilite)) {
					this.valeur -= 1000;
				}
			}
		}
	}
	
	void majSac_sanscompat(List<Objet> listObjets) {
		this.poidsActuel = calculPoids_list(listObjets);
		this.valeur = calculValeur_list(listObjets);
	}

	
	int compteIncompatibilite(List<Incompatibilite> listIncompatibilite) {
		int nbIcomp = 0;
		//on repère les incompatibilites
		for(Objet o : this.listObjets) {
			for(Objet o2 : this.listObjets) {
				if(MainInit.checkIncompatibilite(o, o2, listIncompatibilite)) {
					nbIcomp ++;
				}
			}
		}
		return nbIcomp;
	}
	
	public String toString() {
		return "valeur du sac : " + this.getValeur() + "| poids du sac : " + this.poidsActuel;
	}
}
