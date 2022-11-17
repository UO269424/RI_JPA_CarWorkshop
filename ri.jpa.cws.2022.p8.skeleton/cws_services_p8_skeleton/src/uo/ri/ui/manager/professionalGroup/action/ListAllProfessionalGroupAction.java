package uo.ri.ui.manager.professionalGroup.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que lista por pantalla todos los grupos profesionales
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class ListAllProfessionalGroupAction implements Action {

    /**
     * Lista los grupos porfesionales por consola
     */
    @Override
    public void execute() throws BusinessException {

	// Print result
	Console.println("\nList of professional groups \n");

	Printer.printProfessionalGroupList(
		// Process
		Factory.service.forProfessionalGroupService()
			.findAllProfessionalGroups());

    }

}
