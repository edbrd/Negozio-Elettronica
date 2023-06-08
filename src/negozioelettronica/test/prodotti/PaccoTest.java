package negozioelettronica.test.prodotti;

import junit.framework.TestCase;
import negozioelettronica.prodotti.Pacco;
import negozioelettronica.prodotti.Prodotto;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;

public class PaccoTest extends TestCase {

	private Sconto sconto1, sconto2, sconto3;

    private Prodotto prodotto1, prodotto2, prodotto3;

    private Pacco pacco1, pacco2;

    @Override
    public void setUp() throws Exception
    {
        this.sconto1 = new ScontoPercentuale(0.1f);
        this.sconto2 = new ScontoPercentuale(0.2f);
        this.sconto3 = new ScontoPercentuale(0.25f);

        this.prodotto1 = new Prodotto("prodotto1", 10, sconto1);
        this.prodotto2 = new Prodotto("prodotto2", 20, sconto2);
        this.prodotto3 = new Prodotto("prodotto3", 30, sconto3);

        this.pacco1 = new Pacco("PaccoPiccolo", null);
        this.pacco2 = new Pacco("PaccoGrande", null);
    }
    
   

    public void testPaccoSenzaSconto()
    {
        pacco1.add(prodotto1);
        assertEquals(1, pacco1.size());
        assertEquals(10, pacco1.getPrezzo(), 0);
        assertEquals(9, pacco1.getPrezzoScontato(), 0);

        pacco1.add(prodotto2);

        assertEquals(2, pacco1.size());
        assertEquals(30, pacco1.getPrezzo(), 0);
        assertEquals(25, pacco1.getPrezzoScontato(), 0);

        pacco2.add(pacco1);
        pacco2.add(prodotto3);

        assertEquals(2, pacco2.size());
        assertEquals(60, pacco2.getPrezzo(), 0);
        assertEquals(47.5, pacco2.getPrezzoScontato(), 0);

    }

    public void testPaccoScontato()
    {
        Sconto scontoPacco1 = new ScontoPercentuale(0.5f);
        
        pacco1.setSconto(scontoPacco1);
        
        Sconto scontoPacco2 = new ScontoPercentuale(0.1f);
        
        pacco2.setSconto(scontoPacco2);

        pacco1.add(prodotto1);

        assertEquals(1, pacco1.size());
        assertEquals(10, pacco1.getPrezzo(), 0);
        assertEquals(4.5, pacco1.getPrezzoScontato(), 0);

        pacco1.add(prodotto2);

        assertEquals(2, pacco1.size());
        assertEquals(30, pacco1.getPrezzo(), 0);
        assertEquals(12.5, pacco1.getPrezzoScontato(), 0);

        pacco2.add(pacco1);
        pacco2.add(prodotto3);

        assertEquals(2, pacco2.size());
        assertEquals(60, pacco2.getPrezzo(), 0);
        assertEquals(31.5, pacco2.getPrezzoScontato(), 0);

    }

}
