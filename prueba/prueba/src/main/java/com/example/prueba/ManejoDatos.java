package com.example.prueba;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ManejoDatos {
	
	Context context;
	public ManejoDatos(){
		
	}
	
	public ManejoDatos(Context context){
		this.context=context;
	}
	
	
	public String[] tramites=new String[14];      //02/06/2014 modificado  
	public final String[] fechas = { " 19/05/2014","02/06/2014"," 09/06/2014"," 10/06/2014",
			"23/06/2014"," 26/06/2014"," 30/06/2014",
			"30/07/2014","07/08/2014"," 15/08/2014",
			"21/08/2014"," 22/08/2014"," 25/08/2014",
			" 29/09/2014"};
	public final String[] fechasLimte= { " 30/05/2014"," 06/06/2014"," 17/06/2014"," 24/06/2014",
			"26/06/2014"," 30/06/2014"," 02/07/2014",
			"07/08/2014","09/08/2014"," 20/08/2014",
			"22/08/2014"," 24/08/2014"," 28/08/2014",
			" 12/10/2014"};
	

	public String[] setTramites(){
		SharedPreferences prefe = context.getSharedPreferences("tramitesFI",Context.MODE_PRIVATE);
        int i=0;
        String [] tmp={ "Pago de colegiatura anual", "Nueva contraseña de inscripción", "Encuesta de asignaturas", "Cuestionario de opinión",
    			"Publicación de grupos y horarios","Número de Inscripción","Inscripción por Internet",
    			"Salones asignados y vacantes","Cambios de grupo","Bajas extemporáneas","Revadilación de laboratorios","Comprobante definitivo","Dudas y aclarciones",
    			"Consulta de incripción en el SIAE "};
        String algo=prefe.getString("var","");
        if(algo.equals("")){
        	for(i=0;i<14;i++)tramites[i]=tmp[i];
        }
        else{
        	StringTokenizer tokens=new StringTokenizer(algo, "\n");
        	while(i<14){
        		String str=tokens.nextToken();
        		tramites[i]=str;
        		i++;
        	}
        }
        return tramites;
	}
	
	public void getTramites(String [] tamites) {
        SharedPreferences preferencias=context.getSharedPreferences("tramitesFI",Context.MODE_PRIVATE);
        Editor editor=preferencias.edit();
        int i=0;
        String datos="";
        for(i=0;i<14;i++)datos=datos+tamites[i]+"\n";
        editor.putString("var", datos);
        editor.commit();
        //finish();
    }
	
	public String[] setFechas(){
		return fechas;
	}
	
	public void getTramiteToFacebook(String tramiteEnFace){
		SharedPreferences preferencias=context.getSharedPreferences("tFb",Context.MODE_PRIVATE);
        Editor editor=preferencias.edit();
        editor.putString("varfb", tramiteEnFace);
        editor.commit();
	}
	
	public String setTramiteToFacebook(Context context){
		SharedPreferences prefe = context.getSharedPreferences("tFb",Context.MODE_PRIVATE);
		return prefe.getString("varfb","");
	}
	
	public String[] setFechasLimite(){
		return fechasLimte;
	}
}
