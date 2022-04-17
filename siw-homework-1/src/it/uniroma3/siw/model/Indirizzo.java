package it.uniroma3.siw.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Indirizzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String via;
	
	@Column(nullable = false)
	private Integer civico;
	
	@Column(nullable = false)
	private String comune;
	
	@Column(nullable = false)
	private Long cap;
	
	@Column(nullable = false)
	private String provincia;
	
	/*=================================================GETTERS&SETTERS====================*/

	public Indirizzo(String via, Integer civico, String comune, Long cap, String provincia) {
		this.via = via;
		this.civico = civico;
		this.comune = comune;
		this.cap = cap;
		this.provincia = provincia;
	}
	
	/**
	 * @return la via dove è situata l'azienda
	 */
	public String getVia() {
		return this.via;
	}

	/**
	 * @param via, la via di una determinata azienda
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/**
	 * @return il civico della via dove è situata l'azienda
	 */
	public Integer getCivico() {
		return this.civico;
	}

	/**
	 * @param civico, il civico di una determinata azienda
	 */
	public void setCivico(Integer civico) {
		this.civico = civico;
	}

	/**
	 * @return il comune dove è situata una certa azienda
	 */
	public String getComune() {
		return this.comune;
	}

	/**
	 * @param comune, il comune di una determinata azienda
	 */
	public void setComune(String comune) {
		this.comune = comune;
	}

	/**
	 * @return il cap della città dove è situata l'azienda (es. 04011)
	 */
	public Long getCap() {
		return this.cap;
	}

	/**
	 * @param cap, il cap della città dove è situata una certa azienda da registrare
	 */
	public void setCap(Long cap) {
		this.cap = cap;
	}

	/**
	 * @return la provincia dove è situata l'azienda
	 */
	public String getProvincia() {
		return this.provincia;
	}

	/**
	 * @param provincia, la provincia dove è situata una certa azienda da registrare
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	/**
	 * @return l'identificatore dell'indirizzo.
	 */
	public Long getId() {
		return this.id;
	}
	
	
}
