/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.user.card;

import negozioelettronica.prodotti.sconti.Sconto;

/**
*
* @author Bardeli Edmond
*/
public class TesseraGuest extends TesseraAstratta
{

    public TesseraGuest()
    {
        super(null);
    }

    @Override
    public String getNome()
    {
        return "Senza tessera";
    }
    
}
