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
		this.listObjets = new ArrayList<>();
		Collections.copy(listObjets, _listObjets);
		
	}

	float calculPoids() {
		float p = 0;
		for (Objet o : listObjets) {
			p += o.getPoids();
		}
		return p;
	}
	
	float calculValeur() {
		float v = 0;
		for (Objet o : listObjets) {
			v += o.getValeur();
		}
		return v;
	}
	
	void evaluer() {
		this.poids = calculPoids();
		this.valeur = calculValeur();
	}
	
	boolean estRealisable(float poidsSac) {
		if (this.poids <= poidsSac) {
			return true;
		}
		return false;
	}

}
