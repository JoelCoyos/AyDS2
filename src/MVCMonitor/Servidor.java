package MVCMonitor;

public class Servidor {
	
	private int puerto;
	private String estado; //Primario o secundario
	private boolean disponible; //true = esta disponible, false = esta caido
	
	public Servidor(int puerto)
	{
		this.puerto = puerto;
		estado = "";
		disponible = false;
	}
	
	public int getPuerto()
	{
		return puerto;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	

}
