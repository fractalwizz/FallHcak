package fallhcak.Data;

import fallhcak.UI.Tile;
import fallhcak.UI.UI;

import javafx.util.Pair;

/**
 * Base Data Structure for Mini-game 'passwords'
 * @author William Young
 */
public class Word {
    private String word;
    private Pair<Integer, Integer> coord;
    
    /**
     * Constructor to create new Word
     * Exclusive use for DataSet
     * 
     * @param word  the string which the word represents
     * @param coord a pair of x,y coordinates for starting char
     */
    public Word(String word, Pair<Integer, Integer> coord) {
        this.word = word;
        this.coord = coord;
    }
    
    public String getWord() { return this.word; }
    public void setWord(String word) { this.word = word; }
    
    public Pair<Integer, Integer> getCoords() { return this.coord; }
    public void setCoords(Pair<Integer, Integer> coord) { this.coord = coord; }
    
    /**
     * Gets an array of Tile references to word char tiles
     * 
     * @return a Tile reference array for all word-related tiles
     */
    public Tile[] getCoordList() {
        Tile[] list = new Tile[word.length()];
        int x = coord.getKey();
        int y = coord.getValue();
        
        for (int i = 0; i < word.length(); i++) {
            if (x > 38) {
                x = 27;
                y++;
            } else if (x > 18) {
                x = 7;
                y++;
            }
            
            list[i] = UI.tileAt(x, y);
            x++;
        }
        
        return list;
    }
}