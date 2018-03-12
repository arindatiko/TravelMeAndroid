package arindatiko.example.com.travelme;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity {

    public void onAttachedtoWindow(){
        super.onAttachedToWindow();
        getWindow().setFormat(1);
    }

    Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startAnimation();
    }

    private void startAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.clearAnimation();
        layout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView img = (ImageView) findViewById(R.id.splash);
        img.clearAnimation();
        img.startAnimation(anim);

        splashThread = new Thread(){
            @Override
            public void run(){
                try{
                    int waited = 0;
                    //pause
                    while (waited<3500){
                        sleep(100);
                        waited +=100;
                    }
                    Intent intent = new Intent (SplashActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e){

                } finally {
                    SplashActivity.this.finish();
                }
            }
        };
        splashThread.start();
    }
}
