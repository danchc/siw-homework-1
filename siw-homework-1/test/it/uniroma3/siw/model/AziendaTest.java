package it.uniroma3.siw.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AziendaTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeAll
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("entedb-unit-test");
		em = emf.createEntityManager();
	}
	@AfterAll
	public static void closeEntityManager() throws SQLException {
		if (em != null) em.close();
		if (emf != null) emf.close();
	}

	@BeforeEach
	public void initTransaction() {
		tx = em.getTransaction();
	}
	
	/*
	 * Si testa il funzionamento del PERSIST su Indirizzo.
	 */
	@Test
	public void testPersistWithCascade() throws Exception {
		Indirizzo i1 = new Indirizzo("via delle rose", 22, "roma", (long)12345, "roma");
		Azienda az1 = new Azienda("rag", (long)39221022 , i1);
		tx.begin();
		em.persist(az1);
		tx.commit();
		
		/*
		 * Query : se l'azienda è aggiunta correttamente allora avremo un indirizzo registrato
		 * automaticamente.
		 */
		TypedQuery<Azienda> q3 = em.createQuery("SELECT az FROM Azienda az", Azienda.class);
		List<Azienda> aziendedb = q3.getResultList();
		assertEquals(1, aziendedb.size());
		TypedQuery<Indirizzo> q4 = em.createQuery("SELECT i FROM Indirizzo i", Indirizzo.class);
		List<Indirizzo> indirizzidb = q4.getResultList();
		assertEquals(1, indirizzidb.size());

	}

}
