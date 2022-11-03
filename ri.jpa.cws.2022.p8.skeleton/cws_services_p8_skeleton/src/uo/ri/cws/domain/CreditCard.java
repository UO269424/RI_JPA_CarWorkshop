package uo.ri.cws.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class CreditCard extends PaymentMean {
    public CreditCard(double accumulated, Client client, Set<Charge> charges) {
	super(accumulated, client, charges);
	// TODO Auto-generated constructor stub
    }
    private String number;
    private String type;
    private LocalDate validThru;
    
    
    @Override
    public int hashCode() {
	return Objects.hash(number);
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
	return Objects.equals(number, other.number);
    }
    
    
    

}
