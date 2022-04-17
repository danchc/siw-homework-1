package it.uniroma3.siw.main;

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

import it.uniroma3.siw.model.Allievo;
import it.uniroma3.siw.model.Azienda;
import it.uniroma3.siw.model.Corso;
import it.uniroma3.siw.model.Docente;
import it.uniroma3.siw.model.Indirizzo;

class EnteTest {

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
	
	/* Abbiamo 5 classi : Docente, Corso, Allievo, Azienda, Indirizzo.
	 * Testiamo se l'aggiunta al database avviene in maniera corretta. */
	@Test
	public void testPersist() throws Exception{
		/* DOCENTE */
		List<Corso> lc = new ArrayList<Corso>();
		Docente d1 = new Docente("Mario", "Rossi", LocalDate.now(), "Roma", (long)123 , lc);
		tx.begin();
		em.persist(d1);
		tx.commit();
		TypedQuery<Docente> q1 = em.createQuery("SELECT d FROM Docente d", Docente.class);
		List<Docente> docenti = q1.getResultList();
		assertEquals(1, docenti.size());
		
		/* CORSO */
		List<Allievo> la = new ArrayList<Allievo>();
		Corso c1 = new Corso("Corso 1", LocalDate.now(), 2, la, d1);
		tx.begin();
		em.persist(c1);
		tx.commit();
		TypedQuery<Corso> q2 = em.createQuery("SELECT c FROM Corso c", Corso.class);
		List<Corso> corsi = q2.getResultList();
		assertEquals(1, corsi.size());
		
		/* AZIENDA */
		Indirizzo i1 = new Indirizzo("via delle rose", 22, "roma", (long)12345, "roma");
		Azienda az1 = new Azienda("rag", (long)39221022 , i1);
		tx.begin();
		em.persist(az1);
		tx.commit();
		TypedQuery<Azienda> q3 = em.createQuery("SELECT a FROM Azienda a", Azienda.class);
		List<Azienda> aziende = q3.getResultList();
		assertEquals(1, aziende.size());
		
		/* ALLIEVO */
		Allievo a1 = new Allievo("Paolo", "Rossi", LocalDate.now(), (long)12345, "pao.rossi@gmail.com", az1, lc);
		tx.begin();
		em.persist(a1);
		tx.commit();
		TypedQuery<Allievo> q4 = em.createQuery("SELECT a from Allievo a", Allievo.class);
		List<Allievo> allievi = q4.getResultList();
		assertEquals(1, allievi.size());
	}
}
