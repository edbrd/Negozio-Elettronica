/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negozioelettronica.statics;




import negozioelettronica.prodotti.Pacco;
import negozioelettronica.prodotti.Prodotto;
import negozioelettronica.prodotti.ProdottoInterface;
import negozioelettronica.prodotti.sconti.ListaSconti;
import negozioelettronica.prodotti.sconti.Sconto;
import negozioelettronica.prodotti.sconti.ScontoFisso;
import negozioelettronica.prodotti.sconti.ScontoPercentuale;
import negozioelettronica.prodotti.specifiche.ListaSpecifiche;
import negozioelettronica.prodotti.specifiche.Specifica;
import negozioelettronica.prodotti.specifiche.SpecificaBase;

/**
 *
 * @author Bardeli Edmond
 */
public class Catalogo extends Pacco
{
    private static final Catalogo catalogo = new Catalogo();

    private Catalogo()
    {
        super("Catalogo statico", null);
        
        ListaSconti listaScontiDellaCasa = new ListaSconti("Sconti della casa");
        Sconto scontoPercentuale = new ScontoPercentuale("sconto festivita'",0.05f);
        Sconto scontoFisso = new ScontoFisso (1.5f);
        listaScontiDellaCasa.add(scontoPercentuale);
        listaScontiDellaCasa.add(scontoFisso);
        
        this.setSconto(listaScontiDellaCasa);
        
        creaProdotti();
    }

    /**
     *
     * @return restituisce l'unica istanza del catalogo
     */
    public static Catalogo getInstance()
    {
        return catalogo;
    }

    /**
     * crea la lista di prodotti disponibile in vendita dal negozio
     */
    private void creaProdotti()
    {
        add(creaMonitorSony());
        add(creaMousePerixx());
        add(creaStampanteCanon());
        add(creaStampantePanasonic());
        add(creaTastieraLogitec());
        add(creaTelefonoApple());
        add(creaTelefonoSamsung());
        add(creaTelevisoreSony());
        add(creaBasePcGamingHp());
        add(creaBasePcLavoroAcer());

        add(creaPaccoPcGaming());
        add(creaPaccoPcLavoro());
        add(creaPaccoCartucceUniversali());
        add(creaCartucciaUniversaleAColori());
        add(creaCartucciaUniversaleNera());

        add(creaPaccoStampantePanasonicConCartucce());

    }

    /**
     *
     * @return crea un pacco contenente una base di un pc gaming, un mouse, una
     * tastiera e un monitor
     */
    private ProdottoInterface creaPaccoPcGaming()
    {
        Sconto scontoPacco = new ScontoPercentuale("gamer",0.1f);
        Pacco pcCompleto = new Pacco("Pc gaming", scontoPacco);

        pcCompleto.add(creaBasePcGamingHp());
        pcCompleto.add(creaMousePerixx());
        pcCompleto.add(creaMonitorSony());
        pcCompleto.add(creaTastieraLogitec());

        return pcCompleto;
    }

    /**
     *
     * @return crea un pacco contenente una base di un pc di lavoro, un mouse,
     * una tastiera e un monitor, una stampante con dentro due cartucce bianca e
     * a colori
     */
    private ProdottoInterface creaPaccoPcLavoro()
    {
        Sconto scontoPacco = new ScontoPercentuale("time is money",0.15f);
        Pacco pcCompleto = new Pacco("Pc lavoro", scontoPacco);

        pcCompleto.add(creaBasePcLavoroAcer());
        pcCompleto.add(creaMousePerixx());
        pcCompleto.add(creaMonitorSony());
        pcCompleto.add(creaTastieraLogitec());
        pcCompleto.add(creaPaccoStampantePanasonicConCartucce());

        return pcCompleto;
    }

    /**
     *
     * @return crea un pacco contenente una stampante con dentro le cartucce
     * (bianco e a colori)
     */
    private ProdottoInterface creaPaccoStampantePanasonicConCartucce()
    {
        Pacco stampante = new Pacco("Stampante panasonic con cartucce", new ScontoPercentuale(0.2f));
        stampante.add(creaStampantePanasonic());
        stampante.add(creaPaccoCartucceUniversali());

        return stampante;
    }

