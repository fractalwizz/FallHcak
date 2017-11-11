package fallhcak.Control;

import fallhcak.UI.Tile;
import fallhcak.UI.UI;

import javafx.scene.input.KeyEvent;

/**
 *
 * @author William Young
 */
public class Controller {
    public static void initialize() {
        UI.getScene().addEventFilter(KeyEvent.KEY_TYPED, new KeyTypeHandler());
        
        for (Tile[] tiles : UI.getTiles()) {
            for (Tile tile : tiles) {
                tile.setOnMouseEntered(new EnterExitHandler());
                tile.setOnMouseExited(new EnterExitHandler());
                tile.setOnMouseClicked(new TileClickHandler());
            }
        }
    }
}