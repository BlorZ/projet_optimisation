//package projet_opti;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Solution {
//	
//	List<Objet> listObjets;
//	float poids;
//	float valeur;
//	
//	Solution(List<Objet> _listObjets){
//		this.poids = 0;
//		this.valeur = 0;
//		this.listObjets = new ArrayList<Objet>(_listObjets);
//		this.evaluer();
//		
//	}
//
//	float calculPoids() {
//		float p = 0;
//		for (Objet o : listObjets) {
//			if (o.estDansSac())
//				p += o.getPoids();
//		}
//		return p;
//	}
//	
//	float calculValeur() {
//		float v = 0;
//		for (Objet o : listObjets) {
//			if (o.estDansSac())
//				v += o.getValeur();
//		}
//		return v;
//	}
//	
//	void evaluer() {
//		this.poids = calculPoids();
//		this.valeur = calculValeur();
//	}
//	
//	boolean estRealisable(float poidsSac, List<Incompatibilite> listIncompatibilite) {
//		//Voir après pour les incompatibilités
//		if (this.poids > poidsSac) {
//			return false;
//		}
//		
//		for (Incompatibilite i : listIncompatibilite) {
//			if ( (this.listObjets.get(i.getObjet1()).estDansSac()) && (this.listObjets.get(i.getObjet2()).estDansSac()) ) {
//				return false;
//			}
//		}
//		
//		return true;	
//	}
//	
//	public Solution genereVoisin() {
//		Solution newSol = new Solution(this.listObjets);
//		int i = (int) Math.random() * newSol.listObjets.size();
//		
//		if (newSol.listObjets.get(i).estDansSac()) {
//			newSol.listObjets.get(i).setDansSac(false);
//		}else {
//			newSol.listObjets.get(i).setDansSac(true);
//		}
//		
//		return newSol;
//	}
//	
//	public String toString() {
//		String s = "";
//		
//		for(Objet o : this.listObjets) {
//			s += o.toString() + "\n";
//		}
//		
//		s+="Poids : " + this.poids + " " + "Valeur : " + this.valeur; 
//		
//		return s;
//	}
//
//}
