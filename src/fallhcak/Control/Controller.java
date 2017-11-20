package fallhcak.Control;

import fallhcak.Data.DataHelper;
import fallhcak.UI.Tile;
import fallhcak.UI.UI;

import javafx.scene.input.KeyEvent;

/**
 * Distributes event handlers across the UI elements
 * 
 * @author William Young
 */
public class Controller {
    public static void initialize(DataHelper helper) {
        UI.getScene().addEventFilter(KeyEvent.KEY_TYPED, new KeyTypeHandler());
        
        for (Tile[] tiles : UI.getTiles()) {
            for (Tile tile : tiles) {
                EnterExitHandler handle = new EnterExitHandler(helper);
                tile.setOnMouseEntered(handle);
                tile.setOnMouseExited(handle);
                tile.setOnMouseClicked(new TileClickHandler(helper));
            }
        }
    }
}