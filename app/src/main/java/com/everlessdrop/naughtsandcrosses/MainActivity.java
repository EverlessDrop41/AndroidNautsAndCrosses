package com.everlessdrop.naughtsandcrosses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    //Grid layout
    //[tl][tm][tr]
    //[ml][m ][mr]
    //[bl][bm][br]

    private NaughtsAndCrosses mBoard = new NaughtsAndCrosses();
    private boolean failedAttempt = false;

    Drawable naughtImage;
    Drawable crossImage;

    public TextView turnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBoard.setupBoard();

        naughtImage = getResources().getDrawable(R.drawable.naught);
        crossImage = getResources().getDrawable(R.drawable.cross);

        turnDisplay = (TextView)findViewById(R.id.turnIndicator);

        final ImageView tl = (ImageView)findViewById(R.id.tl);
        final ImageView tm = (ImageView)findViewById(R.id.tm);
        final ImageView tr = (ImageView)findViewById(R.id.tr);

        final ImageView ml = (ImageView)findViewById(R.id.ml);
        final ImageView m  = (ImageView)findViewById(R.id.m );
        final ImageView mr = (ImageView)findViewById(R.id.mr);

        final ImageView bl = (ImageView)findViewById(R.id.bl);
        final ImageView bm = (ImageView)findViewById(R.id.bm);
        final ImageView br = (ImageView)findViewById(R.id.br);

        Button resetButton = (Button)findViewById(R.id.restartButton);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        //Top row of board
        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("tl",tl);
            }
        });
        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("tm",tm);
            }
        });
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("tr",tr);
            }
        });


        //Middle row of board
        ml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("ml",ml);
            }
        });
        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("m",m);
            }
        });
        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("mr",mr);
            }
        });


        //Bottom row of board
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("bl",bl);
            }
        });
        bm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("bm",bm);
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeBoard("br",br);
            }
        });
    }

    void changeBoard(String pos, ImageView posView){
        if (mBoard.inputToBoard(pos)){
            if (mBoard.naughtTurn) {
                posView.setImageDrawable(crossImage);
                turnDisplay.setText(getString(R.string.naught));
            }
            else {
                posView.setImageDrawable(naughtImage);
                turnDisplay.setText(getString(R.string.cross));
            }
        }

        if (!mBoard.checkBoard().equals(NaughtsAndCrosses.symbols.Empty) && !failedAttempt) {
            failedAttempt = true;
            String state = mBoard.checkBoard();
            if (state.equals(NaughtsAndCrosses.symbols.Cross)){
                turnDisplay.setText(getString(R.string.crossWin));
            }
            else if (state.equals(NaughtsAndCrosses.symbols.Naught)) {
                turnDisplay.setText(getString(R.string.naughtWin));
            }
            else if (state.equals(NaughtsAndCrosses.symbols.Draw)){
                turnDisplay.setText(getString(R.string.draw));
            }
            return;
        }

        if (!mBoard.checkBoard().equals(NaughtsAndCrosses.symbols.Empty) && failedAttempt) {
            failedAttempt = true;
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
