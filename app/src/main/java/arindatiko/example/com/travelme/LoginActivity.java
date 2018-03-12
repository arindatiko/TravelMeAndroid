package arindatiko.example.com.travelme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    Button registerButton, loginButton;
    TextView registerBtn, loginBtn;
    LinearLayout registerForm, loginForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginForm = (LinearLayout) findViewById(R.id.loginForm);
        registerBtn = (TextView) findViewById(R.id.signup);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginBtn = (TextView) findViewById(R.id.loginWord);
        registerForm = (LinearLayout) findViewById(R.id.registerForm);

        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                registerForm = (LinearLayout) findViewById(R.id.registerForm);
                loginForm.setVisibility(View.GONE);
                registerForm.setVisibility(View.VISIBLE);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                loginForm.setVisibility(View.VISIBLE);
                registerForm.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
