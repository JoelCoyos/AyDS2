package boton_seleccion;

import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Vista_Boton extends JButton {
	
	private  int widht,height;
	private Icon icono;
	
	public Vista_Boton(String rutaIcono,int widht,int height) {
		this.setSize(widht, height);
		this.widht=widht;
		this.height=height;
		ImageIcon icon= new ImageIcon(this.getClass().getResource(rutaIcono));
		this.icono= new ImageIcon(icon.getImage().getScaledInstance(widht-10, height-10, height-20));
		this.setIcon(this.icono);
		
	}
	
	public int getWidht() {
		return this.widht;
	}
	
	public int getHeight() {
		return this.height;
	}
	


}
