package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

public abstract class PaymentMean {
    // natural attributes
    private double accumulated = 0.0;

    // accidental attributes
    private Client client;
    private Set<Charge> charges = new HashSet<>();
    
    

    public PaymentMean(double accumulated, Client client, Set<Charge> charges) {
	this.accumulated = accumulated;
	this.client = client;
	this.charges = charges;
    }
    
    

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

}
