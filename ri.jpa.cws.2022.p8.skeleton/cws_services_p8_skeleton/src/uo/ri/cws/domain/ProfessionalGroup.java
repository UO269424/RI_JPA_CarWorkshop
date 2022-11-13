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
    private double productivityBonusPercentage;
    private double trienniumPayment;
    
    @OneToMany(mappedBy = "professionalGroup")
    private Set<Contract> contracts = new HashSet<>();
    
    public ProfessionalGroup()	{
	
    }

    public ProfessionalGroup(String name, double productivityBonusPercentage, double trienniumPayment) {
	ArgumentChecks.isNotBlank(name.trim(), "El nombre del grupo porfesional no puede estar vacío");
	ArgumentChecks.isTrue(productivityBonusPercentage>=0, "Productivity rate no puede ser menor que 0");
	ArgumentChecks.isTrue(trienniumPayment>=0, "TrienniumSalary no puede ser menor que 0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProductivityBonusPercentage() {
        return productivityBonusPercentage;
    }

    public void setProductivityBonusPercentage(double productivityBonusPercentage) {
        this.productivityBonusPercentage = productivityBonusPercentage;
    }

    public double getTrienniumSalary() {
        return trienniumPayment;
    }

    public void setTrienniumSalary(double trienniumPayment) {
        this.trienniumPayment = trienniumPayment;
    }

    Set<Contract> _getContracts() {
        return contracts;
    }
    
    public Set<Contract> getContracts() {
        return new HashSet<>(contracts);
    }
    
    

}
