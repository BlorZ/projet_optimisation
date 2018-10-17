package projet_opti;

public class Objet {
	private int id;
	private int poids;
	private Integer valeur;
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

	public Integer getValeur() {
		return valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	
	public void setDansSac(boolean valeur) {
		this.dansSac = valeur;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
