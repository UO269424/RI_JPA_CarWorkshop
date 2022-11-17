package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra la última nómina generada para un mecánico
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeletePayrollForMechanicAction implements Action {

    /**
     * Borra la nómina generada en el mes actual para el mecánico cuyo dni es
     * pasado por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Get info
	String id = Console.readString("Mechanic id ");

	// Process
	Factory.service.forPayrollService().deleteLastPayrollFor(id);

	// Print result
	Console.println(String.format(
		"Last payroll for mechanic %s successfully deleted", id));
    }

}
