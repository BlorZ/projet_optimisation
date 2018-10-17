package projet_opti;

import java.util.ArrayList;
import java.util.List;

public class Sac {
	private List<Objet> listObjets;
	private int valeur;
	private int poidsActuel;
	private int poidsMax;
	
	public Sac() {
		super();
		listObjets = new ArrayList<>();
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
}
