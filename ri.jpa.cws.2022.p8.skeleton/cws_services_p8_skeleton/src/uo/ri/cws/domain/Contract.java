package uo.ri.cws.domain;

import javax.persistence.*;

import uo.ri.cws.domain.base.BaseEntity;

@Entity
@Table(name="TCONTRACTS")
public class Contract extends BaseEntity {

    public enum ContractState {
	TERMINATED, IN_FORCE

    }

}
