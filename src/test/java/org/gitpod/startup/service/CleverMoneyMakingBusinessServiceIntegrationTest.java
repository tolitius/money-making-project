package org.gitpod.startup.service;

import org.gitpod.startup.bank.Currency;
import org.gitpod.startup.bank.MoneyRoll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/conf/spring/application-context.xml",
                        "classpath:META-INF/conf/spring/test-datasource-spring-config.xml" })
public class CleverMoneyMakingBusinessServiceIntegrationTest {

	@Resource( name="cleverMoneyMakingBusinessService" )
    MoneyMakingBusinessService businessService;
	
	@Test
	public void shouldMakeMoney() {
                                                                            
		MoneyRoll moneyRoll = new MoneyRoll();
		moneyRoll.setCurrency( Currency.GOOGLE_DOOLER );
		moneyRoll.setAmount( BigDecimal.valueOf( 1000000.99 ) );
		
		businessService.makeMoney( moneyRoll );
		
		MoneyRoll cash = businessService.withdrawMoney( moneyRoll.getId() );
		
		assertEquals( "some money was stolen from the roll", 
					  BigDecimal.valueOf( 1000000.99 ), cash.getAmount() );
		
		assertEquals( "money is not in GD", Currency.GOOGLE_DOOLER, cash.getCurrency() );		
	}
}
