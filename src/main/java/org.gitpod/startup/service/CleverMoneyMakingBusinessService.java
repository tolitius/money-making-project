package org.gitpod.startup.service;

import org.gitpod.startup.bank.MoneyRoll;
import org.gitpod.startup.repository.MoneyRepository;

public class CleverMoneyMakingBusinessService implements MoneyMakingBusinessService {

	private MoneyRepository moneyRepository;
	
	public void makeMoney( MoneyRoll money )  {
		moneyRepository.make( money );
	}

	public MoneyRoll withdrawMoney( Long moneyRollId ) {
		return moneyRepository.find( moneyRollId );
	}

	public void setMoneyRepository( MoneyRepository moneyRepository ) {
		this.moneyRepository = moneyRepository;
	}
}
