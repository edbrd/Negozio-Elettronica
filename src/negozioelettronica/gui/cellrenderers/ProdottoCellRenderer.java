/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.gui.cellrenderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import negozioelettronica.prodotti.ProdottoInterface;

/**
 *
 * @author Bardeli Edmond
 */
public class ProdottoCellRenderer extends JLabel implements ListCellRenderer<ProdottoInterface>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color SELECTED_BG_COLOR=new Color(1,4,235);

    public ProdottoCellRenderer()
    {
        this.setBackground(SELECTED_BG_COLOR);
    }
    
    
    @Override
    public Component getListCellRendererComponent(JList jlist, ProdottoInterface prodotto, int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(prodotto.getNome());
        this.setOpaque(isSelected);
        return this;
    }
    
}
