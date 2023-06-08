/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.gui;

import negozioelettronica.gui.cellrenderers.ProdottoCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import negozioelettronica.prodotti.ProdottoAstratto;
import negozioelettronica.prodotti.ProdottoInterface;
import negozioelettronica.statics.Catalogo;
import negozioelettronica.user.User;
import negozioelettronica.utils.Carrello;	
import negozioelettronica.visitors.ProdottiInfoVisitor;

/**
 *
 * @author Bardeli Edmond
 */
public class NegozioClientFrame extends javax.swing.JFrame
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DefaultListModel<ProdottoInterface> carrelloListModel = new DefaultListModel<>();
    
    private Carrello carrello=new Carrello(null);
    
    private LoginFrame loginFrame=new LoginFrame();
    private FatturaDialog fatturaDialog;

    /**
     * Creates new form NegozioFrame
     */
    public NegozioClientFrame()
    {
        initComponents();

        insertCatalogo();

        setupComponents();
    }

    /**
     * inserisce i prodotti del catalogo nella lista del catalogo
     * dell'interfaccia gui
     */
    private void insertCatalogo()
    {
        list_catalogo.setCellRenderer(new ProdottoCellRenderer());

        DefaultListModel<ProdottoInterface> prodottiList = new DefaultListModel<>();

        Catalogo catalogo = Catalogo.getInstance();

        for (ProdottoInterface prodotto : catalogo)
        {
            prodottiList.addElement(prodotto);
        }

        list_catalogo.setModel(prodottiList);
    }

    /**
     * inizializza il carrello vuoto
     */
    private void initCarrello()
    {
        list_carrello.setCellRenderer(new ProdottoCellRenderer());
        list_carrello.setModel(carrelloListModel);
    }

    /**
     * inizializza tutti i listeners dei componenti della gui, per farli
     * interagire fra di loro
     */
    private void setupComponents()
    {
        initCarrello();
        connectListCatalogoConDetagli();
        connectAddToCarrello();
        connectRemoveCarrello();
        setupLoginComponents();
        setupFatturaComponents();
    }

    /**
     * collega gli eventi della lista al panello dei dettagli e al prezzo totale
     * del prodotto, per per aggirnarli ogni volta che il valore selezionato
     * della lista cambia
     */
    private void connectListCatalogoConDetagli()
    {

        list_catalogo.addListSelectionListener(new ListSelectionListener()
        {

            @Override
            public void valueChanged(ListSelectionEvent lse)
            {
                updateValoreProdottoSelezionato();
                updateDetagliProdottoSelezionato();
            }
        });
    }
    
    /**
     * aggiorna il valore del prodotto selezioato dal catalogo
     */
    private void updateValoreProdottoSelezionato()
    {
        ProdottoInterface prodotto=list_catalogo.getSelectedValue();
        if(prodotto!=null)
        {
           float prezzo=prodotto.getPrezzoScontato();
            txt_totale_valore_prodotto.setText(" " + ProdottoAstratto.PREZZO_FORMAT.format(prezzo)); 
        }
        
    }
    
    /**
     * aggirna i detagli del prodotto selezionato dal catalogo
     * @param prodotto 
     */
    private void updateDetagliProdottoSelezionato()
    {
        ProdottoInterface prodotto=list_catalogo.getSelectedValue();
        ProdottiInfoVisitor prodottiInfoVisitor = new ProdottiInfoVisitor();
        prodotto.accept(prodottiInfoVisitor);
        txt_detagli_prodotto.setText(prodottiInfoVisitor.getInfoProdotti());
        txt_detagli_prodotto.setCaretPosition(0);
    }

    /**
     * collega il bottone al carrello
     */
    private void connectAddToCarrello()
    {
        btn_aggiungi_al_carrello.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ProdottoInterface prodotto = list_catalogo.getSelectedValue();
                if (prodotto != null)
                {
                    carrelloListModel.addElement(prodotto);
                    carrello.add(prodotto);
                    updateTotaleCarrello();
                    btn_pagamento.setEnabled(true);
                }
            }
        });
    }

    /**
     * aggiorna il totale del costo del carrello dentro la specifica label
     */
    private void updateTotaleCarrello()
    {
        lbl_valore_totale_carrello.setText(ProdottoAstratto.PREZZO_FORMAT.format(carrello.getPrezzoScontato()));
    }

    /**
     * 
     * collega il buttone remove al carrello, in modo da togliere il prodotto selezionato e aggirnare il totale
     */
    private void connectRemoveCarrello()
    {
        btn_remove_prodotto.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ProdottoInterface prodotto = list_carrello.getSelectedValue();
                if (prodotto != null)
                {
                    carrelloListModel.removeElement(prodotto);
                    carrello.remove(prodotto);
                    updateTotaleCarrello();
                    btn_pagamento.setEnabled(!carrello.isEmpty());
                }
            }
        });

    }
    /**
     * setta i componenti del login
     */
    private void setupLoginComponents()
    {
        btn_user_login.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loginFrame.setLocationRelativeTo(btn_user_login);
                loginFrame.setVisible(true);
            }
        });
        
        loginFrame.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e)
            {}

            @Override
            public void windowClosing(WindowEvent e)
            {}

            @Override
            public void windowClosed(WindowEvent e)
            {}

            @Override
            public void windowIconified(WindowEvent e)
            {}

            @Override
            public void windowDeiconified(WindowEvent e)
            {}

            @Override
            public void windowActivated(WindowEvent e)
            {}

            @Override
            public void windowDeactivated(WindowEvent e)
            {
                carrello.setUser(loginFrame.createUser());
                updateTotaleCarrello();
                aggiornaUtente();
                updateValoreProdottoSelezionato();
            }
        });
        
    }
    
    /**
     * collega i componenti del la fatturazione
     */
    private void setupFatturaComponents()
    {
        btn_pagamento.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                fatturaDialog=new FatturaDialog(NegozioClientFrame.this,carrello);
                fatturaDialog.setLocationRelativeTo(NegozioClientFrame.this);
                fatturaDialog.addDialogListener(new DialogListenInteface() {
                    @Override
                    public void onDialogFinish(JDialog source) 
                    {
                      svuotaCarrello();
                    }
                });
                fatturaDialog.setVisible(true);
            }
        });
    }
    
    /**
     * svuota il carrello dai prodotto
     */
    private void svuotaCarrello()
    {
        carrelloListModel.clear();
        carrello.clear();
        updateTotaleCarrello();
    }
    
    /**
     * aggiorna le informazioni dell'utente contenuto nell carrello
     */
    private void aggiornaUtente()
    {
        User user=carrello.getUser();
        
        if(user==null)
        {
            lbl_tessera.setText("-");
            lbl_utente.setText("-");
        }else
        {
            lbl_tessera.setText(user.getTessera().getInfo());
            lbl_utente.setText(user.getInfoUtente());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_catalogo = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_carrello = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        lbl_valore_totale_carrello = new javax.swing.JLabel();
        btn_pagamento = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btn_aggiungi_al_carrello = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_totale_valore_prodotto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_detagli_prodotto = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        btn_remove_prodotto = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_user_login = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_utente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_tessera = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        //Catalogo
        list_catalogo.setBackground(new java.awt.Color(	96, 144, 170));
        list_catalogo.setBorder(javax.swing.BorderFactory.createTitledBorder("                        Catalogo"));
        list_catalogo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(list_catalogo);

        list_carrello.setBorder(javax.swing.BorderFactory.createTitledBorder("                 Carrello"));
        jScrollPane2.setViewportView(list_carrello);
        jScrollPane2.setBackground(new java.awt.Color(1,1,1));;

        jLabel1.setText("Totale :");

        lbl_valore_totale_carrello.setText("-");

        btn_pagamento.setText("Pagamento");
        btn_pagamento.setToolTipText("");
        btn_pagamento.setEnabled(false);

        // dettagli
        jPanel1.setBackground(new java.awt.Color(	96, 144, 170));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100,100,100)));

        btn_aggiungi_al_carrello.setText("Aggiungi al carrello");

        jLabel3.setText("Dettagli :");

        txt_totale_valore_prodotto.setEditable(false);
        txt_totale_valore_prodotto.setBackground(new java.awt.Color(151 ,151 ,153));
        txt_totale_valore_prodotto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_totale_valore_prodotto.setText("-");

        jLabel2.setText("Totale:");

        txt_detagli_prodotto.setEditable(false);
        txt_detagli_prodotto.setBackground(new java.awt.Color(	151, 151, 153));
        txt_detagli_prodotto.setColumns(20);
        txt_detagli_prodotto.setRows(5);
        jScrollPane3.setViewportView(txt_detagli_prodotto);

        jLabel5.setText("(Senza sconto utente)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_aggiungi_al_carrello))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_totale_valore_prodotto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aggiungi_al_carrello)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totale_valore_prodotto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        btn_remove_prodotto.setText("Rimuovi");

        //login
        jPanel2.setBackground(new java.awt.Color(96, 144, 170));

        btn_user_login.setText("Login");

        jLabel4.setText("Utente");

        lbl_utente.setText("-");

        jLabel7.setText("Tessera");

        lbl_tessera.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_user_login)
                .addGap(96, 144, 170)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_utente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_tessera)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_user_login)
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_utente, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_tessera))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_valore_totale_carrello, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 5, Short.MAX_VALUE)
                                .addComponent(btn_pagamento))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_remove_prodotto)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_valore_totale_carrello)
                            .addComponent(btn_remove_prodotto))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(151, 706, Short.MAX_VALUE)
                                .addComponent(btn_pagamento))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aggiungi_al_carrello;
    private javax.swing.JButton btn_pagamento;
    private javax.swing.JButton btn_remove_prodotto;
    private javax.swing.JButton btn_user_login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_tessera;
    private javax.swing.JLabel lbl_utente;
    private javax.swing.JLabel lbl_valore_totale_carrello;
    private javax.swing.JList<ProdottoInterface> list_carrello;
    private javax.swing.JList<ProdottoInterface> list_catalogo;
    private javax.swing.JTextArea txt_detagli_prodotto;
    private javax.swing.JTextField txt_totale_valore_prodotto;
    // End of variables declaration//GEN-END:variables
}
