package uo.ri.cws.domain;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;

@Entity
@Table(name="TCONTRACTTYPES")
public class ContractType extends BaseEntity {

    public ContractType()	{
	
    }
    
    public ContractType(String string, double compensationDays) {
	// TODO Auto-generated constructor stub
    }

}
