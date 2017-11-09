package fallhcak.UI;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Handles all operations regarding the JavaFX window and its Contents
 * Exclusively operates and updates Tile objects
 * @author William Young
 */
public class UI {
    private static boolean TRIP_ERROR = false;
    public static int mWidth = 795;
    public static int mHeight = 550; // 330
    public static int mSizeX = 53;
    public static int mSizeY = 22;
    
    private static final Color BACK = Color.BLACK;
    private static final Color FORE = Color.rgb(42, 254, 141);
    
    private static Group mGroup;
    private static Tile[][] mTile;
    private static Scene mScene;
    
    /**
     * Handler for Mouse movement interactions.
     * Highlights currently hovered over Tile 
     */
    private static final EventHandler<MouseEvent> mMouseEvent = (MouseEvent event) -> {
        EventType e = event.getEventType();
        if (e == MouseEvent.MOUSE_ENTERED || e == MouseEvent.MOUSE_EXITED) {
            Tile temp = (Tile) event.getSource();
            flipFill(temp);
        }
    };
    
    /**
     * Helper Method to flip Color Fills of Tile object(s)
     * @param tiles an array of zero or more Tile objects
     */
    private static void flipFill(Tile... tiles) {
        if (tiles == null) { return; }
        
        for (Tile tile : tiles) {
            Color tempR = tile.getRectFill();
            Color tempL = tile.getLabelFill();
            
            tile.setRectFill(tempL);
            tile.setLabelFill(tempR);
        }
    }
    
    /**
     * Handler for Mouse Click interactions
     * Delegates click event based on Tile context (word, special bracket)
     */
    private static final EventHandler<MouseEvent> mClickEvent = (MouseEvent event) -> {
        EventType e = event.getEventType();
        if (e == MouseEvent.MOUSE_CLICKED) {
            Tile temp = (Tile) event.getSource();
            System.out.printf("You have clicked on Tile at position (%s,%s) with value '%s'\n", temp.getXCoord(), temp.getYCoord(), temp.getBaseChar());
        }
    };
    
    /**
     * Sets up the primary JavaFX window.
     * Populates the Scene with the 2d Grid of Tile objects
     * @return the JavaFX scene populated with Tiles
     * @see Scene
     */
    public static Scene initialize() {
        mGroup = new Group();
        
        mScene = new Scene(mGroup, mWidth, mHeight, Color.WHITE);
        mTile = new Tile[mSizeX][mSizeY];
        
        double w = mWidth / mSizeX;
        double y = mHeight / mSizeY;
        
        for (int i = 0; i < mSizeX; i++) {
            for (int j = 0; j < mSizeY; j++) {
                double sx = w * i;
                double sy = y * j;
                
                Tile tile = new Tile(
                    i,                      // tile x-coordinate
                    j,                      // tile y-coordinate
                    sx,                     // tile start corner x
                    sy,                     // tile start corner y 
                    w,                      // tile width
                    y,                      // tile height
                    BACK,            // Rectangle Fill
                    FORE,            // Label Fill
//                    getRandColor(),
//                    ''
                    ' '                     // Starting Char
                );
                
                tile.setOnMouseEntered(mMouseEvent);
                tile.setOnMouseExited(mMouseEvent);
                tile.setOnMouseClicked(mClickEvent);
                
                mTile[i][j] = tile;
            }
        }
        
        for (Tile[] mRect1 : mTile) { mGroup.getChildren().addAll(mRect1); }
        
        if (/*possible error error*/ TRIP_ERROR) {
            // TODO - return error-based scene
            return null;
        }
        
        return mScene;
    }
    
    /**
     * Helper Method to get Tile at specified coordinates
     * 
     * @param x X-coordinate position in Tile grid
     * @param y Y-coordinate position in Tile grid
     * @return the Tile object at position (x, y)
     */
    public static Tile tileAt(int x, int y) { return mTile[x][y]; }
    
    /**
     * Updates Tile Grid with specified String.
     * Supports wrapping of String around specified wrap Y-coordinate
     * 
     * @param x      starting X-coordinate position in Tile grid
     * @param y      starting Y-coordinate position in Tile grid
     * @param string specified String to be printed
     * @param wrap   X-coordinate position to resume printing on next line
     */
    public static void putString(int x, int y, String string, int wrap) {
        if (x < 0 || x >= mSizeX) { return; }
        if (y < 0 || y >= mSizeY) { return; }
        if (string.isEmpty()) { return; }
        if (wrap >= mSizeX) { wrap = 0; }
        
        for (char c : string.toCharArray()) {
            if (x >= mSizeX) {
                x = wrap;
                y++;
            }
            if (y >= mSizeY) { return; }
            
            mTile[x][y].setBaseChar(c);
            
            x++;
        }
    }
    
    /**
     * Helper Method to clear Grid upon Terminal restart
     */
    public static void clear() {
        for (Tile[] mRect : mTile) {
            for (Tile tile : mRect) { tile.setBaseChar(' '); }
        }
    }
}