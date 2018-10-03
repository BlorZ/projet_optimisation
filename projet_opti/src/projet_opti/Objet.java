package projet_opti;

public class Objet {
	private int poids;
	private int valeur;
	private boolean dansSac;
	
	public Objet() {
		super();
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	public boolean estDansSac() {
		if (dansSac) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = this.getPoids() + " " + this.getValeur() + " " + this.estDansSac();
		return s;	
	}
	
}
