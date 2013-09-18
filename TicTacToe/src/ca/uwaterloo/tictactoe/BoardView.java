package ca.uwaterloo.tictactoe;

import java.util.*;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BoardView extends RelativeLayout implements Observer{
	
	private Model model;
	private ArrayList<Button> buttonGroup = new ArrayList<Button>();
	private int[] buttonId = {R.id.button1, R.id.button2, R.id.button3, 
							  R.id.button4, R.id.button5, R.id.button6, 
							  R.id.button7, R.id.button8, R.id.button9};
	private String font = "JennaSue.ttf";
	private Typeface face;
	
	public BoardView(Context context, Model inputModel) {
		super(context);
		View.inflate(context, R.layout.boardlayout, this);
		Activity fontContext = (Activity)context;
		this.model = inputModel;
		model.addObserver(this);
		face = Typeface.createFromAsset(fontContext.getAssets(), font);
		for (int i = 0; i < 9; i++) {
			Button button = (Button)findViewById(buttonId[i]);
			button.setTypeface(face);
			final int index = i;
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					model.setCurButton(index);
				}
			});
			buttonGroup.add(button);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (model.isCleanBoard()) {
			for (int i = 0; i < 9; i++) {
				buttonGroup.get(i).setText("");
				buttonGroup.get(i).setClickable(true);
			}
		}
		if (model.isInGame() && !model.isInvalid()) {
			int index = model.getCurButton();
			if (model.getCurTurn() == "X") {
				buttonGroup.get(index).setText("X");
				buttonGroup.get(index).setTextColor(Color.BLUE);
			}
			else {
				buttonGroup.get(index).setText("O");
				buttonGroup.get(index).setTextColor(Color.RED);
			}
		}
		if (model.isHasWinner()) {
			int winIndex = model.getWinIndex();
			if (model.isColumWin()) {
				for (int i = winIndex; i < 9; i+=3) {
					Button winButton = buttonGroup.get(i);
					winButton.setTextColor(Color.YELLOW);
				}
			}
			else if (model.isRowWin()) {
				Button winButton = buttonGroup.get(winIndex);
				winButton.setTextColor(Color.YELLOW);
				winButton = buttonGroup.get(winIndex+1);
				winButton.setTextColor(Color.YELLOW);
				winButton = buttonGroup.get(winIndex+2);
				winButton.setTextColor(Color.YELLOW);
			}
			else if (model.isCrossWin()) {
				if (winIndex == 0) {
					Button winButton = buttonGroup.get(0);
					winButton.setTextColor(Color.YELLOW);
					winButton = buttonGroup.get(4);
					winButton.setTextColor(Color.YELLOW);
					winButton = buttonGroup.get(8);
					winButton.setTextColor(Color.YELLOW);
				}
				if (winIndex == 2) {
					Button winButton = buttonGroup.get(2);
					winButton.setTextColor(Color.YELLOW);
					winButton = buttonGroup.get(4);
					winButton.setTextColor(Color.YELLOW);
					winButton = buttonGroup.get(6);
					winButton.setTextColor(Color.YELLOW);
				}
			}
			for (int i = 0; i < 9; i++) {
				buttonGroup.get(i).setClickable(false);
			}
		}
		if(model.getMoveCounter() == 9) {
			for (int i = 0; i < 9; i++) {
				buttonGroup.get(i).setClickable(false);
			}
		}
	}
}
