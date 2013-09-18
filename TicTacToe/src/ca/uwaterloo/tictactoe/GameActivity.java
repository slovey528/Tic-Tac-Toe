package ca.uwaterloo.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

public class GameActivity extends Activity{
	private Model model;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamelayout);
		this.model = MainActivity.getModel();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		BoardView boardView = new BoardView(this, model);
		ViewGroup boViewGroup = (ViewGroup)findViewById(R.id.BoardLayout);
		boViewGroup.addView(boardView);
		
		PlayerView playerView = new PlayerView(this, model);
		ViewGroup pViewGroup = (ViewGroup)findViewById(R.id.PlayerLayout);
		pViewGroup.addView(playerView);
		
		ToolBarView toolBarView = new ToolBarView(this, model);
		ViewGroup tViewGroup = (ViewGroup)findViewById(R.id.ToolBarLayout);
		tViewGroup.addView(toolBarView);
		
		model.setChanged();
		model.notifyObservers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
