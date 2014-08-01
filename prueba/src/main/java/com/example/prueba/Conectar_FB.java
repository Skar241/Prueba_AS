package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Conectar_FB extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conectar_fb);
	}
	public void cerrar(View view) {
    	finish();
    } 
}
