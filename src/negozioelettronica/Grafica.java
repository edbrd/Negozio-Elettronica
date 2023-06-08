package negozioelettronica;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Grafica {
/**
 * Permette di cambiare l'interfaccia grafica della nostra applicazione
 * @param look
 */
    public static void setLookNFeel(String look) {
        LookAndFeelInfo lookNfeels[] = UIManager.getInstalledLookAndFeels();
        int i;
        for (i = 0; i < lookNfeels.length; i++) {
            if (look.equalsIgnoreCase(lookNfeels[i].getName())) {
                try {
                    UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[i].getClassName());
                } catch (Exception ex) {
                    try {
                        UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[0].getClassName());
                    } catch (Exception ex1) {
                        System.exit(1);
                    }
                }
                return;
            }
        }
    }
}