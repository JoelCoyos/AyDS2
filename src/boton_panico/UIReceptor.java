package boton_panico;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class UIReceptor extends JFrame {

	private JPanel contentPane;
	private String[] columnasTabla= new String[] {"Fecha y Hora","Tipo de Emergencia","Ubicacion"};
	private Object[][] datos;
	private JTable tablaEmergencias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIReceptor frame = new UIReceptor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public UIReceptor() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel solicitudesLabel = new JLabel("Solicitudes Recibidas");
		solicitudesLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		panelNorte.add(solicitudesLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 2));
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane);
		
		datos= new Object[][] {
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:55","Personal de Seguridad","8653232"},
			{"17/11/2021 23:30","Asistencia Medica","5632325"},
			{"17/11/2021 23:35","Bomberos","5632325"},
		
		};
		
		tablaEmergencias = new JTable(datos,this.columnasTabla);
		scrollPane.setViewportView(tablaEmergencias);
		
		
		
	}

}
