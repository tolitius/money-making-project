package org.gitpod.startup.service;

import org.gitpod.startup.bank.Currency;
import org.gitpod.startup.bank.MoneyRoll;
import org.hibernate.HibernateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:META-INF/conf/spring/application-context.xml", 
	                    "classpath:META-INF/conf/spring/tx-missing-spring-config.xml",
                        "classpath:META-INF/conf/spring/test-datasource-spring-config.xml" })
public class HibernateSessionNotBoundToThreadIntegrationTest {

    private static final String NO_HIBERNATE_SESSION_BOUND_TO_THREAD =
            "No Hibernate Session bound to thread, " +
                    "and configuration does not allow creation of non-transactional one here";    

	@Resource( name="cleverMoneyMakingBusinessService" )
    MoneyMakingBusinessService businessService;

    @Test(expected=HibernateException.class)
	public void shouldThrowHibernateSessionNotBoundToThreadException() {

		MoneyRoll moneyRoll = new MoneyRoll();
		moneyRoll.setCurrency( Currency.GOOGLE_DOOLER );
		moneyRoll.setAmount( BigDecimal.valueOf( 1000000.99 ) );

        MoneyRoll cash = null;

        try {
            businessService.makeMoney( moneyRoll );
            cash = businessService.withdrawMoney( moneyRoll.getId() );

        } catch ( HibernateSystemException hse ) {
            // de-translating Spring DAO exception back to the vanilla Hibernate
            HibernateException he = ( HibernateException )hse.getRootCause();
            
            assertEquals( NO_HIBERNATE_SESSION_BOUND_TO_THREAD, he.getMessage() );
            throw he;
        }

        assertEquals( "some money was stolen from the roll",
					  BigDecimal.valueOf( 1000000.99 ), cash.getAmount() );
		
		assertEquals( "money is not in GD", Currency.GOOGLE_DOOLER, cash.getCurrency() );		
	}
}
