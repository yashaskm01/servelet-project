package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class MyDao {
	EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction=manager.getTransaction();
	public void save(Customer customer) {
		transaction.begin();
		manager.persist(customer);
		transaction.commit();
	}
	public Customer fetchByEmail(String email) {
//		jpql
		Query query = manager.createQuery("select x from Customer x where email=?1").setParameter(1, email);
		List<Customer> list=query.getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	public Customer fetchByMobile(long mobile) {
//		jpql
		List<Customer> list = manager.createQuery("select x from Customer x where mobile=?1").setParameter(1, mobile).getResultList();
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
