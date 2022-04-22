package MVCEmisor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import boton_panico.TipoEmergencia;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class VistaEmisor extends JFrame implements IVistaEmisor {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelButton; 
	private JButton enviarEmergenciaButton;
	private JPanel panel_1;
	private JPanel panelLabel;
	private JLabel lblNewLabel;
	private JPanel panelIconos;
	private JPanel panelRadio;
	private JRadioButton seguridadButton;
	private JRadioButton bomberosButton;
	private JRadioButton medicaButton;
	private JPanel panelIcon1;
	private JPanel panelIcon2;
	private JPanel panelIcon3;

	/**
	 * Create the frame.
	 */
	public VistaEmisor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
			policiaImagen = ImageIO.read(new File("Iconos/policia.png"));
			bomberoImagen = ImageIO.read(new File("Iconos/bombero.png"));
			doctorImagen = ImageIO.read(new File("Iconos/doctor.png"));
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
		JLabel iconoJLabel3 = new JLabel(icono);
		panelIcon3 = new JPanel();
		panelIconos.add(panelIcon3);
		panelIcon3.add(iconoJLabel3);
		
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
		
		panelLabel = new JPanel();
		contentPane.add(panelLabel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Seleccione Tipo de emegencia");
		panelLabel.add(lblNewLabel);
		
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
	public void EnviarEmergencia() {
		
		
	}

	@Override
	public void MostrarNotificacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

}
