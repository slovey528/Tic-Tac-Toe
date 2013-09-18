package ca.uwaterloo.tictactoe;

import java.util.*;

import org.w3c.dom.Text;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ca.uwaterloo.tictactoe.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PlayerView extends RelativeLayout implements Observer{
	
	private Model model;
	private TextView playerX, playerO; 
	private ImageView leftArrow, rightArrow;
	private String font = "JennaSue.ttf";
	private Typeface face;
	
	public PlayerView(Context context, Model inputModel) {
		super(context);
		View.inflate(context, R.layout.playerlayout, this);
		Activity fontContext = (Activity)context;
		this.model = inputModel;
		model.addObserver(this);
		face = Typeface.createFromAsset(fontContext.getAssets(), font);
		
		playerX = (TextView)findViewById(R.id.textView1);
		playerX.setTextColor(Color.BLUE);
		playerX.setTypeface(face);
		playerX.setText("X: " + model.getxPlayer());
		playerO = (TextView)findViewById(R.id.textView2);
		playerO.setTextColor(Color.RED);
		playerO.setTypeface(face);
		playerO.setText("O: " + model.getoPlayer());
		leftArrow = (ImageView)findViewById(R.id.imageView1);
		leftArrow.setVisibility(INVISIBLE);
		rightArrow = (ImageView)findViewById(R.id.imageView2);
		rightArrow.setVisibility(INVISIBLE);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.getNextTurn() == "X") {
			rightArrow.setVisibility(INVISIBLE);
			leftArrow.setVisibility(VISIBLE);
		}
		else {
			leftArrow.setVisibility(INVISIBLE);
			rightArrow.setVisibility(VISIBLE);
		}
	}
}
