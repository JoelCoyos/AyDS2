package MVCReceptor;

import java.util.ArrayList;
import MVCReceptor.EmergenciaRegistro;
import boton_panico.Emergencia;

public class ModeloReceptor {
   private ArrayList<EmergenciaRegistro> emergencias= new ArrayList<EmergenciaRegistro>();

   public ModeloReceptor() {}
   
   public ArrayList<EmergenciaRegistro> getEmergencias(){
	   return this.emergencias;
   }
   
   public void  addEmergencia(String fechaHora,Emergencia e) {
	   this.emergencias.add(new EmergenciaRegistro(fechaHora,e));
   }
   
}
