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

	private List<Corso> lc = new ArrayList<Corso>();
	private List<Allievo> la = new ArrayList<Allievo>();
	
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
	public void testPersistDocente() throws Exception{
		/* DOCENTE */
		Docente d1 = new Docente("Mario", "Rossi", LocalDate.now(), "Roma", (long)123 , this.lc);
		tx.begin();
		em.persist(d1);
		tx.commit();
		TypedQuery<Docente> q1 = em.createQuery("SELECT d FROM Docente d", Docente.class);
		List<Docente> docenti = q1.getResultList();
		assertEquals(1, docenti.size());
	}
	
	@Test
	public void testPersistCorso() throws Exception{
		Docente d1 = new Docente("Mario", "Rossi", LocalDate.now(), "Roma", (long)123 , this.lc);
		/* CORSO */
		Corso c1 = new Corso("Corso 1", LocalDate.now(), 2, this.la, d1);
		tx.begin();
		em.persist(c1);
		tx.commit();
		TypedQuery<Corso> q2 = em.createQuery("SELECT c FROM Corso c", Corso.class);
		List<Corso> corsi = q2.getResultList();
		assertEquals(1, corsi.size());
	}
	
	@Test
	public void testPersistAzienda() throws Exception{
		/* AZIENDA */
		Indirizzo i1 = new Indirizzo("via delle rose", 22, "roma", (long)12345, "roma");
		Azienda az1 = new Azienda("rag", (long)39221022 , i1);
		tx.begin();
		em.persist(az1);
		tx.commit();
		TypedQuery<Azienda> q3 = em.createQuery("SELECT az FROM Azienda az", Azienda.class);
		List<Azienda> aziende = q3.getResultList();
		assertEquals(2, aziende.size());
		assertEquals("studio", aziende.get(0).getRagioneSociale()); //si riferisce al test sottostante
		assertEquals("rag", aziende.get(1).getRagioneSociale());
	}
	
	@Test
	public void testPersistAllievo() throws Exception{
		/* ALLIEVO */
		Indirizzo i2 = new Indirizzo("via delle more", 43, "roma", (long)12345, "roma");
		Azienda az1 = new Azienda("studio", (long)3945322 , i2);
		Allievo a1 = new Allievo("Paolo", "Rossi", LocalDate.now(), (long)12345, "pao.rossi@gmail.com", az1, lc);
		tx.begin();
		em.persist(a1);
		tx.commit();
		TypedQuery<Allievo> q4 = em.createQuery("SELECT a from Allievo a", Allievo.class);
		List<Allievo> allievi = q4.getResultList();
		assertEquals(1, allievi.size());
	}
}
