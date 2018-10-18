package projet_opti;

import java.util.ArrayList;
import java.util.List;

public class Univers {
	
	int id;
	private List<Objet> listObjets;
	
	public Univers() {
		super();
		this.listObjets = new ArrayList<>();
	}

	public List<Objet> getListObjets() {
		return listObjets;
	}

	public void setListObjets(List<Objet> listObjets) {
		this.listObjets = listObjets;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
