/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.visitors;

import negozioelettronica.prodotti.Pacco;
import negozioelettronica.prodotti.Prodotto;
import negozioelettronica.prodotti.ProdottoInterface;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoAstratto;
import negozioelettronica.prodotti.specifiche.Specifica;
import negozioelettronica.prodotti.specifiche.VisitorSpecificheBase;

/**
 * visita un albero di prodotti tenendo traccia al suo interno (attraverso una stringa ) delle informzioni
 * <br> racolte dai prodotti visitati
 *
 *
 * @author Bardeli Edmond
 */
public class ProdottiInfoVisitor implements ProdottoVisitor
{

    private static String SPAZIO_DAL_BORDO = "      ";
    private int numeroPacchi;
    private String infoProdotti;
    private boolean includiSpecifiche = true;

    private VisitorSpecificheBase visitorSpecificheBase = new VisitorSpecificheBase("");

    public ProdottiInfoVisitor()
    {
        this(true);
    }

    public ProdottiInfoVisitor(boolean includiSpecifiche)
    {
        clear();
        this.includiSpecifiche = includiSpecifiche;
    }

    /**
     * visita un prodotto racogliendo le informazioni contenute al suo interno
     *
     * @param prodotto
     */
    @Override
    public void visit(Prodotto prodotto)
    {
        if (prodotto != null)
        {
            aggiungiSpazioDalBordo();
            infoProdotti += prodotto.getInfo();

            if (includiSpecifiche)
            {
                Specifica specifica = prodotto.getSpecifica();
                if (specifica != null)
                {
                    visitorSpecificheBase.clear();
                    visitorSpecificheBase.setSpazio(calcSpazioDalBordo());
                    specifica.accept(visitorSpecificheBase);
                    infoProdotti += visitorSpecificheBase.getSpecifiche();

                }
            }
            infoProdotti += "\n";

        }
    }

    /**
     * visita tutti i singoli prodotti del pacco 
     * @see {@link #visit(Prodotto)}
     *
     * @param pacco il pacco da visitare, se null verra' ignorato
     */
    @Override
    public void visit(Pacco pacco)
    {
        if (pacco != null)
        {
            aggiungiSpazioDalBordo();
            numeroPacchi++;

            infoProdotti += "Pacco " + pacco.getNome() + " ; sconto: ";
            Sconto sconto = pacco.getSconto();
            if (sconto != null)
            {
                infoProdotti += sconto.getInfo();
            } else
            {
                infoProdotti += "-";
            }
            infoProdotti += ":\n";
            for (ProdottoInterface prodotto : pacco)
            {
                prodotto.accept(this);
            }
            numeroPacchi--;
        }
    }

    /**
     *
     * @return una stringa con i detagli dei prodotti visitati
     */
    public String getInfoProdotti()
    {
        return infoProdotti;
    }

    /**
     * pulisce lo stato del visitor
     */
    public void clear()
    {
        infoProdotti = "";
        numeroPacchi = 0;
    }

    /**
     * per ogni paco visitato aggiungera' uno spazio dal bordo
     * <br>in modo da avere una buona visuale dei prodotti al suo interno,
     * <br>se un pacco ha un'altro pacco al suo interno verra' aggiunto
     * <br>un doppio spazio
     */
    private void aggiungiSpazioDalBordo()
    {
        infoProdotti += calcSpazioDalBordo();
    }

    /**
     * calcola lo spazio dal bordo in base ai pacchi che sta visitando
     *
     * @return
     */
    private String calcSpazioDalBordo()
    {
        String spazio = "";

        for (int i = 0; i < numeroPacchi; i++)
        {
            spazio += SPAZIO_DAL_BORDO;
        }

        return spazio;
    }

}
