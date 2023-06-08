/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti;

import java.text.DecimalFormat;
import java.util.List;
import negozioelettronica.prodotti.sconti.Sconto;

/**
 *
 * @author Bardeli Edmond
 */
public abstract class ProdottoAstratto implements ProdottoInterface
{
	public static final DecimalFormat PREZZO_FORMAT=new DecimalFormat(" Ä ");

    public static final float PREZZO_MINIMO = 0;

    private Sconto sconto;
    private String nome;

    public ProdottoAstratto(String nome, Sconto sconto)
    {
    	this.sconto = sconto;
        setNome(nome);
    }

    /**
     *
     * @return il prezzo scontato, altrimenti se non ha uno sconto ritornera' il
     * prezzo intero
     */
    @Override
    public float getPrezzoScontato()
    {
        float prezzo = getPrezzo();
        if (sconto != null)
        {
            prezzo = sconto.scontaPrezzo(prezzo);
        }
        return prezzo;
    }

    @Override
    public Sconto getSconto()
    {
        return sconto;
    }

    /**
     * setta lo sconto al prodotto
     *
     * @param sconto lo sconto del prodotto, anche null
     */
    public void setSconto(Sconto sconto)
    {
        this.sconto = sconto;
    }

    /**
     *
     * @return il nome del prodotto
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * setta il nome
     *
     * @param nome il nomre del prodotto
     */
    @Override
    public void setNome(String nome)
    {
        if (nome == null)
        {
            throw new IllegalArgumentException("Il nome non pu√≤ essere null");
        }
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof ProdottoAstratto))
        {
            return false;
        }
        ProdottoAstratto other = (ProdottoAstratto) obj;
        if (!other.getNome().equals(this.getNome()))
        {
            return false;
        }

        return this.sconto != null ? this.sconto.equals(other.sconto) : other.sconto == null;
    }


}
