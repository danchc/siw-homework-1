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

class CorsoTest {
	private List<Corso> lc = new ArrayList<>();
	private List<Allievo> la = new ArrayList<>();
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
	 * Si testa il funzionamento del PERSIST su Docente.
	 */

	@Test
	public void testPersistWithCascade() throws Exception{
		Docente d1 = new Docente("Mario", "Rossi", LocalDate.now(), "Roma", (long)123 , this.lc);
		Corso c1 = new Corso("siw", LocalDate.now(), 3, this.la, d1);
		tx.begin();
		em.persist(c1);
		tx.commit();
		
		/*
		 * Query : se il corso è aggiunto correttamente allora avremo un docente registrato
		 * automaticamente.
		 */
		TypedQuery<Corso> q1 = em.createQuery("SELECT c FROM Corso c", Corso.class);
		List<Corso> corsidb = q1.getResultList();
		assertEquals(1, corsidb.size());
		TypedQuery<Docente> q2 = em.createQuery("SELECT d FROM Docente d", Docente.class);
		List<Docente> docentidb = q2.getResultList();
		assertEquals(1, docentidb.size());
	}
}
