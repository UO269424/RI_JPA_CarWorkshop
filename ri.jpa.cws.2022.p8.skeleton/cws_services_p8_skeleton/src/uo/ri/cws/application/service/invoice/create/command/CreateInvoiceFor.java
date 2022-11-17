package uo.ri.cws.application.service.invoice.create.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.InvoiceRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.InvoicingService.InvoiceDto;
import uo.ri.cws.application.util.BusinessChecks;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Invoice;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.util.assertion.ArgumentChecks;

public class CreateInvoiceFor implements Command<InvoiceDto> {

    private List<String> workOrderIds;
    private WorkOrderRepository wrkrsRepo = Factory.repository.forWorkOrder();
    private InvoiceRepository invsRepo = Factory.repository.forInvoice();

    public CreateInvoiceFor(List<String> workOrderIds) {
	ArgumentChecks.isNotNull(workOrderIds);
	ArgumentChecks.isTrue(workOrderIds.size() > 0);
	for (String id : workOrderIds) {
	    ArgumentChecks.isNotNull(id);
	    ArgumentChecks.isNotEmpty(id.trim());
	}
	this.workOrderIds = workOrderIds;
    }

    @Override
    public InvoiceDto execute() throws BusinessException {

	List<WorkOrder> workOrders = wrkrsRepo.findByIds(workOrderIds);
	BusinessChecks.isTrue(workOrders.size() == workOrderIds.size(),
		"Some workorder does not exist");

	BusinessChecks.isTrue(allFinished(workOrders));
	Long numero = invsRepo.getNextInvoiceNumber();
	if (numero == null) {
	    numero = 1L;
	}

	Invoice i = new Invoice(numero, workOrders);
	invsRepo.add(i);

	return DtoAssembler.toDto(i);
    }

    private boolean allFinished(List<WorkOrder> wo) {
	for (WorkOrder w : wo) {
	    if (!w.isFinished())
		return false;
	}
	return true;
    }

}
