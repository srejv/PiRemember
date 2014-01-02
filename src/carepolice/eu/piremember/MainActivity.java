package carepolice.eu.piremember;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.LabeledIntent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public PiComparer piComparer;
	
	
	private OnKeyListener listener = new OnKeyListener() {
		
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
	        // If the event is a key-down event on the "enter" button
	    	EditText edittext = (EditText) findViewById(R.id.enterPiHere);
			TextView label = (TextView) findViewById(R.id.textView1);
			TextView currentStatus = (TextView) findViewById(R.id.textNumberOfString);
			System.out.println("Current Input: " + edittext.getText());
			System.out.flush();
			switch(event.getKeyCode()) {
			case KeyEvent.KEYCODE_0:
			case KeyEvent.KEYCODE_1:
			case KeyEvent.KEYCODE_2:
			case KeyEvent.KEYCODE_3:
			case KeyEvent.KEYCODE_4:
			case KeyEvent.KEYCODE_5:
			case KeyEvent.KEYCODE_6:
			case KeyEvent.KEYCODE_7:
			case KeyEvent.KEYCODE_8:
			case KeyEvent.KEYCODE_9:
			case KeyEvent.KEYCODE_PERIOD:
				// Perform action on key press
				String enteredSoFar = edittext.getText().toString();
				System.out.println(enteredSoFar);
				boolean isCorrectSoFar = piComparer.compare(enteredSoFar);
				if(!isCorrectSoFar) {
					String failed = "You are incorrect. The next few are shown: ";
					String nextThree = piComparer.getNextThree(enteredSoFar);
					currentStatus.setText("Current number of digits: " + enteredSoFar.length());
					label.setText(failed + nextThree);
					return true;
				} else {
					String yoEnteredSoFar = "You have entered \""+ enteredSoFar + "\"  so far. ";
					currentStatus.setText("Current number of digits: " + enteredSoFar.length());
					label.setText(yoEnteredSoFar);
				}
	        }
	        return false;
	    }
    };
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        piComparer = new PiComparer();
        final EditText edittext = (EditText) findViewById(R.id.enterPiHere);
        final TextView label = (TextView) findViewById(R.id.textView1);
		final TextView currentStatus = (TextView) findViewById(R.id.textNumberOfString);
        Button retry = (Button) findViewById(R.id.retryButton);
        edittext.setOnKeyListener(listener);
        retry.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				edittext.setText("");
				label.setText("Retry button pressed");
				currentStatus.setText("Current number of digits: 0");
				return false;
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
