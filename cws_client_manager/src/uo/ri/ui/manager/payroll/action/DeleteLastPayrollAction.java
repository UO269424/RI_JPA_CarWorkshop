package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra las �ltimas n�minas generadas
 * 
 * @author Alonso Gago Su�rez - UO269424
 *
 */
public class DeleteLastPayrollAction implements Action {
    /**
     * Borra todas las n�minas generadas en el mes actual
     */
    @Override
    public void execute() throws BusinessException {

	// Process
	Factory.service.forPayrollService().deleteLastPayrolls();

	// Print result
	Console.println("Last payroll successfully deleted");
    }

}
