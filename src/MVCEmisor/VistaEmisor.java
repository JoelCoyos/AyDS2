package MVCEmisor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VistaEmisor extends JFrame implements IVistaEmisor {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelButton; 
	private JButton enviarEmergenciaButton;
	private JPanel panel_1;
	private JPanel panelIconos;
	private JPanel panelRadio;
	private JRadioButton seguridadButton;
	private JRadioButton bomberosButton;
	private JRadioButton medicaButton;
	private JPanel panelIcon1;
	private JPanel panelIcon2;
	private JPanel panelIcon3;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel ubicacion_Label;

	/**
	 * Create the frame.
	 */
	public VistaEmisor() {
		setTitle("Emisor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelButton = new JPanel();
		contentPane.add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		enviarEmergenciaButton = new JButton("Enviar Emergencia");
		panelButton.add(enviarEmergenciaButton);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panelIconos = new JPanel();
		panel_1.add(panelIconos, BorderLayout.WEST);

		panelIcon1 = new JPanel();
		panelIconos.add(panelIcon1);
		
		
		BufferedImage policiaImagen = null;
		BufferedImage bomberoImagen = null;
		BufferedImage doctorImagen = null;
		try {
			policiaImagen = ImageIO.read(getClass().getResource("/resources/policia.png"));
			bomberoImagen = ImageIO.read(getClass().getResource("/resources/bombero.png"));
			doctorImagen = ImageIO.read(getClass().getResource("/resources/doctor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icono = new ImageIcon(policiaImagen);
		Image iconoImage = icono.getImage();
		iconoImage = icono.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icono = new ImageIcon(iconoImage);
		panelIconos.setLayout(new GridLayout(3, 1, 1, 1));
		panelIconos.setSize(10, 20);
		JLabel iconoJLabel = new JLabel(icono);
		panelIcon1.add(iconoJLabel);
		
		icono = new ImageIcon(bomberoImagen);
		iconoImage = icono.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icono = new ImageIcon(iconoImage);
		panelIcon2 = new JPanel();
		panelIconos.add(panelIcon2);
		JLabel iconoJLabel2 = new JLabel(icono);
		panelIcon2.add(iconoJLabel2);
		
		icono = new ImageIcon(doctorImagen);
		iconoImage = icono.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		icono = new ImageIcon(iconoImage);
		panelIcon3 = new JPanel();
		JLabel iconoJLabel3 = new JLabel(icono);
		panelIconos.add(iconoJLabel3);
		
		
		panelRadio = new JPanel();
		panel_1.add(panelRadio, BorderLayout.CENTER);
		panelRadio.setLayout(new GridLayout(3, 1, 0, 0));
		buttonGroup.add(seguridadButton);
		
		seguridadButton = new JRadioButton("Personal de Seguridad");
		panelRadio.add(seguridadButton);
		buttonGroup.add(seguridadButton);
		
		bomberosButton = new JRadioButton("Bomberos");
		panelRadio.add(bomberosButton);
		buttonGroup.add(bomberosButton);
		
		medicaButton = new JRadioButton("Asistencia Medica");
		panelRadio.add(medicaButton);
		buttonGroup.add(medicaButton);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		
		ubicacion_Label = new JLabel("Ubicacion Actual:");
		ubicacion_Label.setFont(new Font("Arial Black", Font.PLAIN, 12));
		panel_2.add(ubicacion_Label);
		
		panel_3 = new JPanel();
		panel.add(panel_3);
		
		panel_4 = new JPanel();
		panel.add(panel_4);
		
		lblNewLabel = new JLabel("Seleccione Tipo de emergencia");
		panel_4.add(lblNewLabel);
		
		this.setVisible(true);
	}

	@Override
	public String tipoEmergencia() {
		String tipoEmergencia = null;
		if(seguridadButton.isSelected())
			tipoEmergencia = "Seguridad";
		else if(bomberosButton.isSelected())
			tipoEmergencia = "Bomberos";
		else if(medicaButton.isSelected())
			tipoEmergencia = "Medica";
		return tipoEmergencia;
	}
	
	public void setActionListener(ActionListener actionListener)
	{
		enviarEmergenciaButton.addActionListener(actionListener);
	}


	@Override
	public void MostrarNotificacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	@Override
	public void actualizar_ubicacion(String ubicacion) {
		this.ubicacion_Label.setText("Ubicacion Actual: "+ ubicacion);
		
	}

	

}
