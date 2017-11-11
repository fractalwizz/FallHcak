package fallhcak.Control;

import fallhcak.FallHcak;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Handler for Keyboard Type interactions
 * Delegates 'q' for quit, 'r' for restart
 * 
 * @author William Young
 */
public class KeyTypeHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCharacter()) {
            case "r":
            case "R":
                FallHcak.startProcess();
                break;
            case "q":
            case "Q":
                System.exit(0);
        }
    }   
}