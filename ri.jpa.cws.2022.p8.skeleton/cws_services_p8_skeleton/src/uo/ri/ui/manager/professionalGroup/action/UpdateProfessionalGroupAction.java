package uo.ri.ui.manager.professionalGroup.action;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.professionalgroup.ProfessionalGroupService.ProfessionalGroupBLDto;
import uo.ri.util.console.Console;
import uo.ri.util.menu.Action;

/**
 * Clase que actualiza un grupo porfesional dado su nombre
 * 
 * @author Alonso Gago Suárez - UO269424
 *
 */
public class UpdateProfessionalGroupAction implements Action {

    /**
     * Actualiza un grupo porfesional cuyo nombre es pasado por consola. El
     * grupo porfesional es actualizado con un nuevo plus de productividad y un
     * nuevo pago por trienio, también pasados por consola.
     */
    @Override
    public void execute() throws BusinessException {
	// Get info
	String name = Console.readString("Professional group name ");
	double t = Console.readDouble("Professional group triennium salary ");
	double p = Console
		.readDouble("Professional group productivity salary ");

	ProfessionalGroupBLDto dto = new ProfessionalGroupBLDto();
	dto.name = name;
	dto.productivityRate = p;
	dto.trieniumSalary = t;

	// Process
	Factory.service.forProfessionalGroupService()
		.updateProfessionalGroup(dto);

	// Print result
	Console.print("Professional group successfully updated");
    }

}
