package negozioelettronica.test.prodotti.Sconti;

import junit.framework.TestCase;
import negozioelettronica.prodotti.sconti.ListaSconti;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoFisso;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;

public class ListaScontiTest extends TestCase {
	
private Sconto sconto1,sconto2;
    
    private ListaSconti listaSconti;
    
    
    public ListaScontiTest() {
    }

    @Override
    public void setUp()
    {
        sconto1=new ScontoFisso(9);
        sconto2=new ScontoPercentuale(0.1f);
        
        listaSconti=new ListaSconti("Testing sconti");
        
        listaSconti.add(sconto1);
        listaSconti.add(sconto2);
                
    }
    
    /**
     * testa lo sconta prezzo di una lista di sconti
     */
    public void testScontaPrezzo()
    {
        float prezzo=53;
        
        assertEquals(39.6, listaSconti.scontaPrezzo(prezzo),0.01);
        
        prezzo=4;
        
        assertEquals(0, listaSconti.scontaPrezzo(prezzo),0.01);
        
        prezzo=842.49f;
        
        assertEquals(750.14, listaSconti.scontaPrezzo(prezzo),0.01);
        
    }
}