    /**
     *
     * @return crea un telefono samsung
     */
    private ProdottoInterface creaTelefonoSamsung()
    {
        Prodotto telefono = new Prodotto("Telefono Samsung", 250, null);
        ListaSpecifiche specificheTelefono = new ListaSpecifiche();

        Specifica marcaTelefono = new SpecificaBase("Marca", "Samsung");
        Specifica memoriaTelefono = new SpecificaBase("Memoria", "16 gb");
        Specifica schermoTelefono = new SpecificaBase("Schemo", "LCD 5 pollici");
        Specifica pesoTelefono = new SpecificaBase("Meso", "80 grammi");
        Specifica connettivitaTelefono = new SpecificaBase("Connettivita", "LTE");

        specificheTelefono.add(schermoTelefono);
        specificheTelefono.add(pesoTelefono);
        specificheTelefono.add(connettivitaTelefono);

        telefono.setSpecifica(specificheTelefono);

        return telefono;
    }

    /**
     *
     * @return crea un iphone
     */
    private ProdottoInterface creaTelefonoApple()
    {
        Prodotto telefono = new Prodotto("Telefono Apple 6S", 800, null);
        ListaSpecifiche specificheTelefono = new ListaSpecifiche();

        Specifica marcaTelefono = new SpecificaBase("Marca", "Apple");
        Specifica memoriaTelefono = new SpecificaBase("Memoria", "32 gb");
        Specifica schermoTelefono = new SpecificaBase("Schemo", "LCD 6.1 pollici");
        Specifica pesoTelefono = new SpecificaBase("Peso", "120 grammi");
        Specifica connettivitaTelefono = new SpecificaBase("Connettivita", "LTE");

        specificheTelefono.add(marcaTelefono);
        specificheTelefono.add(memoriaTelefono);
        specificheTelefono.add(schermoTelefono);
        specificheTelefono.add(pesoTelefono);
        specificheTelefono.add(connettivitaTelefono);

        telefono.setSpecifica(specificheTelefono);

        return telefono;
    }

