package MVCReceptor;

import java.util.ArrayList;
import MVCReceptor.EmergenciaRegistro;

public class ModeloReceptor {
   private ArrayList<EmergenciaRegistro> emergencias= new ArrayList<EmergenciaRegistro>();

   public ModeloReceptor() {}
   
   public ArrayList<EmergenciasRegistro> getEmergencias(){
	   return this.emergencias;
   }
   
   public void  addEmergencia(String fechaHora,Emergencia e) {
	   this.emergencias.add(new EmergenciaRegistro(fechaHora,e));
   }
   
}
