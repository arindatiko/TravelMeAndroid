package arindatiko.example.com.travelme;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xwray.passwordview.PasswordView;

import arindatiko.example.com.travelme.model.User;
import arindatiko.example.com.travelme.util.LocationService;
import arindatiko.example.com.travelme.util.SessionManager;
import arindatiko.example.com.travelme.util.Validation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtTelp)
    TextInputEditText txtTelp;
    @BindView(R.id.txtPassword)
    PasswordView txtPassword;

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        startService(new Intent(LoginActivity.this, LocationService.class));
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.isLogin()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
        }
    }

    @OnClick(R.id.btnLogin)
    public void toLogin(View view) {
        if (Validation.checkEmpty(txtTelp) && Validation.checkPassword(this, txtPassword)) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            //String telp = sessionManager.

            API.service_post.login(txtTelp.getText().toString(), txtPassword.getText().toString()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    /*Toast.makeText(LoginActivity.this, String.valueOf(user.getId_user()), Toast.LENGTH_SHORT).show();
                    Log.e("cok", String.valueOf(user.getId_user()));
                    Log.e("cok", String.valueOf(user.getUsername()));
                    Log.e("cok", String.valueOf(user.getUser_type()));
                    Log.e("cok", String.valueOf(user.getNama_lengkap()));*/
                    if (user != null) {
                        sessionManager.setUid(String.valueOf(user.getId_user()));
                        sessionManager.setLogin(true);
                        //sessionManager.setWisata();
                        sessionManager.setUsername(user.getUsername());
                        sessionManager.setUsertype(user.getUser_type());

                        //Toast.makeText(LoginActivity.this, sessionManager.getUsername(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error login! Check your phone number and password.", Toast.LENGTH_SHORT).show();
                    Log.e("cok", String.valueOf(t.fillInStackTrace()));
                    progressDialog.dismiss();
                }
            });
        }
    }
}
