package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;
import uo.ri.util.menu.Action;

/**
 * Clase que lista por pantalla todas las nóminas
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListAllPayrollsAction implements Action {

    /**
     * Lista las nóminas por consola
     */
    @Override
    public void execute() throws Exception {
	// Print result
	Printer.printPayrollsSumary(
		// Process
		Factory.service.forPayrollService().getAllPayrolls());

    }

}
