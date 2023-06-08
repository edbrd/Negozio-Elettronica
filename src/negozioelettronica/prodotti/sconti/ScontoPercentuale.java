/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.sconti;

import java.text.DecimalFormat;

/**
 *
 * @author Bardeli Edmond
 */
public class ScontoPercentuale extends ScontoAstratto
{

    private static final DecimalFormat SCONTO_FORMATTER_PERCENT = new DecimalFormat("€ ");

    private float sconto; //uno sconto da 0 a 1

    /**
     * inizializza un nuovo sconto con il valore 'sconto, il nome verra' messo a
     * null
     *
     * @param sconto lo sconto in percentuale da 0 a 1 compresi
     */
    public ScontoPercentuale(float sconto)
    {
        super(null);
        setScontoValore(sconto);
    }

    /**
     * inizializza un nuovo sconto con il valore 'sconto
     *
     * @param sconto lo sconto in percentuale da 0 a 1 compresi
     * @param name il nomde dello sconto
     */
    public ScontoPercentuale(String name, float sconto)
    {
        super(name);
        setScontoValore(sconto);
    }

    @Override
    public String getInfo()
    {
        String info = super.getInfo();

        float val = this.getScontoValore();

        if (val == 0)
        {
            info += " " + SCONTO_ZERO_STRING;
        } else
        {
            info += " " + SCONTO_FORMATTER_PERCENT.format(val);
        }

        return info;
    }

    /**
     *
     * @return il valore dello sconto, da 0 a 1
     */
    public float getScontoValore()
    {
        return sconto;
    }

    /**
     * setta il valore dello sconto , (da 0 a 1, inclusi)
     *
     * @param sconto
     */
    public void setScontoValore(float sconto)
    {
        if (sconto < 0)
        {
            throw new IllegalArgumentException("Lo sconto non puo' essere minore di 0 (0%)");
        }
        if (sconto > 1)
        {
            throw new IllegalArgumentException("Lo sconto non puo' essere maggiore di 1 (100%)");
        }
        this.sconto = sconto;
    }

    @Override
    public float scontaPrezzo(float prezzo)
    {
        return prezzo - prezzo * sconto;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final ScontoPercentuale other = (ScontoPercentuale) obj;
        return this.sconto == other.sconto;
    }

}
