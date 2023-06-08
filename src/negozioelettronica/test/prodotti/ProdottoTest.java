package negozioelettronica.test.prodotti;

import junit.framework.TestCase;
import negozioelettronica.prodotti.Prodotto;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;

public class ProdottoTest extends TestCase {
	
	    public void testProdottoSenzaSconto()
	    {
	        Prodotto prodotto=new Prodotto("Primo prodotto", 10, null);
	        
	        assertEquals(10, prodotto.getPrezzo(),0.01);
	        assertEquals(10,prodotto.getPrezzoScontato(),0.01);
	        
	        assertEquals("Primo prodotto", prodotto.getNome());
	        assertNotSame("Primo prodott", prodotto.getNome());
	        
	        assertNull(prodotto.getSconto());
	        
	        prodotto.setNome("Lamette Gilette.");
	        
	        assertEquals("Lamette Gilette.", prodotto.getNome());
	        
	        try
	        {
	            prodotto.setPrezzo(-1);
	            fail("Non doveva permettere un settagio del prezzo negativo");
	        }catch(IllegalArgumentException e)
	        {
	            
	        }
	        
	        prodotto.setPrezzo(15);
	        
	        assertEquals(15, prodotto.getPrezzo(),0.01);
	        assertEquals(15,prodotto.getPrezzoScontato(),0.01);
	    }
	    
	    public void testProdottoScontoPercentuale(){
	        
	        Sconto sconto=new ScontoPercentuale(0.50f);
	        Prodotto prodotto=new Prodotto("Secondo prodotto",20,sconto);
	        
	        assertEquals(10,prodotto.getPrezzoScontato(),0.01);
	        
	        assertEquals(20, prodotto.getPrezzo(),0.01);
	        
	        sconto=new ScontoPercentuale(0.25f);
	        prodotto.setSconto(sconto);
	        
	        assertEquals(15,prodotto.getPrezzoScontato(),0.01);
	        assertEquals(20, prodotto.getPrezzo(),0.01);
	        
	        Sconto scontoUguale = new ScontoPercentuale(0.25f);
	        Prodotto prodottoUguale= new Prodotto("Secondo prodotto", 20,scontoUguale);
	        
	        assertEquals(prodotto,prodottoUguale);
	        
	        prodotto.setPrezzo(15);
	        
	        assertEquals(15, prodotto.getPrezzo(),0.01);
	        assertEquals(11.25,prodotto.getPrezzoScontato(),0.01);
	    }
}
