package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Azienda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String ragioneSociale;
	
	@Column(nullable = false)
	private Long numeroTel;
	
	/*
	 * In questo caso si preferiscono eventi a cascata su PERSIST e REMOVE perchè si tratta di una composizione
	 * precisa: ad esempio, nel caso in cui venisse aggiunta una nuova azienda bisognerà specificare l'indirizzo e
	 * se non è presente aggiungerlo all'interno del database.
	 */
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) 
	private Indirizzo indirizzo;

	/*=================================================GETTERS&SETTERS====================*/
	
	public Azienda(String ragioneSociale, Long numeroTel, Indirizzo indirizzo) {
		this.ragioneSociale = ragioneSociale;
		this.numeroTel = numeroTel;
		this.indirizzo = indirizzo;
	}
	
	/**
	 * Il metodo ci aiuta a recuperare la ragione sociale dell'azienda in questione.
	 * @return ragioneSociale
	 */
	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	/**
	 * Il metodo ci permette di specificare la ragione sociale dell'azienda
	 * @param ragioneSociale
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * Il metodo ci permette di recuperare il numero di telefono dell'azienda
	 * @return numeroTel
	 */
	public Long getNumeroTel() {
		return this.numeroTel;
	}

	/**
	 * Il metodo ci permette di inserire il numero di telefono dell'azienda
	 * @param numeroTel
	 */
	public void setNumeroTel(Long numeroTel) {
		this.numeroTel = numeroTel;
	}

	/**
	 * Il metodo ci permette di recuperare l'indirizzo dell'azienda
	 * @return indirizzo
	 * @see Indirizzo
	 */
	public Indirizzo getIndirizzo() {
		return this.indirizzo;
	}

	/**
	 * Il metodo ci permette di inserire l'indirizzo dell'azienda
	 * @param indirizzo
	 * @see Indirizzo
	 */
	public void setIndirizzo(Indirizzo indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Il metodo ci permette di recuperare l'id dell'azienda.
	 * @return
	 */
	public Long getId() {
		return id;
	}

}
