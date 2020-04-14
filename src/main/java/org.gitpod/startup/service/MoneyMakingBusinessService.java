package org.gitpod.startup.service;

import org.gitpod.startup.bank.MoneyRoll;


public interface MoneyMakingBusinessService {

	void makeMoney( MoneyRoll money );
	
	MoneyRoll withdrawMoney( Long moneyRollId );
}
