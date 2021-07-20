package com.owo.phlurtyzpaid.activity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.core.BaseActivity;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private final String WELOCOME_TO = "Welcome to";

    private final String FLIRTYZ = " Flirtyz";

    private TextView mainHeader;

    private TextView selectVersion;

    private TextView freeVersion;

    private TextView unlokedVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        initViews();

        addClickListener();

        Typeface faceBigSurprise = Typeface.createFromAsset(getAssets(), "font/bigSurprise.ttf");


        mainHeader.setTypeface(faceBigSurprise);

        Typeface faceMyriadSemiBold = Typeface.createFromAsset(getAssets(), "font/myriadProSemibold.otf");

        selectVersion.setTypeface(faceMyriadSemiBold);

        Typeface faceSantafeletplain = Typeface.createFromAsset(getAssets(), "font/SantaFe LETPlain.ttf");

        freeVersion.setTypeface(faceSantafeletplain);

        unlokedVersion.setTypeface(faceSantafeletplain);

        Spannable word = new SpannableString(WELOCOME_TO);

        word.setSpan(new ForegroundColorSpan(ContextCompat.getColor(WelcomeActivity.this, R.color.hollywood_cerise)), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mainHeader.setText(word);

        Spannable wordTwo = new SpannableString(FLIRTYZ);

        wordTwo.setSpan(new ForegroundColorSpan((ContextCompat.getColor(WelcomeActivity.this, R.color.mine_shaft))), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        mainHeader.append(wordTwo);

    }

    public void initViews() {

        mainHeader = (TextView) findViewById(R.id.main_header);

        selectVersion = (TextView) findViewById(R.id.select_version);

        freeVersion = (TextView) findViewById(R.id.free_version);

        unlokedVersion = (TextView) findViewById(R.id.unlocked_version);

    }

    public void addClickListener() {

        freeVersion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_version:
                toMainActivity();
        }
    }

    public void toMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainModActivity.class);
        startActivity(intent);
    }
}
