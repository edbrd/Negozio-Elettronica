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
public  interface TesseraInterface
{
    /**
     * 
     * @return lo sconto della tessera 
     */
    public Sconto getSconto();
    
    /**
     * 
     * @return le informazioni della tessera 
     */
    public String getInfo();
    
    /**
     * 
     * @return il nome della tessera 
     */
    public String getNome();
}
