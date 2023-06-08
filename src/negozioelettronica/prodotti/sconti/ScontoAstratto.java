/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.sconti;

import java.text.DecimalFormat;

/**
 *
 * @author Bardeli Edmond
 */
public abstract class ScontoAstratto implements Sconto
{
    private String nomeSconto;
    
    protected static final String SCONTO_ZERO_STRING = "-";

    


    /**
     * inizializza uno sconto astratto con un nome
     *
     * @param nomeSconto
     */
    public ScontoAstratto(String nomeSconto)
    {
        this.nomeSconto = nomeSconto;
    }

    @Override
    public String getNomeSconto()
    {
        return nomeSconto;
    }

    @Override
    public void setNomeSconto(String nomeSconto)
    {
        this.nomeSconto = nomeSconto;
    }
    
    @Override
    public String getInfo()
    {
        if (nomeSconto == null)
        {
            return "";
        } 
        return nomeSconto;
    }
    
    

}
