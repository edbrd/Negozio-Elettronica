/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.gui;

import java.awt.Dialog;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JDialog;

/**
 * U
 * @author Bardeli Edmond
 */
public class ListenedDialog extends JDialog
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<DialogListenInteface> dialogListeners=new LinkedHashSet<>();

    
        public ListenedDialog() {
    }

    public ListenedDialog(Frame owner) {
        super(owner);
    }

    public ListenedDialog(Frame owner, boolean modal) {
        super(owner, modal);
    }

    public ListenedDialog(Frame owner, String title) {
        super(owner, title);
    }

    public ListenedDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public ListenedDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public ListenedDialog(Dialog owner) {
        super(owner);
    }

    public ListenedDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    public ListenedDialog(Dialog owner, String title) {
        super(owner, title);
    }

    public ListenedDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public ListenedDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public ListenedDialog(Window owner) {
        super(owner);
    }

    public ListenedDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    public ListenedDialog(Window owner, String title) {
        super(owner, title);
    }

    public ListenedDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    public ListenedDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }
    
    
    /**
     * aggiunge un listener al dialogo,
     * <br>una istanza non puo' essere aggiunta piu' volte (verra' ignorata)
     * <br>se il listener e' null verra' ignorato
     * @param listener
     * @return true se il listener e' stato aggiunto, false se non e' stato aggiunto
     */
    public boolean addDialogListener(DialogListenInteface listener)
    {
        if(listener!=null)
        {
            return dialogListeners.add(listener);
        }
        return false;
    }
    
    /**
     * rimuove il listener dal dialog
     * @param listener
     * @return true se il listener e' stato trovato e rimosso, false se non e' stato rimosso
     */
    public boolean removeDialogListener(DialogListenInteface listener)       
    {
        return dialogListeners.remove(listener);
    }
    
    /**
     * 
     */
    protected void fireAllDialogListener()
    {
        for(DialogListenInteface listener:dialogListeners)
        {
            listener.onDialogFinish(this);
        }
    }
    
}
