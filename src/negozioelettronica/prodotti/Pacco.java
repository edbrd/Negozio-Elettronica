/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.visitors.ProdottoVisitor;

/**
 *
 * @author Bardeli Edmond
 */
public class Pacco extends ProdottoAstratto implements PaccoInterface
{

    private List<ProdottoInterface> prodotti = new ArrayList<>();

    public Pacco(String nome, Sconto sconto)
    {
        super(nome, sconto);
    }

    @Override
    public float getPrezzo()
    {
        float prezzo = 0;
        for (ProdottoInterface prodotto : this)
        {
            prezzo += prodotto.getPrezzo();
        }

        return prezzo;
    }

    /**
     * calcola il prezzo scontato dei prodotti, e sul valore ottenuto applica un
     * ulterioro sconto (se c'e')
     *
     * @return il prezzo in base ai prodotti e ai sconti
     */
    @Override
    public float getPrezzoScontato()
    {
        float prezzo = 0;
        for (ProdottoInterface prodotto : this)
        {
            prezzo += prodotto.getPrezzoScontato();
        }

        if (getSconto() != null)
        {
            prezzo = getSconto().scontaPrezzo(prezzo);
        }

        return prezzo;
    }

   

    @Override
    public void setPrezzo(float prezzo)
    {
        throw new UnsupportedOperationException("Non si piu' settare il preozzo a un pacco");
    }

    @Override
    public String getInfo()
    {
        StringBuilder infoBuilder = new StringBuilder();
        infoBuilder.append("Pacco:  Nome:");
        infoBuilder.append(getNome());
        infoBuilder.append(" Sconto: ");
        Sconto sconto=getSconto();
        if(sconto==null)
        {
            infoBuilder.append("-");
        }else
        {
            infoBuilder.append(sconto.getInfo());
        }
        
        infoBuilder.append("\n{\n");

        for (ProdottoInterface prodottoInterface : this)
        {
            infoBuilder.append(prodottoInterface.getInfo());
            infoBuilder.append("\n");
        }
        infoBuilder.append("}");

        return infoBuilder.toString();
    }

    /**
     *
     * @return
     */
    /*@Override
    public List<ProdottoAstratto> getProdotti()
    {
        return prodotti;
    }*/
    @Override
    public boolean equals(Object o)
    {
        boolean equals = false;

        if (o instanceof Pacco)
        {
            equals = super.equals(o);

            Pacco altro = (Pacco) o;

            equals = equals && altro.prodotti.equals(this.prodotti);
        } else if (o instanceof ProdottoAstratto)
        {
            equals = super.equals(o);
        }

        return equals;
    }

    @Override
    public int size()
    {
        return prodotti.size();
    }

    @Override
    public boolean isEmpty()
    {
        return prodotti.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return prodotti.contains(o);
    }

    @Override
    public Iterator<ProdottoInterface> iterator()
    {
        return prodotti.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return prodotti.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts)
    {
        return prodotti.toArray(ts);
    }

    @Override
    public boolean add(ProdottoInterface e)
    {
        return prodotti.add(e);
    }

    @Override
    public boolean remove(Object o)
    {
        return prodotti.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> clctn)
    {
        return prodotti.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends ProdottoInterface> clctn)
    {
        return prodotti.addAll(clctn);
    }

    @Override
    public boolean addAll(int i, Collection<? extends ProdottoInterface> clctn)
    {
        return prodotti.addAll(i, clctn);
    }

    @Override
    public boolean removeAll(Collection<?> clctn)
    {
        return prodotti.removeAll(clctn);
    }

    @Override
    public boolean retainAll(Collection<?> clctn)
    {
        return prodotti.retainAll(clctn);
    }

    @Override
    public void clear()
    {
        prodotti.clear();
    }

    @Override
    public ProdottoInterface get(int i)
    {
        return prodotti.get(i);
    }

    @Override
    public ProdottoInterface set(int i, ProdottoInterface e)
    {
        return prodotti.set(i, e);
    }

    @Override
    public void add(int i, ProdottoInterface e)
    {
        prodotti.add(i, e);
    }

    @Override
    public ProdottoInterface remove(int i)
    {
        return prodotti.remove(i);
    }

    @Override
    public int indexOf(Object o)
    {
        return prodotti.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return prodotti.lastIndexOf(o);
    }

    @Override
    public ListIterator<ProdottoInterface> listIterator()
    {
        return prodotti.listIterator();
    }

    @Override
    public ListIterator<ProdottoInterface> listIterator(int i)
    {
        return prodotti.listIterator(i);
    }

    @Override
    public List<ProdottoInterface> subList(int i, int i1)
    {
        return prodotti.subList(i, i1);
    }

    /**
     * accetta un visitor per il pacco
     *
     * @param visitor
     */
    @Override
    public void accept(ProdottoVisitor visitor)
    {
        visitor.visit(this);
    }

}
