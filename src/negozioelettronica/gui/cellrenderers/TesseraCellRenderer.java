/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.gui.cellrenderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import negozioelettronica.prodotti.ProdottoInterface;
import negozioelettronica.user.card.TesseraInterface;

/**
 *
 * @author Bardeli Edmond
 */
public class TesseraCellRenderer  extends JLabel implements ListCellRenderer<TesseraInterface>
{
    private static final Color SELECTED_BG_COLOR=new Color(181,119,245);

    public TesseraCellRenderer()
    {
        this.setBackground(SELECTED_BG_COLOR);
    }
    
    
    @Override
    public Component getListCellRendererComponent(JList jlist, TesseraInterface tessera, int index, boolean isSelected, boolean cellHasFocus)
    {
        this.setText(tessera.getNome());
        this.setOpaque(isSelected);
        return this;
    }
}
