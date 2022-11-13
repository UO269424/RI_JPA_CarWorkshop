package uo.ri.cws.application.service.contract.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.contract.ContractService.ContractSummaryDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Contract;

public class FindAllContractsTS implements Command<List<ContractSummaryDto>> {

    @Override
    public List<ContractSummaryDto> execute() throws BusinessException {
	List<Contract> contracts = Factory.repository.forContract().findAll();
	return DtoAssembler.toContractSummaryDtoList(contracts);
    }

}
