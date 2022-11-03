package uo.ri.cws.domain;

import java.util.Set;

public class Cash extends PaymentMean {

    public Cash(double accumulated, Client client, Set<Charge> charges) {
	super(accumulated, client, charges);
	// TODO Auto-generated constructor stub
    }

}
