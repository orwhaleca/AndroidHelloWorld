package edu.frostburg.cosc631.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "edu.frostburg.cosc631.helloworld.MESSAGE";
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, ResultActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String input = editText.getText().toString();
        String letters = input.replaceAll("\\W","");
        String result = "The first letter of your name is "+letters.substring(0,1).toUpperCase();
        intent.putExtra(EXTRA_MESSAGE, result);
        Bundle params = new Bundle();
        params.putString("result_sent", result);
        mFirebaseAnalytics.logEvent("button_pressed", params);
        startActivity(intent);

    }
}
