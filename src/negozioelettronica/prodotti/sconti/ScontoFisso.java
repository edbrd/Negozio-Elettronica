package negozioelettronica.prodotti.sconti;

/**
 *Questa classe rappresenta uno sconto fisso che non va in base a una percentuale, ma a uno sconto fisso
 * @author Bardeli Edmond
 */
public class ScontoFisso extends ScontoAstratto
{
    private float sconto; // un valore fisso che viene sottratto al prezzo

    
    
    /**
     * initializza uno sconto cont il suo valore e senza nome
     * @param sconto valore dello sconto
     */
    public ScontoFisso(float sconto)
    {
        super(null);
        setSconto(sconto);
    }

   
    /**
     * initializza uno sconto con il suo nome e il suo valore fisso
     * @param nomeSconto
     * @param sconto valore dello sconto
     */
    public ScontoFisso(String nomeSconto,float sconto)
    {
        super(nomeSconto);
        setSconto(sconto);
    }

    /**
     * sconta il prezzo con il valore contenuto, 
     * @param prezzo
     * @return il prezzo scontato, mai minore di 0
     */
    @Override
    public float scontaPrezzo(float prezzo)
    {
        prezzo-=sconto;
        if(prezzo<0)
        {
            return 0;
        }
        return prezzo;
    }

    /**
     * 
     * @return il valore dello sconto
     */
    public float getSconto()
    {
        return sconto;
    }

    /**
     * setta il valore dello sconto
     * @param sconto il valore dello sconto
     * @throws IllegalArgumentException se lo sconto e' negativo
     * 
     */
    public void setSconto(float sconto)
    {
        if(sconto<0)
        {
            throw new IllegalArgumentException("Lo sconto non puo' essere negativo");
        }
        this.sconto = sconto;
    }
    
    @Override
    public String getInfo()
    {
        String info = super.getInfo();

        float val = sconto;

        if (val == 0)
        {
            info += " " + SCONTO_ZERO_STRING;
        } else
        {
            info += " " + val;
        }

        return info;
    }
    
}
