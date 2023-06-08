/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.utils;

import negozioelettronica.prodotti.Pacco;
import negozioelettronica.prodotti.ProdottoInterface;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.user.User;

/**
*
* @author Bardeli Edmond
*/
public class Carrello extends Pacco
{
    private User user;
    
    public Carrello(User user)
    {
        super("Carrello", null);
        this.user=user;
    }

    /**
     * 
     * @return l'utente setato 
     */
    public User getUser()
    {
        return user;
    }

    /**
     * setta l'utente 
     * @param user 
     */
    public void setUser(User user)
    {
        this.user = user;
    }
    
    @Override
    public void setSconto(Sconto sconto)
    {
    	throw new UnsupportedOperationException("Non puoi setare direttamente uno sconto al carello, ma solo attraverso l'user");
    }
    
    /**
     * @return lo sconto della tessera dell'utente se c'e', altrimenti null
     */
    @Override
    public Sconto getSconto()
    {
    	Sconto sconto=null;
    	if(user!=null && user.getTessera()!=null)
    	{
    		sconto=user.getTessera().getSconto();
    	}
    	return sconto;
    }


    @Override
    public float getPrezzoScontato()
    {
        float prezzo=super.getPrezzoScontato();
        if(user!=null)
        {
            prezzo=user.getTessera().getSconto().scontaPrezzo(prezzo);
        }
        return prezzo;
    }
    
    
    
    
    
}
