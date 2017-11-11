package fallhcak.Control;

import fallhcak.UI.Tile;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * Handler for Mouse Click interactions
 * Delegates click event based on Tile context (word, special bracket)
 * 
 * @author William Young
 */
public class TileClickHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        EventType e = event.getEventType();
        if (e == MouseEvent.MOUSE_CLICKED) {
            // TODO - Proper click behavior
            Tile temp = (Tile) event.getSource();
            System.out.printf(
                "You have clicked on Tile at position (%s,%s) with value '%s'\n",
                temp.getXCoord(), temp.getYCoord(), temp.getBaseChar()
            );
        }
    }
}