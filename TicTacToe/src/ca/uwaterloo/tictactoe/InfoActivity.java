package ca.uwaterloo.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class InfoActivity extends Activity{
	private TextView gameguide, start, option, play, score;
	private String font = "JennaSue.ttf";
	private Typeface face;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infolayout);
		face = Typeface.createFromAsset(this.getAssets(), font);
		
		gameguide = (TextView)findViewById(R.id.textView1);
		gameguide.setTypeface(face);
		start = (TextView)findViewById(R.id.textView2);
		start.setTypeface(face);
		option = (TextView)findViewById(R.id.textView3);
		option.setTypeface(face);
		play = (TextView)findViewById(R.id.textView4);
		play.setTypeface(face);
		score = (TextView)findViewById(R.id.textView5);
		score.setTypeface(face);
	}
}
