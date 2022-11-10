package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

@Entity
@Table(name = "TVOUCHERS")
public class Voucher extends PaymentMean {
    @Column(unique = true)
    private String code;
    private double available = 0.0;
    private String description;

    public Voucher(String code) {
	ArgumentChecks.isNotEmpty(code.trim());
	this.code = code;
    }

    Voucher() {
    }

    public Voucher(String code, String description, double available,
	    Client client) {
	this(code, description, available);
	ArgumentChecks.isNotNull(client);
	Associations.Pay.link(client, this);
    }

    public Voucher(String code, String description, double available) {
	this(code);
	ArgumentChecks.isNotEmpty(description.trim());
	ArgumentChecks.isTrue(available >= 0);
	this.description = description;
	this.available = available;
    }

    public Voucher(String code, double available) {
	this(code, "no-description", available);
    }

    public String getCode() {
	return code;
    }

    public double getAvailable() {
	return available;
    }

    public String getDescription() {
	return description;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((code == null) ? 0 : code.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Voucher other = (Voucher) obj;
	if (code == null) {
	    if (other.code != null)
		return false;
	} else if (!code.equals(other.code))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Voucher [code=" + code + ", available=" + available
		+ ", description=" + description + "]";
    }

    /**
     * Augments the accumulated (super.pay(amount) ) and decrements the
     * available
     * 
     * @throws IllegalStateException if not enough available to pay
     */
    @Override
    public void pay(double amount) {
	StateChecks.isTrue(amount <= available);
	super.pay(amount);
	available -= amount;
    }

    @Override
    public boolean canPay(double amount) {
	return available >= amount;
    }
}
