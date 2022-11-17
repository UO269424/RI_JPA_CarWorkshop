package uo.ri.cws.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;
import uo.ri.util.assertion.StateChecks;

@Entity
@Table(name = "TCREDITCARDS")
public class CreditCard extends PaymentMean {
    @Column(unique = true)
    private String number;
    private String type;
    private LocalDate validThru;

    CreditCard() {
    }

    public CreditCard(String number) {
	ArgumentChecks.isNotEmpty(number.trim());
	this.number = number;
	this.type = "UNKNOWN";
	this.validThru = LocalDate.now().plusDays(1);
    }

    public CreditCard(String number, String type, LocalDate validThru) {
	this(number);
	ArgumentChecks.isNotEmpty(type.trim());
	ArgumentChecks.isNotNull(validThru);
	this.type = type;
	this.validThru = validThru;
    }

    public CreditCard(String number, String type, LocalDate validThru,
	    Client client) {
	this(number, type, validThru);
	ArgumentChecks.isNotNull(client, "The client cannot be null");
	Associations.Pay.link(client, this);
    }

    public String getNumber() {
	return number;
    }

    public String getType() {
	return type;
    }

    public LocalDate getValidThru() {
	return validThru;
    }

    public void setValidThru(LocalDate date) {
	this.validThru = date;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((number == null) ? 0 : number.hashCode());
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
	CreditCard other = (CreditCard) obj;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CreditCard [number=" + number + ", type=" + type
		+ ", validThru=" + validThru + "]";
    }

    /**
     * Augments the accumulated (super.pay(amount) ) and decrements the
     * available
     * 
     * @throws IllegalStateException if card is expired
     */
    @Override
    public void pay(double amount) {
	StateChecks.isTrue(isValidNow());
	super.pay(amount);
    }

    public boolean isValidNow() {
	return validThru.isAfter(LocalDate.now());
    }

    @Override
    public boolean canPay(double amount) {
	return LocalDate.now().isBefore(validThru);
    }
}
