package boton_seleccion;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

public class Panel_Seleccion extends JPanel implements Observer {
	
	private Controlador_Boton boton1;
	private Controlador_Boton boton2;
	private Controlador_Boton boton3;
	private int widhtBoton=40;
	private int heightBoton=40;
	
	public Panel_Seleccion() {
		
		this.boton1=new Controlador_Boton(new Vista_Boton("/Iconos/policia.png",this.widhtBoton,this.heightBoton), new Modelo_Boton("Personal de Seguridad"));
		this.boton2=new Controlador_Boton(new Vista_Boton("/Iconos/bombero.png",this.widhtBoton,this.heightBoton), new Modelo_Boton("Bomberos"));
		this.boton3=new Controlador_Boton(new Vista_Boton("/Iconos/doctor.png",this.widhtBoton,this.heightBoton), new Modelo_Boton("Asistencia Medica"));
		this.boton1.addObserver(this);
		this.boton2.addObserver(this);
		this.boton3.addObserver(this);
		setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panelBoton1 = new JPanel();
		add(panelBoton1);
		panelBoton1.setLayout(new GridLayout(0, 1, 0, 0));
		panelBoton1.add(this.boton1.getVista());
		this.boton1.getVista().setHorizontalAlignment(SwingConstants.CENTER);
		this.boton1.getVista().setVerticalAlignment(SwingConstants.CENTER);
		this.boton1.getVista().setVisible(true);
		
		JPanel panelLabel1 = new JPanel();
		add(panelLabel1);
		panelLabel1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel seguridad_Label = new JLabel("Personal de Seguridad");
		seguridad_Label.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel1.add(seguridad_Label);
		
		JPanel panelBoton2 = new JPanel();
		add(panelBoton2);
		panelBoton2.setLayout(new GridLayout(0, 1, 0, 0));
		panelBoton2.add(this.boton2.getVista());
		this.boton2.getVista().setHorizontalAlignment(SwingConstants.CENTER);
		this.boton2.getVista().setVerticalAlignment(SwingConstants.CENTER);
		this.boton2.getVista().setVisible(true);
		
		
		JPanel panelLabel2 = new JPanel();
		add(panelLabel2);
		panelLabel2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel bomberos_Label = new JLabel("Bomberos");
		bomberos_Label.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabel2.add(bomberos_Label);
		
		JPanel panelBoton3 = new JPanel();
		add(panelBoton3);
		panelBoton3.setLayout(new GridLayout(0, 1, 0, 0));
		panelBoton3.add(this.boton3.getVista());
		this.boton3.getVista().setHorizontalAlignment(SwingConstants.CENTER);
		this.boton3.getVista().setVerticalAlignment(SwingConstants.CENTER);
		this.boton3.getVista().setVisible(true);
		
		JPanel panel_Label3 = new JPanel();
		add(panel_Label3);
		panel_Label3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel medicos_Label = new JLabel("Asistencia Medica");
		medicos_Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Label3.add(medicos_Label);

	}

	@Override
	public void update(Observable o, Object arg) {
		if(o!=null) {
				String mensaje= arg.toString();
				if(mensaje.equals("Personal de Seguridad")) {
					this.boton2.desactivar();
					this.boton3.desactivar();
				}else if(mensaje.equals("Bomberos")) {
					this.boton1.desactivar();
					this.boton3.desactivar();
				}else if(mensaje.equals("Asistencia Medica")) {
					this.boton1.desactivar();
					this.boton2.desactivar();
				}
				this.boton1.getVista().setVisible(true);
				this.boton2.getVista().setVisible(true);
				this.boton3.getVista().setVisible(true);
		}
	}

	

}
