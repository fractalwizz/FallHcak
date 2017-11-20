package fallhcak.Data;

import fallhcak.UI.Tile;
import fallhcak.UI.UI;
import java.util.ArrayList;
import java.util.List;

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
    public Tile[] getTileArray() {
        Tile[] list = new Tile[word.length()];
        if (coord == null) { return null; }
        
        int x = coord.getKey();
        int y = coord.getValue();
        
        for (int i = 0; i < word.length(); i++) {
            if (x > 38) {
                x = 27;
                y++;
            } else if (x == 19) {
                x = 7;
                y++;
            }
            
            list[i] = UI.tileAt(x, y);
            x++;
        }
        
        return list;
    }
    
    /**
     * Gets a List of Integer Pairs for Word Tile coordinates
     * Extracts the information from getTileArray
     * 
     * @return a List of Coordinate Pairs composing a word
     */
    public List<Pair<Integer, Integer>> getCoordList() {
        Tile[] arr = this.getTileArray();
        if (arr == null) { return new ArrayList<>(); }
        
        List<Pair<Integer, Integer>> list = new ArrayList<>(arr.length);
        
        for (Tile tile : arr) {
            list.add(new Pair<>(tile.getXCoord(), tile.getYCoord()));
        }
        
        return list;
    }
}