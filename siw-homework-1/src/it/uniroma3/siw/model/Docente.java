package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Docente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@Column(nullable = false)
	private String luogoNascita;
	
	@Column(nullable = false)
	private Long partitaIVA;
	
	@OneToMany(mappedBy = "docente")
	private List<Corso> corsi;

	/*=================================================GETTERS&SETTERS====================*/
	
	public Docente(String nome, String cognome, LocalDate dataNascita, String luogoNascita, Long partitaIVA,
		List<Corso> corsi	) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.luogoNascita = luogoNascita;
		this.partitaIVA = partitaIVA;
		this.corsi = corsi;
	}
	
	/**
	 * Il metodo ritorna il nome del docente.
	 * @return nome, il nome del docente.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Il metodo ci aiuta a settare il nome di un determinato allievo.
	 * @param nome, il nome del docente da inserire
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il metodo ritorna il cognome del docente.
	 * @return cognome, il cognome del docente.
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Il metodo ci aiuta a settare il cognome di un determinato docente.
	 * @param cognome, il cognome del docente
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Il metodo ci aiuta a recuperare la data di nascita del docente nel database
	 * @return dataNascita, la data di nascita del docente.
	 */
	public LocalDate getDataNascita() {
		return this.dataNascita;
	}

	/**
	 * Il metodo ci permette di inserire la data di nascita del docente da inserire nel database
	 * @param dataNascita
	 */
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return il luogo di nascita di un docente
	 */
	public String getLuogoNascita() {
		return luogoNascita;
	}

	/**
	 * @param luogoNascita, il luogo dove è nato un docente
	 */
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	/**
	 * @return la partitaIVA
	 */
	public Long getPartitaIVA() {
		return this.partitaIVA;
	}

	/**
	 * @param partitaIVA, la partitaIVA di un docente da inserire
	 */
	public void setPartitaIVA(Long partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	/**
	 * @return i corsi di un certo docente
	 */
	public List<Corso> getCorsi() {
		return this.corsi;
	}

	/**
	 * @param corsi, i corsi di un docente
	 */
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	/**
	 * @return  l'identificatore di un docente
	 */
	public Long getId() {
		return id;
	}
	
	
}
