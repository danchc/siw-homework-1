package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Corso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private LocalDate dataInizio;
	
	@Column(nullable = false)
	private Integer durata;											//si tratta della durata in mesi (1-12)
	
	@ManyToMany
	private List<Allievo> allievi;
	
	/*
	 * In questo caso si preferisce un Fetch di tipo Eager perchè l'utente ogni volta che andrà a richiedere
	 * le informazioni che riguardano un certo corso bisognerà fornire anche il docente di quel determinato
	 * corso.
	 * Si preferisce invece un evento a cascata di tipo PERSIST perchè magari si vuole aggiungere un determinato
	 * corso specificando un docente ancora non inserito all'interno del database, quindi per facilitare
	 * le operazioni viene aggiunto automaticamente nel database corrente.
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
	private Docente docente;
	
	/*=================================================GETTERS&SETTERS====================*/

	public Corso(String nome, LocalDate dataInizio, Integer durata, List<Allievo> allievi, Docente docente) {
		this.nome = nome;
		this.dataInizio = dataInizio;
		this.durata = durata;
		this.allievi = allievi;
		this.docente = docente;
	}
	
	/**
	 * @return il nome del corso
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome, il nome del corso da registrare
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return la data dell'inizio del corso
	 */
	public LocalDate getDataInizio() {
		return this.dataInizio;
	}

	/**
	 * @param dataInizio, la data di quando inizia un determinato corso
	 */
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	 * @return la durata del corso in mesi
	 */
	public Integer getDurata() {
		return this.durata;
	}

	/**
	 * @param durata, la durata in mesi di un determinato corso
	 */
	public void setDurata(Integer durata) {
		this.durata = durata;
	}

	/**
	 * @return gli allievi iscritti ad un corso
	 */
	public List<Allievo> getAllievi() {
		return this.allievi;
	}

	/**
	 * @param allievi, la lista degli allievi che si iscrivono ad un corso
	 */
	public void setAllievi(List<Allievo> allievi) {
		this.allievi = allievi;
	}

	/**
	 * @return l'identificatore di un corso.
	 */
	public Long getId() {
		return this.id;
	}
	
	
	
	
	
}
