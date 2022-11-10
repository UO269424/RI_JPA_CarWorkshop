package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;

@Entity
@Table(name = "TPaymentMeans")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentMean extends BaseEntity {
    // natural attributes
    private double accumulated = 0.0;

    // accidental attributes
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "paymentMean")
    private Set<Charge> charges = new HashSet<>();

//    public PaymentMean(double accumulated, Client client, Set<Charge> charges) {
//	this.accumulated = accumulated;
//	this.client = client;
//	this.charges = charges;
//    }

    public double getAccumulated() {
	return accumulated;
    }

    public void setAccumulated(double accumulated) {
	this.accumulated = accumulated;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

    public void setCharges(Set<Charge> charges) {
	this.charges = charges;
    }

    public void pay(double importe) {
	this.accumulated += importe;
    }

    void _setClient(Client client) {
	this.client = client;
    }

    public Set<Charge> getCharges() {
	return new HashSet<>(charges);
    }

    Set<Charge> _getCharges() {
	return charges;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		PaymentMean other = (PaymentMean) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentMean [client=" + client + "]";
	}
	
	public abstract boolean canPay(double amount);


}
