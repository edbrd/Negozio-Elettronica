/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.visitors;

import negozioelettronica.prodotti.Pacco;
import negozioelettronica.prodotti.Prodotto;

/**
*
* @author Bardeli Edmond
*/
public interface ProdottoVisitor
{
    /**
     * visita un prodotto
     * @param prodotto 
     */
    public void visit(Prodotto prodotto);
    
    /**
     * visita un pacco
     * @param pacco 
     */
    public void visit(Pacco pacco);
}
