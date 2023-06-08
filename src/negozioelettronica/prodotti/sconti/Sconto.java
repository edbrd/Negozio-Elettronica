/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.sconti;

/**
 *
 * @author Bardeli Edmond
 */
public interface Sconto
{
    
    /**
     * sconta il prezo in base alle caratteristiche dello sconto
     * @param prezzo
     * @return il prezzo scontato
     */
    public float scontaPrezzo(float prezzo);
    
    
    /**
     * 
     * @return una stringa con il nome dello sconto e lo sconto effetivo 
     */
    public String getInfo();
    
    /**
     * 
     * @return il nome dello sconto, potrebbe essere anche null
     */
    public String getNomeSconto();
    
    /**
     * setta il nome dello sconto, potrebbe essere anche null
     * @param nomeSconto 
     */
    public void setNomeSconto(String nomeSconto);
}
