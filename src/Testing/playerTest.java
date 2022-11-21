import static org.junit.Assert.*;
import cardGame.player;
import cardGame.cardDeck;
import cardGame.card;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;



 public class playerTest {
     private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
     private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
     private final PrintStream originalOut = System.out;
     private final PrintStream originalErr = System.err;
     private static int winner;

     @Before
     public void setUpStreams() {
         System.setOut(new PrintStream(outContent));
         System.setErr(new PrintStream(errContent));
     }

     @After
     public void restoreStreams() {
         System.setOut(originalOut);
         System.setErr(originalErr);
     }

     @org.junit.Test
     public void getPlayer() {
         cardDeck sampleDeck1 = new cardDeck(1);
         cardDeck sampleDeck2 = new cardDeck(2);
         player testPlayer = new player(1, sampleDeck1, sampleDeck2);
         assertEquals(1, testPlayer.getPlayerId());
     }

     @org.junit.Test
     public void getHand() {
         cardDeck testDeck = new cardDeck(1);
         card testCard = new card(1);
         player testPlayer = new player(1, testDeck, testDeck);
         testPlayer.addToHand(testCard);
         assertNotNull(testPlayer.getHand().get(0));
     }


     @org.junit.Test
     public void addToHand() {
         cardDeck testDeck = new cardDeck(1);
         card testCard = new card(1);
         player testPlayer = new player(1, testDeck, testDeck);
         boolean pass = true;
         try {
             testPlayer.addToHand(testCard);
         }
         catch (Exception ex) {
             pass = false;
         }
         assertTrue(pass);
     }

     public void testFolder(){
         cardDeck sampleDeck1 = new cardDeck(1);
         cardDeck sampleDeck2 = new cardDeck(2);
         player testPlayer = new player(0, sampleDeck1, sampleDeck2);
         testPlayer.create_log_file();
         testFolder();
     }


     @org.junit.Test
     public void createLogTest(){

     }

     @org.junit.Test
     public void writeLogTest() throws IOException {
     }



     public static void assertContains(String string, String subString) {
         assertTrue(string.contains(subString));
     }

     @org.junit.Test
     public void getStringHand() {
         cardDeck testDeck = new cardDeck(1);
         card testCard = new card(1);
         player testPlayer = new player(1, testDeck, testDeck);
         testPlayer.addToHand(testCard);
         assertContains("1", testPlayer.getStringHand());
     }

     @org.junit.Test
     public void winnerCheck() {
         cardDeck testDeck = new cardDeck(1);
         player testPlayer = new player(1, testDeck, testDeck);
         card testCard = new card(1);
         for (int i = 0; i<4; i++){
             testPlayer.addToHand(testCard);
         }
         assertTrue(testPlayer.winnerCheck());

     }

     @org.junit.Test
     public void printTurn() {
         boolean pass = true;
         cardDeck testDeck = new cardDeck(1);
         player testPlayer = new player(1, testDeck, testDeck);
         card testCard = new card(1);
         for (int i = 0; i<4; i++){
             testPlayer.addToHand(testCard);
         }
         try {
             testPlayer.printTurn(1, 1);
             assertContains(outContent.toString(), "player 1 draws a 1 from deck 1");
         }
         catch (AssertionError e){
             pass = false;
         }
         assertTrue(pass);
     }



     @org.junit.Test
     public void setWinnerTest() {
         boolean pass = true;
         cardDeck testDeck = new cardDeck(1);
         player testPlayer = new player(1, testDeck, testDeck);

         try {
             testPlayer.setWinner(1);
             assertEquals(1, testPlayer.getWinnerID());
         }
         catch (AssertionError e){
             pass = false;
         }
         assertTrue(pass);
     }

     @org.junit.Test
     public void getWinnerTest(){
         boolean pass = true;
         cardDeck testDeck = new cardDeck(1);
         player testPlayer = new player(1, testDeck, testDeck);
         try {
             Integer.valueOf(testPlayer.getWinnerID());
            }
         catch(NumberFormatException e){
             pass = false;
         }
             assertTrue(pass);
         }

     @org.junit.Test
     public void end() {
         cardDeck testDeck = new cardDeck(1);
         player testPlayer = new player(1, testDeck, testDeck);
         boolean pass = true;
         card testCard = new card(1);
         for (int i = 0; i<4; i++){
             testPlayer.addToHand(testCard);
         }
         try {
             testPlayer.setWinner(1);
             testPlayer.end();
             assertContains(outContent.toString(), "player 1 wins");
         }
         catch(AssertionError e){
             pass = false;
         }
         assertTrue(pass);
     }

     @org.junit.Test
     public void draw() {
         cardDeck sampleDeck1 = new cardDeck(1);
         cardDeck sampleDeck2 = new cardDeck(2);
         player testPlayer = new player(1, sampleDeck1, sampleDeck2);
         card testCard = new card(1);
         boolean pass = true;

         try {
             for (int i = 0; i < 4; i++) {
                 sampleDeck1.addToDeck(testCard);
             }
                assertEquals(1, testPlayer.draw());
             }
         catch (AssertionError e){
             pass = false;
         }
         assertTrue(pass);
     }

     @org.junit.Test
     public void discard() {
         cardDeck sampleDeck1 = new cardDeck(1);
         cardDeck sampleDeck2 = new cardDeck(2);
         player testPlayer = new player(1, sampleDeck1, sampleDeck2);
         card testCard = new card(1);
         card testCard2 = new card (2);
         boolean pass = true;

         try {
             for (int i = 0; i < 3; i++) {
                 testPlayer.addToHand(testCard);
             }
             testPlayer.addToHand(testCard2);
             int discardTestValue = testPlayer.discard();
             assertEquals(2, discardTestValue);
         }
         catch(AssertionError e){
             pass = false;
         }
         assertTrue(pass);
     }

     @org.junit.Test
     public void run() {
     }
 }