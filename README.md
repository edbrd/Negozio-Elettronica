# Negozio-Elettronica
Simulazione di un negozio di dispositivi e oggetti elettrnoici.

## SPECIFICHE
Il progetto propone una applicazione che simula un negozio di elettronica online tramite un interfaccia grafica. 
L’interfaccia si presenta inizialmente come una finestra divisa fondamentalmente in tre componenti principali.
La prima componente è il catalogo: al suo interno sono contenuti tutti i prodotti acquistabili del
negozio. Ogni prodotto può essere costituito da un singolo articolo o da più prodotti raggruppati in
un pacco.
Ogni prodotto nel catalogo può essere selezionato dall'utente. La selezione permette di vedere i
dettagli del prodotto nella componente centrale dell'interfaccia, informando l'utente dei suoi dettagli
ovvero le specifiche, i prezzi e gli sconti del prodotto.
Nella seconda componente una volta consultate le specifiche di un prodotto acquistabile dal
catalogo, l’utente può decidere di aggiungerlo al suo carrello.
Nella terza componente, il carrello, l’utente può rimuovere i prodotti che non desidera più.
Oltre a far vedere i contenuti dei prodotti che l'utente è intenzionato a comprare, il carrello
visualizza anche la spesa totale, tenendo conto della tessera dell’utente che potrebbe avere degli
ulteriori sconti.
Il negozio online prevede la presenza di sconti, sia intrinsechi al singolo prodotto o ai pacchi di
prodotti, sia in base all'utente che sta utilizzando l'interfaccia.
L'utente può infatti autentificarsi tramite una apposita finestra login.
L’utente concludendo la spesa viene dunque rimandato ad una finestra che mostra una fattura dei
suoi acquisti. Se questa viene confermata, l'utente viene rimandato alla finestra di partenza per poter
effettuare una nuova spesa, altrimenti ha la possibilità di tornare indietro e modificare i suoi
acquisti.

## COMMENTI E MOTIVAZIONI
I prodotti del catalogo posso essere singoli articoli, oppure composizione di più prodotti, che
a loro volta possono essere costituiti da altre composizioni di prodotti. Ho deciso di realizzare
i prodotti utilizzando il design pattern Composite. In questo modo, l'utente interagisce con
l'interfaccia “ProdottoInterface”, senza distinguere se si tratta di un oggetto della classe “Prodotto”
o della classe “Pacco”.
Lo stesso discorso si ripete per le specifiche, che possono essere una specifica base, oppure una lista
di specifiche. Il client interagisce quindi con l'interfaccia “Specifiche” senza distinguere se si tratta
di un oggetto della classe “SpecificaBase” o della classe “ListaSpecifiche”.
Il catalogo del nostro negozio è una classe di cui deve esistere un solo esemplare e solo con questo i
client devono interagire. Per questa classe ho quindi deciso di sfruttare il design pattern
Singleton: è una classe con un costruttore private, una unica istanza private statica, alla quale si può
accedere solo tramite il metodo statico “getIstance()”.
Ho deciso di interpretare lo sconto di un prodotto (o di un pacco) come uno dei suoi aspetti
principali (un prodotto senza sconto può essere interpretato come uno prodotto con sconto uguale a
zero).
Con il design pattern Strategy ho deciso di attribuire un campo “sconto” di tipo
“ScontoInterface”alla classe astratta “ProdottoAstratto”. In questo modo, quando quest'ultima ha
necessità di ottenere il valore del proprio sconto, delega l'operazione di tale calcolo alle classi che
implementano l'interfaccia “Sconto”.
Uso lo stesso design per attribuire le specifiche ad un prodotto singolo (ma non ad un Pacco, che
non ha specifiche intrinseche) aggiungendo un attributo “specifiche” di tipo “Specifica” alla classe
“Prodotto”.
Quando un prodotto viene selezionato dal catalogo, voglio che di questo ci compaia la sua
descrizione completa nell'apposita finestra. Per evitare a dare troppa responsabilità ad una singola
classe, ho deciso di portare fuori il compito di gestire la stampa di queste informazioni tramite
il design pattern Visitor. La classe “ProdottoInterface” accetta quindi un visitor “ProdottoVisitor”,
ed a seconda che il primo sia un oggetto “Prodotto” o “Pacco”, richiamerà il metodo adeguato per
stampare le informazioni.
Stessa logica viene applicata all'interfaccia “Specifica”, dove l'operazione di gestire la stampa delle
specifiche viene delegato al visitor “VisitorSpecifiche”.
L'accesso di uno specifico utente tramite login, così come la presentazione della fattura della spesa,
vengono visualizzate come finestre distinte dall'interfaccia iniziale: il login può essere gestito in
modo più sofisticato senza dover mettere mani su tutta l'applicazione; la creazione della fattura deve
essere effettuata alla conclusione della spesa , dove non e' più necessario interagire con l'interfaccia
principale.
La conferma di entrambe però portano alcune modifiche all'interfaccia principale: dopo il login
tengo conto dell'utente che sta effettuando la spesa nell'interfaccia principale, nella quale
applicho determinati sconti all'intero carrello; confermata la fattura si vuole ritornare
all'interfaccia principale per effettuare una nuova spesa, avendo opportunamente rimosso tutti i
prodotti presenti nel carrello precedente.
Le classi MessageDialog e FatturaDialog, siccome estendono la classe ListenedDialog dove
ho implementato un pattern di tipo Observer, hanno la possibilità di notificare gli osservatori
quando l’utente ha preso visione del messaggio oppure quando è d’accordo a confermare la fattura.

## TESTS
Per verificare che il programma non faccia errori nel calcolare i prezzi e gli sconti, ho
utilizzato il framework JUnit testare le seguenti classi Pacco, Prodotto, ScontoFisso,
ScontoPercentuale, ListaSconti.

## AVVIO APPLICAZIONE
1) All’apertura del programma vene mostrata l’interfaccia utente.
2) L’utente può consultare i dettagli dei vari prodotti e pacchi con i loro prezzi, sconti e specifiche .
3) Fare il login attraverso una finestra dove potrà impostare: il suo Titolo, Username e il tipo di
carta che possiede (Nel totale dei dettagli il prezzo non è già scontato in base alla tessera
dell’utente).
4) Aggiungere o rimuovere prodotti dal carrello, nel totale troverà il prezzo già scontato in
base alla tessera inserita.
5) Una volta scelti i prodotti da acquistare l’utente potrà proseguire con il pagamento
premendo appunto il bottone “Pagamento”, e in seguito gli verrà mostrata la fattura, premendo
ulteriormente ok l’utente potrà fare un nuovo acquisto.
