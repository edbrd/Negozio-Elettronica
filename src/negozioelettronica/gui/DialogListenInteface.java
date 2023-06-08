/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.gui;

import javax.swing.JDialog;

/**
 * Una interfacia per osservare gli eventi di un dialogo
 * @author Bardeli Edmond
 */
public interface DialogListenInteface 
{
    /**
     * metodo che viene chiamato quando un dialog si chiude in modo corretto
     * @param source il dialogo che e' stato chiuso
     */
    public void onDialogFinish(JDialog source);
    
}
