/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.prodotti.specifiche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Bardeli Edmond
 */
public class ListaSpecifiche implements Specifica, List<Specifica> {
	private List<Specifica> list_specifiche = new ArrayList<>();

	/**
	 * crea una lista vuota di specifiche
	 */
	public ListaSpecifiche() {
	}

	@Override
	public void accept(VisitorSpecifiche visitor) {
		visitor.visit(this);
	}

	@Override
	public int size() {
		return list_specifiche.size();
	}

	@Override
	public boolean isEmpty() {
		return list_specifiche.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list_specifiche.contains(o);
	}

	@Override
	public Iterator<Specifica> iterator() {
		return list_specifiche.iterator();
	}

	@Override
	public Object[] toArray() {
		return list_specifiche.toArray();
	}

	@Override
	public <T> T[] toArray(T[] ts) {
		return list_specifiche.toArray(ts);
	}

	@Override
	public boolean add(Specifica e) {
		return list_specifiche.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list_specifiche.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> clctn) {
		return list_specifiche.containsAll(clctn);
	}

	@Override
	public boolean addAll(Collection<? extends Specifica> clctn) {
		return list_specifiche.addAll(clctn);
	}

	@Override
	public boolean addAll(int i, Collection<? extends Specifica> clctn) {
		return list_specifiche.addAll(i, clctn);
	}

	@Override
	public boolean removeAll(Collection<?> clctn) {
		return list_specifiche.removeAll(clctn);
	}

	@Override
	public boolean retainAll(Collection<?> clctn) {
		return list_specifiche.retainAll(clctn);
	}

	@Override
	public void clear() {
		list_specifiche.clear();
	}

	@Override
	public Specifica get(int i) {
		return list_specifiche.get(i);
	}

	@Override
	public Specifica set(int i, Specifica e) {
		return list_specifiche.set(i, e);
	}

	@Override
	public void add(int i, Specifica e) {
		list_specifiche.add(i, e);
	}

	@Override
	public Specifica remove(int i) {
		return list_specifiche.remove(i);
	}

	@Override
	public int indexOf(Object o) {
		return list_specifiche.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list_specifiche.lastIndexOf(o);
	}

	@Override
	public ListIterator<Specifica> listIterator() {
		return list_specifiche.listIterator();
	}

	@Override
	public ListIterator<Specifica> listIterator(int i) {
		return list_specifiche.listIterator(i);
	}

	@Override
	public List<Specifica> subList(int i, int i1) {
		return list_specifiche.subList(i, i1);
	}

}
