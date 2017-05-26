/**
 * Java. TeRoundButtonsCalculator
 *
 * @author Ternyuk Igor
 * @version 1.0 dated May 25, 2017
 */
package teroundbuttonscalculator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TeRoundButtonsCalculator {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            View view = new View();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TeRoundButtonsCalculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
