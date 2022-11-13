package uo.ri.cws.application.service.payroll.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.payroll.PayrollService;
import uo.ri.cws.application.service.payroll.crud.command.DeleteLastPayrollForMechanicTS;
import uo.ri.cws.application.service.payroll.crud.command.GeneratePayrollTS;
import uo.ri.cws.application.util.command.CommandExecutor;

public class PayrollServiceImpl implements PayrollService {
    
    private final CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public void generatePayrolls() throws BusinessException {
	generatePayrolls(LocalDate.now());
    }

    @Override
    public void generatePayrolls(LocalDate present) throws BusinessException {
	executor.execute(new GeneratePayrollTS(present));

    }

    @Override
    public void deleteLastPayrollFor(String mechanicId)
	    throws BusinessException {
	executor.execute(new DeleteLastPayrollForMechanicTS(mechanicId));

    }

    @Override
    public void deleteLastPayrolls() throws BusinessException {
	executor.execute(new DeleteLastPayrollsTS());

    }

    @Override
    public Optional<PayrollBLDto> getPayrollDetails(String id)
	    throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<PayrollSummaryBLDto> getAllPayrolls() throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<PayrollSummaryBLDto> getAllPayrollsForMechanic(String id)
	    throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<PayrollSummaryBLDto> getAllPayrollsForProfessionalGroup(
	    String name) throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

}
