package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TSpareParts")
public class SparePart extends BaseEntity {
    // natural attributes
    @Column(unique = true)
    private String code;
    @Basic(optional = false)
    private String description;
    private double price;

    // accidental attributes
    @OneToMany(mappedBy = "sparePart")
    private Set<Substitution> substitutions = new HashSet<>();

    public SparePart()	{
	
    }
    
    public SparePart(String code) {
	this(code, "no-description", 0.0);
    }

    public SparePart(String code, String description, double price) {
	ArgumentChecks.isNotBlank(code, "El código no puede estar vacío");
	ArgumentChecks.isNotBlank(description,
		"El código no puede estar vacío");
	ArgumentChecks.isTrue(price >= 0, "El precio no puede ser negativo");
	this.code = code;
	this.description = description;
	this.price = price;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public double getPrice() {
	return price;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public Set<Substitution> getSustitutions() {
	return new HashSet<>(substitutions);
    }

    Set<Substitution> _getSubstitutions() {
	return substitutions;
    }

    @Override
    public String toString() {
	return "SparePart [code=" + code + ", description=" + description
		+ ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SparePart other = (SparePart) obj;
	return Objects.equals(code, other.code);
    }

}
