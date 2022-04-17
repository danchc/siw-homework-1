package it.uniroma3.siw.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

class AllievoTest {

	private List<Corso> lc = new ArrayList<>();
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
	 * Si testa il funzionamento del PERSIST su Azienda.
	 */

	@Test
	public void testPersistWithCascade() throws Exception{
		Indirizzo i1 = new Indirizzo("via delle rose", 22, "roma", (long)12345, "roma");
		Azienda az1 = new Azienda("rag", (long)39221022 , i1);
		Allievo allievo1 = new Allievo("Mario", "Rossi", LocalDate.now(), (long)344321, "email@email.com",
				az1, this.lc);
		tx.begin();
		em.persist(allievo1);
		tx.commit();
		
		/* Query: in questo caso se viene aggiunto correttamente l'allievo allora
		 * nella tabella AZIENDA avremo l'azienda dell'allievo. */
		TypedQuery<Allievo> q0 = em.createQuery("SELECT a FROM Allievo a", Allievo.class);
		List<Allievo> allievidb = q0.getResultList();
		assertEquals(1, allievidb.size());
		TypedQuery<Azienda> q1 = em.createQuery("SELECT az FROM Azienda az", Azienda.class);
		List<Azienda> aziendedb = q1.getResultList();
		assertEquals(1, aziendedb.size());
	}
}
