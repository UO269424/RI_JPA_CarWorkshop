package uo.ri.cws.domain;

import java.util.Objects;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;

@Entity
@Table(name = "TCHARGES", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "INVOICE_ID", "PAYMENTMEAN_ID" }) })
public class Charge extends BaseEntity {
    // natural attributes
    private double amount = 0.0;

    // accidental attributes
    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private PaymentMean paymentMean;

    Charge() {
    }

    public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
	this.amount = amount;
	paymentMean.pay(amount);
	Associations.ToCharge.link(paymentMean, this, invoice);
	// store the amount
	// increment the paymentMean accumulated -> paymentMean.pay( amount )
	// link invoice, this and paymentMean
    }

    public double getAmount() {
	return amount;
    }

    public void setAmount(double amount) {
	this.amount = amount;
    }

    public Invoice getInvoice() {
	return invoice;
    }

    void _setInvoice(Invoice invoice) {
	this.invoice = invoice;
    }

    public PaymentMean getPaymentMean() {
	return paymentMean;
    }

    void _setPaymentMean(PaymentMean paymentMean) {
	this.paymentMean = paymentMean;
    }

    @Override
    public int hashCode() {
	return Objects.hash(invoice, paymentMean);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Charge other = (Charge) obj;
	return Objects.equals(invoice, other.invoice)
		&& Objects.equals(paymentMean, other.paymentMean);
    }

    /**
     * Unlinks this charge and restores the accumulated to the payment mean
     * 
     * @throws IllegalStateException if the invoice is already settled
     */
    public void rewind() {
	// asserts the invoice is not in PAID status
	// decrements the payment mean accumulated ( paymentMean.pay( -amount) )
	// unlinks invoice, this and paymentMean
    }

}
