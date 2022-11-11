package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.cws.domain.base.BaseEntity;
import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name="TProfessionalGroups")
public class ProfessionalGroup extends BaseEntity {
    
    @Column(unique=true)
    private String name;
    private double productivityRate;
    private double trienniumSalary;
    
    @OneToMany(mappedBy = "professionalGroup")
    private Set<Contract> contracts = new HashSet<>();
    
    public ProfessionalGroup()	{
	
    }

    public ProfessionalGroup(String name, double productivityRate, double trienniumSalary) {
	ArgumentChecks.isNotBlank(name.trim(), "El nombre del grupo porfesional no puede estar vacío");
	ArgumentChecks.isTrue(productivityRate>=0, "Productivity rate no puede ser menor que 0");
	ArgumentChecks.isTrue(trienniumSalary>=0, "TrienniumSalary no puede ser menor que 0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProductivityRate() {
        return productivityRate;
    }

    public void setProductivityRate(double productivityRate) {
        this.productivityRate = productivityRate;
    }

    public double getTrienniumSalary() {
        return trienniumSalary;
    }

    public void setTrienniumSalary(double trienniumSalary) {
        this.trienniumSalary = trienniumSalary;
    }

    Set<Contract> _getContracts() {
        return contracts;
    }
    
    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }
    
    

}
