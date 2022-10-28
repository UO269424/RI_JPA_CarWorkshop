package uo.ri.cws.domain;

import java.util.Objects;
import java.util.Set;

public class Voucher extends PaymentMean {
    public Voucher(double accumulated, Client client, Set<Charge> charges) {
	super(accumulated, client, charges);
	// TODO Auto-generated constructor stub
    }





    private String code;
    private double available = 0.0;
    private String description;
    
    
    
    

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
	Voucher other = (Voucher) obj;
	return Objects.equals(code, other.code);
    }





    /**
     * Augments the accumulated (super.pay(amount) ) and decrements the
     * available
     * 
     * @throws IllegalStateException if not enough available to pay
     */
    @Override
    public void pay(double amount) {

    }

}
