package ca.uwaterloo.tictactoe;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity {
	private Model model;
	private TextView total, xplayer, oplayer;
	private String font = "JennaSue.ttf";
	private Typeface face;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.scoreboardlayout);
		face = Typeface.createFromAsset(this.getAssets(), font);
		this.model = MainActivity.getModel();
		
		total = (TextView)findViewById(R.id.textView1);
		total.setTypeface(face);
		total.setText(total.getText() + Integer.toString(model.getTotalPlayed()));
		xplayer = (TextView)findViewById(R.id.textView2);
		xplayer.setTypeface(face);
		xplayer.setText(model.getxPlayer() + " " + xplayer.getText() + Integer.toString(model.getxWinCounter()));
		oplayer = (TextView)findViewById(R.id.textView3);
		oplayer.setTypeface(face);
		oplayer.setText(model.getoPlayer() + " " + oplayer.getText() + Integer.toString(model.getoWinCounter()));
	}
}
