package uo.ri.ui.manager.professionalGroup.action;

import uo.ri.conf.Factory;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que borra un grupo profesional
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class DeleteProfessionalGroupAction implements Action {

    /**
     * Borra el grupo porfesional dado su nombre pasado por consola
     */
    @Override
    public void execute() throws Exception {

	// Get info
	String name = Console.readString("Professional group name ");

	// Process
	Factory.service.forProfessionalGroupService()
		.deleteProfessionalGroup(name);

	// Print result
	Console.print("Professional group successfully deleted");
    }

}
