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
public abstract class TesseraAstratta implements TesseraInterface
{
    
    private Sconto scontoTessera;
    
    /**
     * inizialliza una nuova tessera
     * 
     * @param scontoTessera lo sconto della tessera se e' null viene iniziallizata ad uno sconto di default 0
     */
    public TesseraAstratta (Sconto scontoTessera)
    {
        if(scontoTessera == null){
            scontoTessera = new ScontoFisso(0);
        }
        this.scontoTessera = scontoTessera;
    }
    
    /**
     * 
     * @return lo sconto della tessera, mai null 
     */
    @Override
    public Sconto getSconto()
    {
        return scontoTessera;
    }


    @Override
    public String getInfo()
    {
        String info = getNome();
       
        if(info==null)
        {
            info = "";
        }
        
        info += " , sconto: ";
        
        if(scontoTessera==null)
        {
            info += "-";
        }else
        {
            info += scontoTessera.getInfo();
        }
        
        return info;
            
    }

 
}
