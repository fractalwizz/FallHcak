package fallhcak.Control;

import fallhcak.Data.DataHelper;
import fallhcak.UI.Tile;
import fallhcak.UI.UI;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * Handler for Mouse movement interactions.
 * Highlights currently hovered over Tile
 * 
 * @author William Young
 */
public class EnterExitHandler implements EventHandler<MouseEvent> {
    private DataHelper refHelper;
    
    EnterExitHandler(DataHelper helper) { this.refHelper = helper; }
    
    @Override
    public void handle(MouseEvent event) {
        EventType e = event.getEventType();
        if (e == MouseEvent.MOUSE_ENTERED || e == MouseEvent.MOUSE_EXITED) {
            // TODO - Handle Word + Bracket flipping
            Tile temp = (Tile) event.getSource();
            UI.flipFill(temp);
        }
    }
}