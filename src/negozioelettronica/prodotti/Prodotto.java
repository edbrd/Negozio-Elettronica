/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti;

import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoAstratto;
import negozioelettronica.prodotti.specifiche.Specifica;
import negozioelettronica.visitors.ProdottoVisitor;

/**
 *
 * @author Bardeli Edmond
 */
public class Prodotto extends ProdottoAstratto
{

    private float prezzo;  //il prezzo del prodotto

    private Specifica specifica = null;

    
    
    
    
    
    /**
     * inizializza un nuovo prodotto senza sconto, solo con il nome e il prezzo
     *
     * @param nome
     * @param prezzo
     */
    public Prodotto(String nome, float prezzo)
    {
        super(nome, null);
        setPrezzo(prezzo);
    }

    /**
     * inizializza un nuovo prodotto con il nome, il prezzo e lo sconto
     *
     * @param nome
     * @param prezzo
     * @param sconto
     */
    public Prodotto(String nome, float prezzo, Sconto sconto)
    {
        super(nome, sconto);
        setPrezzo(prezzo);
    }

    @Override
    public float getPrezzo()
    {
        return prezzo;
    }

    @Override
    public void setPrezzo(float prezzo)
    {
        if (prezzo < PREZZO_MINIMO)
        {
            throw new IllegalArgumentException("Il prezzo non puo' essere minore di 0");
        }
        this.prezzo = prezzo;
    }

    @Override
    public String getInfo()
    {
        String info = "Prodotto " + getNome() + " : prezzo: " + PREZZO_FORMAT.format(getPrezzo()) + " ; sconto: ";
        Sconto sconto = getSconto();
        if (sconto != null)
        {
            info += sconto.getInfo();
        } else
        {
            info += " - ";
        }
        return info;
    }

    @Override
    public String toString()
    {
        return "Prodotto{" + "prezzo=" + prezzo + ", sconto=" + getSconto() + ", nome=" + getNome() + '}';
    }

    @Override
    public boolean equals(Object o)
    {
        boolean equals = false;

        if (o instanceof Prodotto)
        {
            equals = super.equals(o);

            Prodotto altro = (Prodotto) o;

            equals = equals && altro.getPrezzo() == this.getPrezzo();
        } else if (o instanceof ProdottoAstratto)
        {
            equals = super.equals(o);
        }

        return equals;
    }

    /**
     * accetta un visitor per il prodotto
     *
     * @param visitor
     */
    @Override
    public void accept(ProdottoVisitor visitor)
    {
        visitor.visit(this);
    }

    /**
     *
     * @return la specifica del prodotto
     */
    public Specifica getSpecifica()
    {
        return specifica;
    }

    /**
     * setta la specifica del prodotto
     *
     * @param specifica
     */
    public void setSpecifica(Specifica specifica)
    {
        this.specifica = specifica;
    }

}
