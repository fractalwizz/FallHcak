package fallhcak.Data;

import fallhcak.Control.Controller;
import fallhcak.UI.UI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.util.Pair;

/**
 *
 * @author William Young
 */
public class DataHelper {
    private int dataLevel;
    private DataSet dataSet;
    
    /**
     * Default Constructor - For initial reference distribution
     */
    public DataHelper() {}
    
    /**
     * Direct Helper initialization
     * @param level the word length requested
     */
    public DataHelper(int level) { this.setLevel(level); }
    
    public int getLevel() { return dataLevel; }

    /**
     * Setup internal data storage for particular word length
     * 
     * @param level the word length requested
     */
    public final void setLevel(int level) {
        clearData();
        
        dataLevel = level;
        dataSet = new DataSet(level);
    }
    
    // TODO - Word Search opposed to by index
    public Word getWord(Pair<Integer, Integer> index) { return dataSet.getWord(index); }

    private void clearData() {
        dataSet = null;
        dataLevel = -1;
    }
    
    /**
     * Internal Storage of Mini-game Data
     */
    private class DataSet {
        List<Word> mData = new ArrayList<>();
        
        DataSet(int level) {
            String file = String.valueOf(level) + ".dat";
            String line;
            Random rand = new Random();
            
            InputStream in = getClass().getResourceAsStream(file);
            try (BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in, "utf-8"))) {
                while ((line = reader.readLine())!= null) {
                    mData.add(new Word(line, null));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            // TODO - SubList length based on level
            int len = mData.size();
            while (len >= mData.size()) {
                len = rand.nextInt(9) + 3;
            }
            
            Collections.shuffle(mData);
            mData = mData.subList(0, len);
            
            setCoords();
        }
        
        // TODO - Word Search through word.CoordList
        Word getWord(Pair<Integer, Integer> index) {
            for (Word word : mData) {
                if (word.getCoordList().contains(index)) { return word; }
            }
            
            return null;
        }
        
        /**
         * Assign coordinates for each word in the DataSet
         * Includes Collision-prevention
         */
        final void setCoords() {
            final Random rnd = new Random();
            mData.stream().forEach(word -> {
                int x, y;
                
                do {
                    x = 53;
                    y = 22;
                    
                    while (x > 38 || (x > 18 && x < 27) || x < 7) {
                        x = rnd.nextInt(32) + 7;
                    }
                    
                    while (y > 20 || y < 5) {
                        y = rnd.nextInt(17) + 5;
                    }
                    
                    word.setCoords(new Pair<>(x, y));
                } while (hasCollision(word));
                
                Controller.flipInWord(word.getTileArray());
                
                int lim = (x <= 18) ? 18 : 38;
                int re = (x <= 18) ? 7 : 27;
                UI.putString(x, y, word.getWord(), re, lim);
            });
        }
        
        /**
         * Helper Method to detect collision
         * 
         * @param word the word to be verified
         * @return a boolean value if "hasCollision"
         */
        final boolean hasCollision(Word word) {
            List<Pair<Integer, Integer>> wList = word.getCoordList();
            
            for (Word comp : mData) {
                // Same word
                if (comp.getWord().equals(word.getWord())) { continue; }
                
                // comp word has no coordinates yet
                List<Pair<Integer, Integer>> cList = comp.getCoordList();
                if (cList.isEmpty()) { return false; }
                
                // Any shared coordinate pairs?
                List<Pair<Integer, Integer>> common = new ArrayList<>(cList);
                common.retainAll(wList);
                if (!common.isEmpty()) { return true; }
                
                // Check for horizontal proximity
                if (cList.get(0).getKey() - 1 == wList.get(wList.size() - 1).getKey() ||
                    wList.get(0).getKey() - 1 == cList.get(cList.size() - 1).getKey()) {
                    return true;
                }
            }
            
            return false;
        }
    }
}