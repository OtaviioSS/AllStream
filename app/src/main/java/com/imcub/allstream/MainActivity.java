package com.imcub.allstream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ImageView youtube;
    private ImageView twitch;
    private ImageView booyah;
    private ImageView nimotv;
    private ImageView facebook;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                Toast.makeText(MainActivity.this,"Erro ao carregar anuncios",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(MainActivity.this,"Cobriu a tela",Toast.LENGTH_SHORT).show();
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                Toast.makeText(MainActivity.this,"Obrigado por nos apoiar :)",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(MainActivity.this,"Saiu do aplicativo",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(MainActivity.this,"Voltou do anuncio",Toast.LENGTH_SHORT).show();

            }
        });

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-6840447934899672/6550228848");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        iniciarcomponentes();
        cliques();
    }

    private void cliques() {
        youtube.setOnClickListener(abrirYouTube);
        twitch.setOnClickListener(abrirTwitch);
        nimotv.setOnClickListener(abrirNimoTV);
        facebook.setOnClickListener(abrirFacebook);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mAdView != null){
            mAdView.pause();
        }
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }    }



    private void iniciarcomponentes() {
        youtube = findViewById(R.id.imgYoutube);
        twitch = findViewById(R.id.imgTwitch);
        nimotv = findViewById(R.id.imgNimo);
        facebook = findViewById(R.id.imgFace);
    }

    View.OnClickListener abrirYouTube = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ActivityYoutube.class);
            startActivity(intent);
        }
    };

    View.OnClickListener abrirTwitch = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ActivityTwitch.class);
            startActivity(intent);
        }
    };


    View.OnClickListener abrirNimoTV = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ActivityNimo.class);
            startActivity(intent);
        }
    };
    View.OnClickListener abrirFacebook = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Activity_Facebook.class);
            startActivity(intent);

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null){
            mAdView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdView != null){
            mAdView.pause();
        }
    }
}