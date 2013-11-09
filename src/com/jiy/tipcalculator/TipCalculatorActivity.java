package com.jiy.tipcalculator;

import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TipCalculatorActivity extends Activity {

	public EditText etAmount;
	public TextView tvTip;
	public Button btnTen;
	public Button btnFifteen;
	public Button btnTwenty;
	DecimalFormat format = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        btnTen=(Button) findViewById(R.id.btnTen);
        btnFifteen=(Button) findViewById(R.id.btnFifteen);
        btnTwenty=(Button) findViewById(R.id.btnTwenty);
        
        etAmount=(EditText) findViewById(R.id.etAmount); 
        etAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
			}
			
			@Override
			//disable the buttons until an amount is entered 
			public void afterTextChanged(Editable s) {
				try {
					String chars = etAmount.toString();
					Boolean isDigit=false;
					for(int i=0; i<chars.length(); i++){
						if(Character.isDigit(chars.charAt(i))){
							isDigit=true;
						}
					}
					
					if(isDigit){
						
						btnTen.setEnabled(true);
						btnFifteen.setEnabled(true);
						btnTwenty.setEnabled(true);
				}
				}catch (NumberFormatException nfe) {
				    nfe.printStackTrace();
				    Log.d("etAmount", etAmount.toString());
				   
				}
			}
		});
        tvTip = (TextView) findViewById(R.id.tvTip);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculator, menu);
        return true;
    }
    public void onSubmit(View v){
    	
    	if(etAmount.toString()!=null){
	    	double billAmount = Double.parseDouble(etAmount.getText().toString());
	    	
	    	try{
		    	double tipAmount=0.0;
		    	if(v.getTag().toString().equals("ten")){
		    		tipAmount = (billAmount * 10) / 100;
		    	}
		    	else if(v.getTag().toString().equals("fifteen")){
		    		tipAmount = (billAmount * 15) / 100;
		    	}
		    	else if(v.getTag().toString().equals("twenty")){
		    		tipAmount = (billAmount * 20) / 100;
		    	}
		    	String formattedText1 = format.format(tipAmount);
		    	tvTip.setText("$"+formattedText1);
	    	}catch(NumberFormatException e) {
	    		   Log.e("ERROR", e.toString());
	    		   return;
	    	}
    	}
    	else
    		return;
    }

}