    /**
     *
     * @return crea una stampante canon
     */
    private ProdottoInterface creaStampanteCanon()
    {
        Prodotto stampante = new Prodotto("Stampante Canon", 94, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Canon");
        Specifica consumo = new SpecificaBase("consumo", "A-");
        Specifica schermo = new SpecificaBase("schemo", "LCD 3 pollici a colori");
        Specifica peso = new SpecificaBase("peso", "2 chili");
        Specifica connettivita = new SpecificaBase("Connettivita", "Wifi, Bluetoot, USB 3.0");

        specifiche.add(marca);
        specifiche.add(consumo);
        specifiche.add(schermo);
        specifiche.add(peso);
        specifiche.add(connettivita);

        stampante.setSpecifica(specifiche);

        return stampante;
    }

    /**
     *
     * @return crea una stampante panasonic
     */
    private ProdottoInterface creaStampantePanasonic()
    {
        Prodotto stampante = new Prodotto("Stampante Panosinic", 119.99f, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Panasonic");
        Specifica consumo = new SpecificaBase("consumo", "A");
        Specifica schermo = new SpecificaBase("schemo", "LCD 2 pollici");
        Specifica peso = new SpecificaBase("peso", "1.8 chili");
        Specifica connettivita = new SpecificaBase("Connettivita", "Wifi, Bluetoot, USB 2.0");

        specifiche.add(marca);
        specifiche.add(consumo);
        specifiche.add(schermo);
        specifiche.add(peso);
        specifiche.add(connettivita);

        stampante.setSpecifica(specifiche);

        return stampante;
    }

    /**
     *
     * @return un prodotto che rappresenta un pacco di cartucce universali,
     * usabile con tutti i tipi di stampante, contenente una nera e una a colori
     */
    private ProdottoInterface creaPaccoCartucceUniversali()
    {
        Pacco cartucce = new Pacco("Cartucce 1x nero & 1x a colori", new ScontoPercentuale("multicolor",0.05f));

        cartucce.add(creaCartucciaUniversaleNera());
        cartucce.add(creaCartucciaUniversaleAColori());

        return cartucce;
    }

    /**
     *
     * @return una cartuccia universale di colore nero
     */
    private ProdottoInterface creaCartucciaUniversaleNera()
    {
        Prodotto cartucciaNera = new Prodotto("Cartuccia nera", 15, null);
        cartucciaNera.setSpecifica(new SpecificaBase("colore", "nero"));

        return cartucciaNera;
    }

    /**
     *
     * @return una cartuccia universale a colori
     */
    private ProdottoInterface creaCartucciaUniversaleAColori()
    {
        Prodotto cartucciaAColori = new Prodotto("Cartuccia a colori", 21, null);
        cartucciaAColori.setSpecifica(new SpecificaBase("colori", "rosso,blue,giallo"));

        return cartucciaAColori;
    }

    /**
     *
     * @return crea un monitor sony
     */
    private ProdottoInterface creaMonitorSony()
    {
        Prodotto monitor = new Prodotto("Monitor Sony Bravia", 247, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Sony");
        Specifica consumo = new SpecificaBase("consumo", "A-");
        Specifica schermo = new SpecificaBase("schemo", "LCD 15 pollici");
        Specifica peso = new SpecificaBase("peso", "560 grammi");
        Specifica connettivita = new SpecificaBase("Connettivita", "VGA, HDMI");
        Specifica pixel = new SpecificaBase("DPI", "300");

        specifiche.add(marca);
        specifiche.add(consumo);
        specifiche.add(schermo);
        specifiche.add(peso);
        specifiche.add(connettivita);
        specifiche.add(pixel);

        monitor.setSpecifica(specifiche);

        return monitor;
    }

    /**
     *
     * @return crea una tastiera logitec
     */
    private ProdottoInterface creaTastieraLogitec()
    {
        Prodotto tastiera = new Prodotto("Tastiera Logitec K120", 14.5f, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Logitec");
        Specifica peso = new SpecificaBase("peso", "120 grammi");
        Specifica modello = new SpecificaBase("modello", "K120");
        Specifica connettivita = new SpecificaBase("Connettivita", "USB 2.0");

        specifiche.add(marca);
        specifiche.add(modello);
        specifiche.add(peso);
        specifiche.add(connettivita);

        tastiera.setSpecifica(specifiche);

        return tastiera;
    }

    /**
     *
     * @return crea un televisore sony
     */
    private ProdottoInterface creaTelevisoreSony()
    {
        Prodotto tv = new Prodotto("Televisore Sony Bravia", 297.99f, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Sony");
        Specifica consumo = new SpecificaBase("consumo", "A-");
        Specifica schermo = new SpecificaBase("schemo", "LCD 42 pollici");
        Specifica peso = new SpecificaBase("peso", "890 grammi");
        Specifica connettivita = new SpecificaBase("Connettivita", "VGA, HDMI,  VHF/UHF");
        Specifica risoluzione = new SpecificaBase("risoluzione", "720p");

        specifiche.add(marca);
        specifiche.add(consumo);
        specifiche.add(schermo);
        specifiche.add(peso);
        specifiche.add(connettivita);
        specifiche.add(risoluzione);

        tv.setSpecifica(specifiche);

        return tv;
    }

    /**
     *
     * @return crea un mouse perixx
     */
    private ProdottoInterface creaMousePerixx()
    {
        Prodotto mouse = new Prodotto("Mouse Perixx", 30, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Perixx");
        Specifica peso = new SpecificaBase("peso", "80 grammi");
        Specifica modello = new SpecificaBase("modello", "TM 134");
        Specifica connettivita = new SpecificaBase("Connettivita", "USB 2.0");

        specifiche.add(marca);
        specifiche.add(modello);
        specifiche.add(peso);
        specifiche.add(connettivita);

        mouse.setSpecifica(specifiche);

        return mouse;
    }

    /**
     *
     * @return crea una base di un pc gaming
     */
    private ProdottoInterface creaBasePcGamingHp()
    {
        Prodotto pc = new Prodotto("Pc Hp", 450, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Hp");
        Specifica peso = new SpecificaBase("peso", "2 chili");
        Specifica modello = new SpecificaBase("modello", "Pavillion Envy dv6");
        Specifica connettivita = new SpecificaBase("Connettivita", "3x USB 3.0");
        Specifica ram = new SpecificaBase("ram", "4 gb DDR3");
        Specifica cpu = new SpecificaBase("cpu", "I7");

        specifiche.add(marca);
        specifiche.add(modello);
        specifiche.add(peso);
        specifiche.add(connettivita);
        specifiche.add(ram);
        specifiche.add(cpu);

        pc.setSpecifica(specifiche);

        return pc;
    }

    /**
     *
     * @return crea e ritorna un prodotto che rapresenta solo la base del pc di
     * lavoro acer
     */
    private ProdottoInterface creaBasePcLavoroAcer()
    {
        Prodotto pc = new Prodotto("Pc Acer", 450, null);
        ListaSpecifiche specifiche = new ListaSpecifiche();

        Specifica marca = new SpecificaBase("marca", "Acer");
        Specifica peso = new SpecificaBase("peso", "1,5 chili");
        Specifica modello = new SpecificaBase("modello", "XC");
        Specifica connettivita = new SpecificaBase("Connettivita", "3x USB 2.0");
        Specifica ram = new SpecificaBase("ram", "2 gb DDR3");
        Specifica cpu = new SpecificaBase("cpu", "I5");

        specifiche.add(marca);
        specifiche.add(modello);
        specifiche.add(peso);
        specifiche.add(connettivita);
        specifiche.add(ram);
        specifiche.add(cpu);

        pc.setSpecifica(specifiche);

        return pc;
    }
}
