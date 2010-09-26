package org.gitpod.startup.repository;

import java.util.List;

import org.gitpod.startup.bank.MoneyRoll;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateMoneyRepository implements MoneyRepository {

	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public MoneyRoll find( Long rollId ) {
		
		MoneyRoll moneyRoll = null;
		
		Query query = getSession().getNamedQuery("find.moneyroll.by.id");
		query.setParameter( "id", rollId );
		
		List<MoneyRoll> moneyList = query.list();
		
		if ( moneyList.size() != 0 ) {
			moneyRoll = ( MoneyRoll )query.list().get( 0 );
		}
		
		return moneyRoll;
	}

	public void make( MoneyRoll moneyRoll ) {
		getSession().save( moneyRoll );
	}

	public void takeOut( MoneyRoll moneyRoll ) {
        getSession().delete( moneyRoll );
	}

	public void update(MoneyRoll money) {

		Query query = getSession().getNamedQuery("update.moneyroll");
		query.setParameter( "id", money.getId() );
		query.setParameter( "amount", money.getAmount() );
		query.setParameter( "currency", money.getCurrency() );
		
		query.executeUpdate();
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}