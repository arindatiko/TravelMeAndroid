package arindatiko.example.com.travelme;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText noTelp, setKode;
    private Button btnMasuk, btnVerify, btnResend;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener stateListener;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    private String VerifikasiId;
    private String no_telp;
    private TextView PhoneID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        noTelp = (TextInputEditText) findViewById(R.id.txtTelp);
        setKode = (TextInputEditText) findViewById(R.id.txtKode);
        btnMasuk = (Button) findViewById(R.id.btnLogin);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        btnResend = (Button) findViewById(R.id.btnResend);

        btnResend.setOnClickListener(this);
        btnResend.setEnabled(true);
        btnVerify.setOnClickListener(this);
        btnMasuk.setOnClickListener(this);

        //connect project with firebase authentication
        auth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Melampirkan Listener pada FirebaseAuth saat Activity Dimulai
        auth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(stateListener != null){
            //Menghapus Listener pada FirebaseAuth saat Activity Dihentikan
            auth.removeAuthStateListener(stateListener);
        }
    }

    private void setupVerificationCallback(){
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // Callback didalam sini akan dipanggil/dieksekusi saat terjadi proses pengiriman kode
                // Dan User Diminta untuk memasukan kode verifikasi

                // Untuk Menyimpan ID verifikasi dan kirim ulang token
                VerifikasiId = verificationId;
                resendToken = token;
                btnResend.setEnabled(true);
                Toast.makeText(getApplicationContext(), "Mendapatkan Kode Verifikasi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationCompleted(PhoneAuthCredential Credential) {
                // Callback disini akan dipanggil saat Verifikasi Selseai atau Berhasil
                Toast.makeText(getApplicationContext(), "Verifikasi Selesai", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(Credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Callback disini akan dipanggil saat permintaan tidak valid atau terdapat kesalahan
                Toast.makeText(getApplicationContext(), "Verifikasi Gagal, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Sign In Berhasil
                        startActivity(new Intent(LoginActivity2.this, MainActivity.class));
                        finish();
                    }else{
                        //Sign In Gagal
                        if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            // Kode Yang Dimasukan tidal Valid.
                            Toast.makeText(getApplicationContext(), "Kode yang dimasukkan tidak valid", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                no_telp = noTelp.getText().toString();
                setupVerificationCallback();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        no_telp, //nomor yg diverifikasi
                        60, //durasi waktu
                        TimeUnit.SECONDS, //satuan waktu
                        this,
                        callbacks
                );
                Toast.makeText(getApplicationContext(), "Memverifikasi, Mohon Tunggu", Toast.LENGTH_SHORT).show();
                noTelp.setText("");
                break;
            case R.id.btnVerify:
                String VerifyCode = setKode.getText().toString();
                if(TextUtils.isEmpty(VerifyCode)){
                     //Memberi Pesan pada user bahwa kode verifikasi tidak boleh kosong saat menekan Tombol Verifikasi
                    Toast.makeText(this, "Masukkan kode verifikasi anda", Toast.LENGTH_SHORT).show();
                }else{
                    //Memverifikasi Nomor Telepon, Saat Tombol Verifikasi Ditekan
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerifikasiId, VerifyCode);
                    signInWithPhoneAuthCredential(credential);
                    Toast.makeText(getApplicationContext(),"Sedang Memverifikasi", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnResend:
                no_telp = noTelp.getText().toString();
                setupVerificationCallback();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        no_telp, //nomor yg diverifikasi
                        60, //durasi waktu
                        TimeUnit.SECONDS, //satuan waktu
                        this,
                        callbacks,
                        resendToken
                );
                Toast.makeText(getApplicationContext(), "Mengirim ulang kode verifikasi", Toast.LENGTH_SHORT).show();
                noTelp.setText("");
                break;
        }
    }
}
