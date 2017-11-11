package fallhcak.Control;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Handler for Keyboard Type interactions
 * Delegates 'Q' for quit, 'R' for restart
 * 
 * @author William Young
 */
public class KeyTypeHandler implements EventHandler<KeyEvent> {
    // TODO - Quit + Restart
    @Override
    public void handle(KeyEvent event) {
        System.out.println("You have typed " + event.getCharacter());
    }   
}