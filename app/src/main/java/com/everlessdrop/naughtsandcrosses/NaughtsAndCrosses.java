package com.everlessdrop.naughtsandcrosses;

import android.graphics.Point;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas on 13/10/14.
 */
public class NaughtsAndCrosses {

    //Grid layout
    //[tl][tm][tr]
    //[ml][m ][mr]
    //[bl][bm][br]

    static class symbols
    {
        //Create Some static variables so Input is not confused
        public static String Naught = "O";
        public static String Cross = "X";
        public static String Empty = " ";
        public static String Draw = "D";
    }
    /*
    //Create Some static variables so Input is not confused
    public static String Naught = "O";
    public static String Cross = "X";
    public static String Empty = " ";
    */
    private HashMap<String,String> mBoard = new HashMap<String, String>();

    public boolean naughtTurn = true;
    //Create the board
    public void setupBoard()
    {
        mBoard.put("Full","F");
        mBoard.put("tl",symbols.Empty);
        mBoard.put("tm",symbols.Empty);
        mBoard.put("tr",symbols.Empty);

        mBoard.put("mr",symbols.Empty);
        mBoard.put("m" ,symbols.Empty);
        mBoard.put("ml",symbols.Empty);

        mBoard.put("br",symbols.Empty);
        mBoard.put("bm",symbols.Empty);
        mBoard.put("bl",symbols.Empty);
    }

    public boolean inputToBoard (String pos) {
        String boardState = mBoard.get(pos);
        if (!(isValidSymbol(boardState))){
            //Valid symbol checks if position is a naught or cross
            if (naughtTurn) {
                mBoard.put(pos,symbols.Naught);
            }
            else {
                mBoard.put(pos, symbols.Cross);
            }
            naughtTurn = !naughtTurn;
            return true;
        }
        return false;
    }

    public String checkBoard()
    {
        //Diagonals
        if ( isInLine(mBoard.get("tl"), mBoard.get("m"), mBoard.get("br")) )
        {
            return mBoard.get("m");
        }
        else if ( isInLine(mBoard.get("bl"), mBoard.get("m"), mBoard.get("tr")) )
        {
            return mBoard.get("m");
        }
        else if ( isInLine(mBoard.get("tl"), mBoard.get("tm"), mBoard.get("tr")) )
        {
            //Top row
            return mBoard.get("tm");
        }
        else if ( isInLine(mBoard.get("ml"), mBoard.get("m"), mBoard.get("mr")) )
        {
            //Middle row
            return mBoard.get("m");
        }
        else if ( isInLine(mBoard.get("bl"), mBoard.get("bm"), mBoard.get("br")) )
        {
            //Bottom row
            return mBoard.get("bm");
        }
        else if ( isInLine(mBoard.get("tl"), mBoard.get("ml"), mBoard.get("bl")) )
        {
            //Left Column
            return mBoard.get("ml");
        }
        else if ( isInLine(mBoard.get("tm"), mBoard.get("m"), mBoard.get("bm")) )
        {
            //Middle Column
            return mBoard.get("m");
        }
        else if ( isInLine(mBoard.get("tr"), mBoard.get("mr"), mBoard.get("br")) )
        {
            //Right Column
            return mBoard.get("mr");
        }

        //Check for a full board
        if (isBoardFull(mBoard)){
            return symbols.Draw;
        }

        return symbols.Empty;
    }

    private boolean isBoardFull(HashMap<String,String> board)
    {
        String tl = board.get("tl");
        String tm = board.get("tm");
        String tr = board.get("tr");

        String ml = board.get("ml");
        String m  = board.get("m" );
        String mr = board.get("mr");

        String bl = board.get("bl");
        String bm = board.get("bm");
        String br = board.get("br");

        if (!tl.equals(symbols.Empty) && !tm.equals(symbols.Empty) && !tr.equals(symbols.Empty)){
            if (!ml.equals(symbols.Empty) && !m.equals(symbols.Empty) && !mr.equals(symbols.Empty)){
                if (!bl.equals(symbols.Empty) && !bm.equals(symbols.Empty) && !br.equals(symbols.Empty)){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }

        //TODO make this work
    }

    public static boolean isValidSymbol(String symbol)
    {
        if (symbol.length() != 1 || !(symbol == symbols.Cross || symbol == symbols.Naught))
        {
            return false;
        }
        return true;
    }

    private boolean isInLine(String point1,String point2,String point3)
    {
        if (((point1 == point2) && (point2 == point3)) && isValidSymbol(point2))
        {
            return true;
        }
        return false;
    }

}
