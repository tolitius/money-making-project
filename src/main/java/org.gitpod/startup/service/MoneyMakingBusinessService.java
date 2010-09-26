package org.gitpod.startup.service;

import org.gitpod.startup.bank.MoneyRoll;


public interface MoneyMakingBusinessService {

	public void makeMoney( MoneyRoll money );
	
	public MoneyRoll withdrawMoney( Long moneyRollId );	
}
