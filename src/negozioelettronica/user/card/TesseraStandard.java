/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.user.card;

import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoFisso;

/**
*
* @author Bardeli Edmond
*/
public class TesseraStandard extends TesseraAstratta
{

    public TesseraStandard()
    {
        super(new ScontoFisso("standard",5f));
    }

    @Override
    public String getNome()
    {
        return "Standard";
    }
    
}
