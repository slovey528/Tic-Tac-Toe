package ca.uwaterloo.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

public class OptionActivity extends Activity{
	private Model model;
	private RadioButton playerX, playerO;
	private ImageButton playNow, quite;
	private EditText player1, player2;
	private PopupWindow popUp;
	private String font = "JennaSue.ttf";
	private Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.model = MainActivity.getModel();
		setContentView(R.layout.optionlayout);
		face = Typeface.createFromAsset(this.getAssets(), font);
		
		player1 = (EditText)findViewById(R.id.editText1);
		player2 = (EditText)findViewById(R.id.editText2);
		player1.setTypeface(face);
		player2.setTypeface(face);
		
		playerX = (RadioButton)findViewById(R.id.radioButton1);
		playerX.setTypeface(face);
		playerO = (RadioButton)findViewById(R.id.radioButton2);
		playerO.setTypeface(face);
		playerX.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				model.setCurTurn("X");
				model.setNextTurn("X");
				playerO.setChecked(false);
			}
		});
		playerO.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				model.setCurTurn("O");
				model.setNextTurn("O");
				playerX.setChecked(false);
			}
		});
		
		playNow = (ImageButton)findViewById(R.id.imageButton1);
		playNow.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				model.setxPlayer(player1.getText().toString());
				model.setoPlayer(player2.getText().toString());
				model.newGame();
				Intent intent = new Intent(OptionActivity.this, GameActivity.class);
	            startActivity(intent);
	            finish();
			}
		});
		
		quite = (ImageButton)findViewById(R.id.imageButton2);
		quite.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
