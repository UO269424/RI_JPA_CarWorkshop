package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra las últimas nóminas generadas
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeleteLastPayrollAction implements Action {
    /**
     * Borra todas las nóminas generadas en el mes actual
     */
    @Override
    public void execute() throws BusinessException {

	// Process
	Factory.service.forPayrollService().deleteLastPayrolls();

	// Print result
	Console.println("Last payroll successfully deleted");
    }

}
