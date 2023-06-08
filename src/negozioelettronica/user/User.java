package negozioelettronica.user;

import negozioelettronica.user.card.TesseraGuest;
import negozioelettronica.user.card.TesseraInterface;


/**
*
* @author Bardeli Edmond
*/
public class User
{
    private String titolo;
    private String username;
    private TesseraInterface tessera;

    public User(String titolo,String username, TesseraInterface tessera)
    {
        this.titolo=titolo;
        this.username = username;
        setTessera(tessera);
    }

    /**
     * 
     * @return il titolo della user
     */
    public String getTitolo()
    {
        if(titolo==null)
        {
            return "";
        }
        return titolo;
    }

    /**
     * setta il titolo del user
     * @param titolo 
     */
    public void setTitolo(String titolo)
    {
        this.titolo = titolo;
    }
    
    

    /**
     * 
     * @return l'username dell'utente, se l'username e' null o vuoto allora viene ritornato Guest
     */
    public String getUsername()
    {
        if(username==null || username.isEmpty())
        {
            return "Guest";
        }
        return username;
    }

    /**
     * setta l'username dell'utente
     * @param username 
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     *
     * @return la tessera dell'utente
     */
    public TesseraInterface getTessera()
    {
        return tessera;
    }

    /**
     * setta la tessera dell'utente
     * @param tessera la tessera dell'utente, se null allora viene inserita una tessera Guest
     */
    public void setTessera(TesseraInterface tessera)
    {
        if(tessera==null)
        {
            tessera=new TesseraGuest();
        }
        this.tessera = tessera;
    }
    
    /**
     * 
     * @return le informazioni dell'utente
     */
    public String getInfo()
    {
        return getInfoUtente()+" , "+getTessera().getInfo();
    }
    
    /**
     * 
     * @return ritorna il titolo con il nome dell'utente 
     */
    public String getInfoUtente()
    {
       return getTitolo()+" "+getUsername();
    }
}
