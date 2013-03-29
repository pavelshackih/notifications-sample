package com.example.untitled1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyActivity extends Activity implements View.OnClickListener {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button) findViewById(android.R.id.button1);
        button.setOnClickListener(this);
        textView = (TextView) findViewById(android.R.id.text1);
    }

    @Override
    public void onClick(View view) {
        NotifyUtils.buildNotify(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int num = intent.getIntExtra("num", -1);
        textView.setText("Extra: " + num);
    }
}
