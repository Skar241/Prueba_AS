package com.example.prueba;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class mostrarAlertDialogo{
	
	ManejoDatos data;
	String[]Tramites;
	String[]Fechas;
	String[]FechasLimte;
	String fechaActual;
	
	Context context;
	ListView Calendario;
	TextView tramiteActual;
	
	int pos=0;
	public mostrarAlertDialogo(){
		
	}
	
	public mostrarAlertDialogo(Context context,String fechaActual){
		this.context=context;
		this.Calendario=(ListView) LayoutInflater.from(context).inflate(R.layout.page_three, null);
		this.tramiteActual=(TextView) LayoutInflater.from(context).inflate(R.layout.page_two, null);
		this.fechaActual=fechaActual;
		this.data= new ManejoDatos(context);
		this.Tramites=data.setTramites();
		this.Fechas=data.setFechas();
		this.FechasLimte=data.setFechasLimite();
	}
	public void SiNoFacebook(){
		AlertDialog.Builder fbMensaje = new AlertDialog.Builder(context);
		fbMensaje.setCancelable(true);
		fbMensaje.setMessage("¿Deseas compartirlo en Facebook?");
		fbMensaje.setNegativeButton("No", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	         }
	      });
		fbMensaje.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	        	 Toast.makeText(context,"se presiono que sí" , Toast.LENGTH_SHORT).show();
	        	 LanzarActivity();
	         }
	      });
		fbMensaje.show();
	}
	
	
	public void LanzarActivity(){
		Intent i = new Intent(context, Conectar_FB.class );
        context.startActivity(i);
	}
	public void getPos(int position){
		pos=position;
	}
	
	public void setAlertDialogo(String tramite,String fecha){
	       AlertDialog.Builder dialog = new AlertDialog.Builder(context);
	       
	       dialog.setMessage(Tramites[pos]+" puede realizarse apartir del\n"+fecha+".\n¿Deseas marcarlo como Trámitado?");
	       dialog.setCancelable(true);
	       dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
		         
		         @Override
		         public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		         }
		      });
	       
	       
	       
	       dialog.setNeutralButton("Marcar", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	        	 int tmpPos;
	        	 if(!Tramites[pos].endsWith(" *")){
	        		 data.getTramiteToFacebook(Tramites[pos]);
	        		 Tramites[pos]=Tramites[pos]+" *";
	        		 Calendario.setAdapter(new ArrayAdapter<String>(context, R.layout.textview, Tramites));
	        		 data.getTramites(Tramites);
	        		 if(pos==13)tmpPos=13;
	        		 else tmpPos=pos+1;
	        		 tramiteActual.setText("La fecha de hoy es:"+fechaActual+"\nEl próximo tramite que\npuedes hacer es:\n"+Tramites[tmpPos]+"\nY lo puedes realizar del:"+Fechas[tmpPos]+" hasta el "+FechasLimte[tmpPos]);
	        		 dialog.cancel();
	        		 SiNoFacebook();
	        	 }
	         }
	      });
	       dialog.setPositiveButton("Desmarcar", new DialogInterface.OnClickListener() {
	         
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	            //dialog.cancel();
	            if(Tramites[pos].endsWith(" *")){
	            	Tramites[pos]=Tramites[pos].substring(0, Tramites[pos].indexOf(" *"));
	            	Calendario.setAdapter(new ArrayAdapter<String>(context, R.layout.textview, Tramites));
	            	data.getTramites(Tramites);
	            	int posi=proximoTramite()+1;
	            	if(posi==14)pos=13;
	            	tramiteActual.setText("La fecha de hoy es:"+fechaActual+"\nEl próximo tramite que\npuedes hacer es:\n"+Tramites[posi]+"\nY lo puedes realizar del:"+Fechas[posi]+" hasta el "+FechasLimte[posi]);
	            	
	            }
	         }
	      });
	       dialog.show();
	       
	    }
	private int proximoTramite(){
		int flag=0;
		for(int i=0;i<14;i++){
			if(Tramites[i].endsWith(" *"))
				flag=i;
		}
		if(flag==14)return 13;
		else return flag;
	}
	
}

