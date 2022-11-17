package uo.ri.cws.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.math.Round;

@Entity
@Table(name = "TSUBSTITUTIONS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "INTERVENTION_ID",
		"SPAREPART_ID" }) })
public class Substitution extends BaseEntity {
    // natural attributes
    private int quantity;

    // accidental attributes
    @ManyToOne
    private SparePart sparePart;
    @ManyToOne
    private Intervention intervention;

    Substitution() {
    }

    public Substitution(SparePart sparePart, Intervention intervention) {
	ArgumentChecks.isNotNull(intervention);
	ArgumentChecks.isNotNull(sparePart);

	Associations.Substitute.link(sparePart, this, intervention);
    }

    public Substitution(SparePart sparePart, Intervention intervention,
	    int quantity) {
	this(sparePart, intervention);
	ArgumentChecks.isTrue(quantity > 0);
	this.quantity = quantity;
    }

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

    @Override
    public int hashCode() {
	return Objects.hash(intervention, sparePart);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Substitution other = (Substitution) obj;
	return Objects.equals(intervention, other.intervention)
		&& Objects.equals(sparePart, other.sparePart);
    }

    public double getAmount() {
	return Round.twoCents(getSparePart().getPrice() * quantity);
    }

}
