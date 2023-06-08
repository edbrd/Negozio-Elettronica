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
public interface Specifica {
	/**
	 * accetta un visitor
	 * 
	 * @param visitor
	 */
	public void accept(VisitorSpecifiche visitor);
}
