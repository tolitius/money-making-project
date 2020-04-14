package org.gitpod.startup.repository;

import org.gitpod.startup.bank.MoneyRoll;

public interface MoneyRepository {

	void make( MoneyRoll moneyRoll );    // C
	MoneyRoll find( Long id );           // R
	void update( MoneyRoll moneyRoll );  // U
	void takeOut( MoneyRoll moneyRoll ); // D
}
