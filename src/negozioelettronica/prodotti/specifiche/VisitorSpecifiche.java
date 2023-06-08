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
public interface VisitorSpecifiche {
	/**
	 * visita un oggetto di tipo specifica base
	 * 
	 * @param specificaBase
	 */
	public void visit(SpecificaBase specificaBase);

	/**
	 * visita un oggetto di tipo lista specifiche
	 * 
	 * @param listaSpecifiche
	 */
	public void visit(ListaSpecifiche listaSpecifiche);
}
