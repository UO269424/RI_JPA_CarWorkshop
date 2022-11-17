package uo.ri.ui.manager.professionalGroup.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.ui.util.Printer;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que crea un nuevo grupo porfesional.
 * 
 * (Esta clase no implementa ninguna funcionalidad requerida en mis casos de
 * uso)
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class AddProfessionalGroupAction implements Action {

    /**
     * Crea un grupo porfesional cuyo nombre, trienio y plus de productividad
     * son pasados por consola
     */
    @Override
    public void execute() throws Exception {

	// Get info
	String name = Console.readString("Name ");
	Double triennium = Console.readDouble("Triennium Salary ");
	Double productivityR = Console.readDouble("Productivity rate ");

	// Process

	ProfessionalGroupBLDto pg = new ProfessionalGroupBLDto();
	pg.trieniumSalary = triennium;
	pg.name = name;
	pg.productivityRate = productivityR;

	// Print result
	Console.println("New Professional Group succesfully added");
	Printer.printProfessionalGroup(Factory.service
		.forProfessionalGroupService().addProfessionalGroup(pg));
    }

}
