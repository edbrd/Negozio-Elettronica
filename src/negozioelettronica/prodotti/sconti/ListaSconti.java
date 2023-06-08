/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.sconti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Bardeli Edmond
 */
public class ListaSconti extends ScontoAstratto implements List<Sconto>
{
    private List<Sconto> listaSconto = new ArrayList<>();

    public ListaSconti()
    {
        super(null);
    }
    
    public ListaSconti(String nomeSconto)
    {
        super(nomeSconto);
    }
            


    @Override
    public int size()
    {
        return listaSconto.size();
    }

    @Override
    public boolean isEmpty()
    {
        return listaSconto.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return listaSconto.contains(o);
    }

    @Override
    public Iterator<Sconto> iterator()
    {
        return listaSconto.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return listaSconto.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts)
    {
        return listaSconto.toArray(ts);
    }

    @Override
    public boolean add(Sconto e)
    {
        return listaSconto.add(e);
    }

    @Override
    public boolean remove(Object o)
    {
        return listaSconto.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> clctn)
    {
        return listaSconto.containsAll(clctn);
    }

    @Override
    public boolean addAll(Collection<? extends Sconto> clctn)
    {
        return listaSconto.addAll(0, clctn);
    }

    @Override
    public boolean addAll(int i, Collection<? extends Sconto> clctn)
    {
        return listaSconto.addAll(i, clctn);
    }

    @Override
    public boolean removeAll(Collection<?> clctn)
    {
        return listaSconto.removeAll(clctn);
    }

    @Override
    public boolean retainAll(Collection<?> clctn)
    {
        return listaSconto.retainAll(clctn);
    }

    @Override
    public void clear()
    {
        listaSconto.clear();
    }

    @Override
    public Sconto get(int i)
    {
        return listaSconto.get(i);
    }

    @Override
    public Sconto set(int i, Sconto e)
    {
        return listaSconto.set(i, e);
    }

    @Override
    public void add(int i, Sconto e)
    {
        listaSconto.add(i, e);
    }

    @Override
    public Sconto remove(int i)
    {
        return listaSconto.remove(i);
    }

    @Override
    public int indexOf(Object o)
    {
        return listaSconto.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o)
    {
        return listaSconto.lastIndexOf(o);
    }

    @Override
    public ListIterator<Sconto> listIterator()
    {
        return listaSconto.listIterator();
    }

    @Override
    public ListIterator<Sconto> listIterator(int i)
    {
        return listaSconto.listIterator(i);
    }

    @Override
    public List<Sconto> subList(int i, int i1)
    {
        return listaSconto.subList(i1, i1);
    }

    @Override
    public float scontaPrezzo(float prezzo)
    {
        for(Sconto sconto: this)
        {
            prezzo = sconto.scontaPrezzo(prezzo);
        }
        if(prezzo<0){
            return 0;
        }
        return prezzo;
    }
    
    @Override
    public String getInfo()
    {
        String info = super.getInfo();
        Sconto sconto;
        for(int c=0;c<this.size();c++)
        {
            sconto=get(c);
            if(c>=1)
            {
                info+=" , ";
            }
            info += " " + sconto.getInfo();
        }
        return info;
    }
}
