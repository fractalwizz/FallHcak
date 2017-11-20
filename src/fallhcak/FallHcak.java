package fallhcak;

import javafx.application.Application;
import javafx.stage.Stage;

import fallhcak.Control.Controller;
import fallhcak.Data.DataHelper;
import fallhcak.UI.UI;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Replication of Fallout Hacking Mini-Game in Java8 + JavaFX
 * Zenimax Media owns Copyrights to Fallout, RobCo (c) 2017
 * @author William Young
 */
public class FallHcak extends Application {
    private static int mLevel = 0;
    private static DataHelper dataHelper;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fallout Hacking Mini-game");
        primaryStage.setScene(UI.initialize());
        
        dataHelper = new DataHelper();
        
        Controller.initialize(dataHelper);
        
        primaryStage.show();
        
        startProcess();
    }
    
    public static void main(String[] args) { launch(args); }
    
    /**
     * Starts up the Game procedures.
     * Initial Terminal setup, plus word list handles
     * Supports restarting the simulation if requested.
     */
    public static void startProcess() {
        UI.clear();
        
        Random rand = new Random();
        mLevel = rand.nextInt(12) + 4;
        
        UI.putString(0, 0, "ROBCO INDUSTRIES (TM) TERMLINK PROTOCOL", 0, 53);
        UI.putString(0, 1, "ENTER PASSWORD NOW", 0, 53);
        
        String attempts = new StringBuilder("4 ATTEMPT(S) LEFT: ")
            .append(Character.toString((char) 9608))
            .append(" ")
            .append(Character.toString((char) 9608))
            .append(" ")
            .append(Character.toString((char) 9608))
            .append(" ")
            .append(Character.toString((char) 9608))
            .toString();
        
        UI.putString(0, 3, attempts, 0, 53);
        
        addMemAddr();
        
        dataHelper.setLevel(mLevel); // Create new DataSet
    }
    
    /**
     * Helper Function that adds all Line Memory Addresses
     */
    private static void addMemAddr() {
        int dec = 3;
        
        Random rand = new Random();
        do { dec = rand.nextInt(3687) + 61440; } while (dec % 4 != 0);
        final int ced = dec; // Hack to keep IntConsumer + Compiler happy
        
        // IntConsumer
        IntStream.rangeClosed(5, 21).forEach((int y) -> {
            int num = ced + (y - 5) * 12;
            UI.putString(0, y, "0x" + Integer.toHexString(num).toUpperCase(), 0, 53);
            UI.putString(20, y, "0x" + Integer.toHexString(num + 204).toUpperCase(), 0, 53);
        });
    }
}