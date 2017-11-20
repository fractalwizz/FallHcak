package fallhcak.UI;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
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
                    ' '                     // Starting Char
                );
                
                mTile[i][j] = tile;
            }
        }
        
        for (Tile[] mRect1 : mTile) { mGroup.getChildren().addAll(mRect1); }
        
        // Show Current Time
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LocalTime time = LocalTime.now();
                String t = time.format(DateTimeFormatter.ofPattern("hh:mm:ss"));
                
                Platform.runLater(() -> { putString(45, 1, t, 45, 53); });
            }
        };
        
        new Timer("").scheduleAtFixedRate(task, 1000, 1000);
        
        if (/*possible error error*/ TRIP_ERROR) {
            // TODO - return error-based scene
            return null;
        }
        
        return mScene;
    }
    
    /**
     * Helper method to expose Scene for modification outside of display
     * 
     * @return the scene from JavaFX window
     */
    public static Scene getScene() { return mScene; }
    
    /**
     * Helper Method to get Tile at specified coordinates
     * 
     * @param x X-coordinate position in Tile grid
     * @param y Y-coordinate position in Tile grid
     * @return the Tile object at position (x, y)
     */
    public static Tile tileAt(int x, int y) { return mTile[x][y]; }
    
    /**
     * Helper method to expose Tile grid for modification
     * 
     * @return the 2d Tile grid displayed on the window
     */
    public static Tile[][] getTiles() { return mTile; }
    
    /**
     * Updates Tile Grid with specified String.
     * Supports wrapping of String around specified wrap Y-coordinate
     * 
     * @param x       starting X-coordinate position in Tile grid
     * @param y       starting Y-coordinate position in Tile grid
     * @param string  specified String to be printed
     * @param wrapX   X-coordinate position to resume printing on next line
     * @param wrapLim X-coordinate position to wrap at
     */
    public static void putString(int x, int y, String string, int wrapX, int wrapLim) {
        if (x < 0 || x >= mSizeX) { return; }
        if (y < 0 || y >= mSizeY) { return; }
        if (string.isEmpty()) { return; }
        if (wrapX >= mSizeX) { wrapX = 0; }
        
        for (char c : string.toCharArray()) {
            if (x > wrapLim) {
                x = wrapX;
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
            for (Tile tile : mRect) {
                tile.setBaseChar(' ');
                tile.setRectFill(BACK);
                tile.setLabelFill(FORE);
            }
        }
    }
    
    /**
     * Helper Method to flip Color Fills of Tile object(s)
     * @param tiles an array of zero or more Tile objects
     */
    public static void flipFill(Tile... tiles) {
        if (tiles == null) { return; }
        
        for (Tile tile : tiles) {
            Color tempR = tile.getRectFill();
            Color tempL = tile.getLabelFill();
            
            tile.setRectFill(tempL);
            tile.setLabelFill(tempR);
        }
    }
}