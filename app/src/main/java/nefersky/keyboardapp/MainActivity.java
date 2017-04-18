package nefersky.keyboardapp;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long mBackPressed;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onBackPressed(){
        showQuitToast();
    }

    @Override
    protected void onUserLeaveHint(){
        Toast.makeText(getApplicationContext(), "Нажата клавиша Home", Toast.LENGTH_SHORT).show();
        super.onUserLeaveHint();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_MENU){
            event.startTracking();
            mTextView.setText(R.string.key_down);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_MENU){
            mTextView.setText(R.string.long_press);
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    private void showQuitDialog(){
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены");
        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();
    }

    public void showQuitToast(){
        if((mBackPressed + 2000) > System.currentTimeMillis()) {
            showQuitDialog();
        } else {
            mBackPressed = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show();
        }
    }
}
