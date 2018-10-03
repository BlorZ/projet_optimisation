package projet_opti;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	
	List<Objet> listObjets;
	float poids;
	float valeur;
	
	Solution(List<Objet> _listObjets){
		this.poids = 0;
		this.valeur = 0;
		this.listObjets = new ArrayList<Objet>(_listObjets);
		//Collections.copy(listObjets, _listObjets);
		this.evaluer();
		
	}

	float calculPoids() {
		float p = 0;
		for (Objet o : listObjets) {
			if (o.estDansSac())
				p += o.getPoids();
		}
		return p;
	}
	
	float calculValeur() {
		float v = 0;
		for (Objet o : listObjets) {
			if (o.estDansSac())
				v += o.getValeur();
		}
		return v;
	}
	
	void evaluer() {
		this.poids = calculPoids();
		this.valeur = calculValeur();
	}
	
	boolean estRealisable(float poidsSac) {
		//Voir après pour les incompatibilités
		if (this.poids <= poidsSac) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		
		for(Objet o : this.listObjets) {
			s += o.toString() + "\n";
		}
		
		s+="Poids : " + this.poids + " " + "Valeur : " + this.valeur; 
		
		return s;
	}

}
