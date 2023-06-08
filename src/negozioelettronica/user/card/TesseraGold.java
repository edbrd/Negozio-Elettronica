/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.user.card;

import negozioelettronica.prodotti.sconti.ListaSconti;

import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoFisso;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;

/**
*
* @author Bardeli Edmond
*/
public class TesseraGold extends TesseraAstratta
{

    public TesseraGold()
    {
        
        super(new ListaSconti()
                {{
                    add(new ScontoFisso("Gold",5f));
                    add(new ScontoPercentuale("Ulteriore",0.1f));
                }});
    }

    @Override
    public String getNome()
    {
        return "Gold";
    }
    
}
