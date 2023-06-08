package negozioelettronica.test.prodotti.Sconti;

import junit.framework.TestCase;
import negozioelettronica.prodotti.sconti.ScontoFisso;

public class ScontoFissoTest extends TestCase {

	public ScontoFissoTest()
    {
    }
    


    /**
     * Test of scontaPrezzo method, of class ScontoFisso.
     */
    public void testScontaPrezzo()
    {
        float prezzo = 50.0F;
        ScontoFisso sconto = new ScontoFisso(5.0f);
        float result = sconto.scontaPrezzo(prezzo);
        assertEquals(45.0f, result, 0.01);
        
        sconto.setSconto(60.0f);
        result = sconto.scontaPrezzo(prezzo);
        assertEquals(0.0f, result, 0.01);
        
        sconto.setSconto(0.01f);
        result = sconto.scontaPrezzo(prezzo);
        assertEquals(49.99f, result, 0.01);
    }

}
