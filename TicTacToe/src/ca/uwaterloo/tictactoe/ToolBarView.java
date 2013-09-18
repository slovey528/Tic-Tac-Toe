package ca.uwaterloo.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToolBarView extends RelativeLayout implements Observer{
	
	private Model model;
	private TextView toolBarMes;
	private ImageButton newGame, scoreBoard, again;
	private String font = "JennaSue.ttf";
	private Typeface face;
	
	public ToolBarView(final Context context, Model inputModel) {
		super(context);
		
		Activity fontContext = (Activity)context;
		View.inflate(context, R.layout.toolbarlayout, this);
		this.model = inputModel;
		model.addObserver(this);
		face = Typeface.createFromAsset(fontContext.getAssets(), font);
		
		newGame = (ImageButton)findViewById(R.id.imageButton1);
		newGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				model.setCurTurn("X");
				Intent intent = new Intent(context, OptionActivity.class);
	            context.startActivity(intent);
	            Activity curContext = (Activity)context;
	            curContext.finish();
			}
		});
		scoreBoard = (ImageButton)findViewById(R.id.imageButton2);
		scoreBoard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, ScoreBoardActivity.class);
	            context.startActivity(intent);
			}
		});
		
		again = (ImageButton)findViewById(R.id.imageButton3);
		again.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				model.playAgain();
			}
		});
		toolBarMes = (TextView)findViewById(R.id.textView1);
        toolBarMes.setTypeface(face);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.isInvalid()) toolBarMes.setText("Invalid Move, Please Try Again.");
		else if (model.isHasWinner()) {
			if (model.isxWin()) toolBarMes.setText("Congratulations, X Wins!");
			else toolBarMes.setText("Congratulations, O Wins!");
		}
		else if (!model.isHasWinner() && model.getMoveCounter() == 9) toolBarMes.setText("Sorry, Tie Game.");
		else toolBarMes.setText(model.getMoveCounter() + " Moves");
	}
}
