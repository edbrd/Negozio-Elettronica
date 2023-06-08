package negozioelettronica.test.prodotti.Sconti;

import junit.framework.TestCase;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;

public class ScontoPercentualeTest extends TestCase 
{
	/**
     * Test of getScontoValore method, of class ScontoPercentuale.
     */
    public void testScontaPrezzo()
    {
        float prezzo = 50f;
        Sconto sconto = new ScontoPercentuale(0.5f);
    
        float result = sconto.scontaPrezzo(prezzo);
        assertEquals(25f, result, 0.01);
        
        
        Sconto sconto2=new ScontoPercentuale(1);
        
        result=sconto2.scontaPrezzo(prezzo);
        assertEquals(0f, result,0.01);
        
        
        prezzo=100;
        
        Sconto sconto3=new ScontoPercentuale(0f);
        result=sconto3.scontaPrezzo(prezzo);
        assertEquals(100f, result,0.01);
    }

    /**
     * Test of setScontoValore method, of class ScontoPercentuale.
     */
    public void testSetScontoValore()
    {
        float prezzo = 75f;
        
        ScontoPercentuale sconto = new ScontoPercentuale(0.1f);
        
        float result = sconto.scontaPrezzo(prezzo);
        assertEquals(67.5f, result, 0.01);
        
        
        sconto.setScontoValore(0.2f);
       
        
        result=sconto.scontaPrezzo(prezzo);
        assertEquals(60f, result, 0.01);
        
        sconto.setScontoValore(0f);
        
        result=sconto.scontaPrezzo(prezzo);
        
        assertEquals(75f, result,0.01);
        
    }
}
