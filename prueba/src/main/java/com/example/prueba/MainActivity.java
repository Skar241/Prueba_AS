package com.example.prueba;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private ViewPager viewPager;
	PagerAdapter pageAdapter;
	
	private final String[] titulos={"Calendario","Trámite Actual","Trámites por internet","Facebook"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MainPageAdapter(this));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position){
				Toast.makeText(MainActivity.this, titulos[position], Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

			}

			@Override
			public void onPageScrollStateChanged(int state){

			}
		});	
	}
		
}
