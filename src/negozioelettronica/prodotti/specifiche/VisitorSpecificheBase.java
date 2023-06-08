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
public class VisitorSpecificheBase implements VisitorSpecifiche {
	private static final String SPAZIO_AGGIUNTIVO = "   ";

	private String specifiche = "";
	private String spazio = "";

	/**
	 * crea un nuovo visitor per le specifiche
	 * 
	 * @param spazio
	 *            lo spazio aggiunto prima di ogni specifica
	 */
	public VisitorSpecificheBase(String spazio) {
		clear();
		this.spazio = spazio;
	}

	/**
	 * pulisce le specifiche
	 */
	public void clear() {
		specifiche = "";
	}

	/**
	 * 
	 * @return ritorna la stringa delle specifiche visitate
	 */
	public String getSpecifiche() {
		return specifiche;
	}

	/**
	 * setta lo spazio dal bordo da aggiungere prima di ogni specifica
	 * 
	 * @param spazio
	 */
	public void setSpazio(String spazio) {
		this.spazio = spazio;
	}

	@Override
	public void visit(SpecificaBase specificaBase) {
		specifiche += "\n" + createSpazio() + specificaBase.getInfo();
	}

	/**
	 * aggiunge allo spazio esistente lo spazio aggiuntivo
	 * 
	 * @return
	 */
	private String createSpazio() {
		return SPAZIO_AGGIUNTIVO + spazio;
	}

	@Override
	public void visit(ListaSpecifiche listaSpecifiche) {
		for (Specifica specifica : listaSpecifiche) {
			specifica.accept(this);
		}
	}

}
