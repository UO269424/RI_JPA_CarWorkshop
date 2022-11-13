package uo.ri.cws.application.service.payroll.crud;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Payroll;

/**
 * Implementación de la interfaz Command que elimina todas las nóminas ya
 * generadas en el mes actual para todos los mecánicos.
 * 
 * Aplica patrón Transaction Script
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeleteLastPayrollsTS implements Command<Void> {

    /**
     * Elimina todas las nóminas ya generadas en el mes actual para todos los
     * mecánicos.
     * 
     * @throws BusinessException
     */
    @Override
    public Void execute() throws BusinessException {
	List<Payroll> payrolls = Factory.repository.forPayroll().findAll();
	if (payrolls.isEmpty())
	    return null;
	List<Payroll> thisMonth = new ArrayList<>();
	LocalDate present = LocalDate.now();
	for (Payroll p : payrolls) {
	    if (p.getDate().getMonth().equals(present.getMonth())
		    && p.getDate().getYear() == present.getYear())
		thisMonth.add(p);
	}

	if (thisMonth.isEmpty())
	    return null;

	for (Payroll p : thisMonth) {
	    Factory.repository.forPayroll().remove(p);
	}

	return null;
    }

}
