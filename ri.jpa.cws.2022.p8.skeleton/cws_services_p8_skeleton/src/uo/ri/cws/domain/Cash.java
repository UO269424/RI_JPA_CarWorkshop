package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import uo.ri.util.assertion.ArgumentChecks;

@Entity
@Table(name = "TCASHES")
public class Cash extends PaymentMean {

    Cash() {
    }

    public Cash(Client client) {
	ArgumentChecks.isNotNull(client);
	Associations.Pay.link(client, this);
    }

    @Override
    public boolean canPay(double amount) {
	return true;
    }

}
