package dev.chau.adsmodjava.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdsManager {

    private final Context context;
    private final AdRequest adRequest;
    private final String TAG;

    private InterstitialAd mInterstitialAd;

    private AdsManager(Context context, String tag) {
        TAG = tag;
        this.context = context;
        MobileAds.initialize(context, initializationStatus -> {
        });
        adRequest = new AdRequest.Builder().build();
    }

    public void loadBanner(AdView adView) {
        adView.loadAd(adRequest);
    }

    public void setInterstitial(String adUnitId) {
        InterstitialAd.load(context, adUnitId, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d(TAG, "The ad was dismissed.");
                                setInterstitial(adUnitId);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d(TAG, "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                Log.d(TAG, "The ad was shown.");
                                mInterstitialAd = null;
                            }
                        });
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }

    public void showInterstitial(Activity activity){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            Log.d(TAG, "The interstitial ad wasn't ready yet.");
        }
    }

    public void loadNative(String adUnitId, TemplateView templateView){
        AdLoader adLoader = new AdLoader.Builder(context, adUnitId)
                .forNativeAd(nativeAd -> {
                    NativeTemplateStyle styles = new
                            NativeTemplateStyle.Builder().build();
                    templateView.setStyles(styles);
                    templateView.setNativeAd(nativeAd);
                })
                .build();
        adLoader.loadAd(adRequest);
    }




    private static AdsManager instance = null;

    public static AdsManager getInstance(Context context, String tag) {
        if (instance == null) {
            instance = new AdsManager(context, tag);
        }
        return instance;
    }
}
