package boton_seleccion;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Controlador_Boton extends Observable implements MouseListener {
	
	private Vista_Boton vista;
	private Modelo_Boton modelo;
	private Color colorInicial;
	private Observer observer;
	
	public Controlador_Boton(Vista_Boton vista,String id) {
		this.vista=vista;
		this.modelo= new Modelo_Boton(id);
		this.vista.addMouseListener(this);
		this.colorInicial= this.vista.getBackground();
	}
	
	public String getID() {
		return this.modelo.getId();
	}
	
	public Vista_Boton getVista() {
		return this.vista;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		this.vista.setBackground(Color.GREEN);
		this.modelo.activar();
		this.notifyObservers(this.getID());
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!this.modelo.estaActivado()) {
			this.vista.setBackground(Color.MAGENTA);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!this.modelo.estaActivado()) {
			this.vista.setBackground(this.colorInicial);
		}
		
	}
	
	public void desactivar() {
		if(this.modelo.estaActivado()) {
			this.modelo.desactivar();
			this.vista.setBackground(this.colorInicial);
		}
		
	}
	
	
	
	@Override
	public synchronized void addObserver(Observer o) {
		this.observer=o;
	}

	@Override
	public void notifyObservers(Object arg) {
		if(this.observer!=null) {
			this.observer.update(this, this.getID());
		}
		
	}

	
}
