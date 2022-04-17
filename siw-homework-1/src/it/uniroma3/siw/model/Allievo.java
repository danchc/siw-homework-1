package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Allievo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@Column(unique = true, nullable = false)
	private Long matricola;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@ManyToOne
	private Azienda azienda;
	
	@ManyToMany(mappedBy = "allievi")
	private List<Corso> corsiFrequentati;
	
	
	/*=================================================GETTERS&SETTERS==================== */
	
	public Allievo(String nome, String cognome, LocalDate dataNascita, Long matricola, String email,
			Azienda azienda, List<Corso> corsiFrequentati) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.matricola = matricola;
		this.email = email;
		this.azienda = azienda;
		this.corsiFrequentati = corsiFrequentati;
	}
	
	/**
	 * Il metodo ritorna il nome dell'allievo.
	 * @return nome, il nome dell'allievo.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Il metodo ci aiuta a settare il nome di un determinato allievo.
	 * @param nome, il nome dell'allievo da inserire
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Il metodo ritorna il cognome dell'allievo.
	 * @return cognome, il cognome dell'allievo.
	 */
	public String getCognome() {
		return this.cognome;
	}

	/**
	 * Il metodo ci aiuta a settare il cognome di un determinato allievo.
	 * @param cognome, il cognome desiderato dell'allievo
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Il metodo ci aiuta a recuperare la data di nascita dell'allievo nel database
	 * @return dataNascita, la data di nascita dell'allievo.
	 */
	public LocalDate getDataNascita() {
		return this.dataNascita;
	}

	/**
	 * Il metodo ci permette di inserire la data di nascita dell'allievo da inserire nel database
	 * @param dataNascita
	 */
	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Il metodo aiuta a recuperare la matricola dell'allievo. La matricola è unica.
	 * @return matricola, la matricola dell'allievo
	 */
	public Long getMatricola() {
		return this.matricola;
	}

	/**
	 * Il metodo ci permette di inserire una matricola differente per un allievo.
	 * @param matricola
	 */
	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}

	/**
	 * Il metodo ci permette di recuperare l'indirizzo di posta elettronica di un determinato allievo.
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Il metodo ci permette di inserire l'indirizzo di posta elettronica di un determinato allievo.
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.id;
	}
	
	
}
