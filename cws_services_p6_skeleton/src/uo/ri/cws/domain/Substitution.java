package uo.ri.cws.domain;

public class Substitution {
	// natural attributes
	private int quantity;

	// accidental attributes
	private SparePart sparePart;
	private Intervention intervention;



	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	public int getQuantity() {
	    return quantity;
	}

	public void setQuantity(int quantity) {
	    this.quantity = quantity;
	}

	public SparePart getSparePart() {
	    return sparePart;
	}

	public void setSparePart(SparePart sparePart) {
	    this.sparePart = sparePart;
	}

	public Intervention getIntervention() {
	    return intervention;
	}

	public void setIntervention(Intervention intervention) {
	    this.intervention = intervention;
	}
	
	

}
