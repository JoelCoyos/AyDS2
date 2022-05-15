package MVCServidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clasesComunes.Emergencia;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class VistaServidor extends JFrame implements IVistaServidor {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JTable tabla_EmergenciasRecibidas;
	private JScrollPane scrollPane;
	private DefaultTableModel tabla_Recibidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaServidor frame = new VistaServidor();
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
	public VistaServidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblSolicitudesDeEmergecias = new JLabel("Solicitudes de Emergencias Recibidas");
		lblSolicitudesDeEmergecias.setFont(new Font("Arial Black", Font.PLAIN, 15));
		panelNorte.add(lblSolicitudesDeEmergecias);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 2));
		
		scrollPane = new JScrollPane();
		panelCentral.add(scrollPane);
		
		tabla_EmergenciasRecibidas = new JTable();
		scrollPane.setViewportView(tabla_EmergenciasRecibidas);
		this.tabla_Recibidos.addColumn("Fecha y Hora");
		this.tabla_Recibidos.addColumn("Tipo de Emergencia");
		this.tabla_Recibidos.addColumn("Ubicacion");
		this.tabla_EmergenciasRecibidas.setModel(this.tabla_Recibidos);
		scrollPane.setViewportView(this.tabla_EmergenciasRecibidas);
		this.setVisible(true);
		
		
	}

	@Override
	public void agregar_emergencia(Emergencia emergencia) {
		Object[] objeto= new Object[3];
		objeto[0]=emergencia.getFechaHora();
		objeto[1]=emergencia.getTipoEmergencia();
		objeto[2]=emergencia.getUbicacion();
		this.tabla_Recibidos.addRow(objeto);
		this.setVisible(true);
		
	}

}
