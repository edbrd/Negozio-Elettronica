/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti;

import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.visitors.ProdottoVisitor;

/**
 *
 * @author Bardeli Edmond
 */
public interface ProdottoInterface
{
    
    /**
     *
     * @return il prezzo del prodotto senza sconto
     */
    public float getPrezzo();

    /**
     *
     * setta il prezzo del prodotto
     * @param prezzo
     */
    public void setPrezzo(float prezzo);
    
    /**
     * 
     * @return il prezzo scontato
     */
    public float getPrezzoScontato();
   
    
    /**
     * 
     * @return lo strategy dello sconto
     */
    public Sconto getSconto();

    /**
     * setta lo sconto al prodotto
     *
     * @param sconto lo sconto del prodotto, anche null
     */
    public void setSconto(Sconto sconto);
    

    /**
     *
     * @return una stringa contenente le informazioni del prodotto
     */
    public String getInfo();

    /**
     *
     * @return il nome del prodotto
     */
    public String getNome();
    

    /**
     * setta il nome
     * @param nome il nomre del prodotto
     */
    public void setNome(String nome);
    
    /**
     * accetta un visitor
     * @param visitor 
     */
    public void accept(ProdottoVisitor visitor);
  
}
