package todo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;


public class MyDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();

	public void saveCustomer(Customer customer) {
		manager.getTransaction().begin();
		manager.persist(customer);
		manager.getTransaction().commit();
	}

	public Customer findByEmail(String email) {
		List<Customer> customers = manager.createQuery("select x from Customer x where email=?1").setParameter(1, email)
				.getResultList();
		if (customers.isEmpty())
			return null;
		else
			return customers.get(0);
	}
}
	

