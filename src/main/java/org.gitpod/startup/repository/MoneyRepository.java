package org.gitpod.startup.repository;

import org.gitpod.startup.bank.MoneyRoll;

public interface MoneyRepository {

	public void make( MoneyRoll moneyRoll );    // C
	public MoneyRoll find( Long id );           // R
	public void update( MoneyRoll moneyRoll );  // U
	public void takeOut( MoneyRoll moneyRoll ); // D
}
