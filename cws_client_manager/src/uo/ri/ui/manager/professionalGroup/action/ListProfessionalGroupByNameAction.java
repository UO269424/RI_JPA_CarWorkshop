package uo.ri.ui.manager.professionalGroup.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que encuentra un grupo porfesional dado un nombre
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListProfessionalGroupByNameAction implements Action {

    /**
     * Encuentra un tipo un grupo porfesional dado su nombre pasado por consola
     */
    @Override
    public void execute() throws BusinessException {
	// Get info
	String name = Console.readString("Professional group name ");

	// Print result
	Printer.printProfessionalGroup(
		// Process
		Factory.service.forProfessionalGroupService()
			.findProfessionalGroupByName(name).get());

    }

}
