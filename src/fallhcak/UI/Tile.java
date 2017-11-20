package fallhcak.UI;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Tile object that populates JavaFX window.
 * The Tile object stacks a Rectangle and a Label into one grid position
 * @author William Young
 */
public class Tile extends StackPane {
    private Rectangle mRect = new Rectangle();
    private Label mLabel = new Label();
    private char base = ' ';
    private Color rectFill;
    private Color labelFill;
    private int x;
    private int y;
    private boolean isInWord;
    
    /**
     * Tile Constructor.
     * Initializes StackPane children and associated properties
     * 
     * @param coordX   X-coordinate position in Tile grid
     * @param coordY   Y-coordinate position in Tile grid
     * @param x        actual x-axis position in JavaFX window
     * @param y        actual y-axis position in JavaFX window
     * @param width    width of tile object
     * @param height   height of tile object
     * @param fill     color of background rectangle
     * @param textFill color of Label Text
     * @param base     character to be displayed in the Tile Label
     */
    public Tile(int coordX, int coordY, double x, double y, double width, double height, Color fill, Color textFill, char base) {
        this.rectFill = fill;
        this.labelFill = textFill;
        
        mRect = new Rectangle(x, y, width, height);
        mRect.setFill(fill);
        
        mLabel.setTextFill(textFill);
        mLabel.setFont(Font.font(16));
        
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.x = coordX;
        this.y = coordY;
        
        // Character.MIN_VALUE for no update (?)
        if (base != 0) {
            this.base = base;
            updateLabel();
        }
        
        this.getChildren().add(mRect);
        this.getChildren().add(mLabel);
        
        this.isInWord = false;
    }
    
    /**
     * Updates Tile Label with new character
     */
    private void updateLabel() { mLabel.setText(String.valueOf(base)); }
    
    public Rectangle getRectangle() { return mRect; }
    public void setRectangle(Rectangle r) { this.mRect = r; }
    
    public Label getLabel() { return mLabel; }
    public void setLabel(Label label) { this.mLabel = label; }
    
    public char getBaseChar() { return base; }
    public void setBaseChar(char base) {
        this.base = base;
        updateLabel();
    }
    
    public Color getRectFill() { return rectFill; }
    public void setRectFill(Color fill) {
        this.rectFill = fill;
        mRect.setFill(rectFill);
    }
    
    public Color getLabelFill() { return labelFill; }
    public void setLabelFill(Color fill) {
        this.labelFill = fill;
        mLabel.setTextFill(labelFill);
    }
    
    public int getXCoord() { return x; }
    public void setXCoord(int x) { this.x = x; }
    
    public int getYCoord() { return y; }
    public void setYCoord(int y) { this.y = y; }
    
    public boolean isInWord() { return isInWord; }
    public void setInWord(boolean inWord) { this.isInWord = inWord; }
    public void invertWord() { isInWord = !isInWord; }
}