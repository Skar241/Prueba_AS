package com.example.prueba;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainPageAdapter extends PagerAdapter{
	//
	private TextView tramiteActual;
	private ListView Calendario;
	private WebView tramitesInternet;
	Context contextoPrincipal;
	
	mostrarAlertDialogo mensaje;
	
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar FechaDeHoy = Calendar.getInstance();
	private String fechaActual=sf.format(FechaDeHoy.getTime());
	int al;
	ManejoDatos data;
	private String[]Tramites;
	private String[]Fechas;
	private String[]FechasLimte;
	
	
	
	public MainPageAdapter() {
		// TODO Auto-generated constructor stub
	}
	public MainPageAdapter(Context contextoPrincipal) {
		this.contextoPrincipal = contextoPrincipal;
		this.data= new ManejoDatos(contextoPrincipal);
		this.Tramites=data.setTramites();
		this.Fechas=data.setFechas();
		this.FechasLimte=data.setFechasLimite();
		this.mensaje=new mostrarAlertDialogo(contextoPrincipal,fechaActual);
	}
	
	@Override
	public int getCount(){
		return 3;//4
	}

	@Override
	public Object instantiateItem(ViewGroup collection, int position){
		View page = null;
		switch (position)
		{
		case 0:
			if (Calendario == null){
				Calendario = (ListView) LayoutInflater.from(contextoPrincipal).inflate(R.layout.page_three, null);
				initListView();
			}
			page = Calendario;
			break;
		case 1:
			if(tramiteActual==null){
				tramiteActual=(TextView) LayoutInflater.from(contextoPrincipal).inflate(R.layout.page_two, null);
				tramiteActual.setText("La fecha de hoy es:"+fechaActual+"\nEl próximo tramite que\npuedes hacer es:\n"+Tramites[al]+"\nY lo puedes realizar del:"+Fechas[al]+" hasta el "+FechasLimte[al]);
			}
			page=tramiteActual;
			break;
		default:
			if (tramitesInternet == null){
				tramitesInternet = (WebView) LayoutInflater.from(contextoPrincipal).inflate(R.layout.pagina, null);
				tramitesInternet.loadUrl("http://inscripciones.ingenieria.unam.mx/");
				tramitesInternet.setWebViewClient(new WebViewClient());
			}
			page = tramitesInternet;
			break;
		}

		((ViewPager) collection).addView(page, 0);

		return page;
	}

	@Override
	public boolean isViewFromObject(View view, Object object){
		return view == object;
	}

	@Override
	public void destroyItem(View collection, int position, Object view){
		((ViewPager) collection).removeView((View) view);
	}

	private void initListView(){
		String[] items = new String[14];
		for (int i = 0; i < 14; i++){
			items[i] = Tramites[i];
		}
		Calendario.setAdapter(new ArrayAdapter<String>(contextoPrincipal, R.layout.textview, items));
		Calendario.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				mensaje.getPos(position);
				mensaje.setAlertDialogo((String) parent.getItemAtPosition(position),Fechas[position]);
				
			}
		});

	}
	
	
}
