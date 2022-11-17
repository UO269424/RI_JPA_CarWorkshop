package uo.ri.ui.manager.payroll.action;

import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;
import uo.ri.util.menu.Action;

/**
 * Clase que lista por pantalla todas las n�minas
 * 
 * @author Alonso Gago Su�rez - UO269424
 *
 */
public class ListAllPayrollsAction implements Action {

    /**
     * Lista las n�minas por consola
     */
    @Override
    public void execute() throws Exception {
	// Print result
	Printer.printPayrollsSumary(
		// Process
		Factory.service.forPayrollService().getAllPayrolls());

    }

}
