package com.example.administrator.qrscan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ReaderActivity extends AppCompatActivity {

    public static final String MY_TAG="the_costom_message";

    private Button button1;
    private EditText pass_word;
    private Button button_sbm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        addListenerOnButton();
    }

    public void addListenerOnButton(){
        pass_word = (EditText)findViewById(R.id.idPassword);
        button_sbm = (Button)findViewById(R.id.idLoginButton);

        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ReaderActivity.this,pass_word.getText(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    public void onButtonClick(View v){
        EditText e1 = (EditText)findViewById(R.id.editText1);
        EditText e2 = (EditText)findViewById(R.id.editText2);
        TextView v3 = (TextView)findViewById(R.id.textView3);

        Double num1 = Double.parseDouble(e1.getText().toString());
        Double num2 = Double.parseDouble(e2.getText().toString());
        Double num3 = num1+num2;
        v3.setText(Double.toString(num3));


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MY_TAG,"onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MY_TAG,"onResume invoked");

        Log.i(MY_TAG,"on_Create");

        button1 = (Button)  findViewById(R.id.button1);
        final Activity activity= this;
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("扫描");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MY_TAG,"onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MY_TAG,"onStop invoked") ;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MY_TAG,"onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MY_TAG,"onDestroy invoked");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        TextView v3 = (TextView)findViewById(R.id.textView3);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null){
            if (result.getContents()==null){
                Toast.makeText(this,"你取消了扫描",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                v3.setText(result.getContents());
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
