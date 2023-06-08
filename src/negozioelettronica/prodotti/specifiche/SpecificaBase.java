/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.specifiche;

/**
 *
 * @author Bardeli Edmond
 */
public class SpecificaBase implements Specifica {
	private String nome;
	private String valore;

	public SpecificaBase(String nome, String valore) {
		this.nome = nome;
		this.valore = valore;
	}

	@Override
	public void accept(VisitorSpecifiche visitor) {
		visitor.visit(this);
	}

	/**
	 * 
	 * @return il nome della specifica
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * setta il nome della specifica
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return il valore della specifica sotto forma di stringa
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * setta il valore della specifica sotto forma di stringa
	 * 
	 * @param valore
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}

	/**
	 * 
	 * @return restituisce il nome e poi il suo valore
	 */
	public String getInfo() {
		return getNome() + " : " + getValore();
	}

}
