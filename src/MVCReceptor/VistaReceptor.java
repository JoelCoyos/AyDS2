package MVCReceptor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import MVCReceptor.IVistaReceptor;
import clasesComunes.Emergencia;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class VistaReceptor extends JFrame implements IVistaReceptor {

	private JPanel contentPane;
	private String[] columnasTabla= new String[] {"Fecha y Hora","Tipo de Emergencia","Ubicacion"};
	private Object[] datos;
	private JTable tablaEmergencias;
	private DefaultTableModel modeloTabla= new DefaultTableModel();


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public VistaReceptor() {
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
		
		tablaEmergencias = new JTable() ;
		this.modeloTabla.addColumn("Fecha y Hora");
		this.modeloTabla.addColumn("Tipo de Emergencia");
		this.modeloTabla.addColumn("Ubicacion");
		this.tablaEmergencias.setModel(modeloTabla);
		scrollPane.setViewportView(tablaEmergencias);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
	}
	
	@Override
	public void MostrarEmergencia(Emergencia emergencia) {
		Object[] objeto= new Object[3];
		objeto[0]=emergencia.getFechaHora();
		objeto[1]=emergencia.getTipoEmergencia();
		objeto[2]=emergencia.getUbicacion();
		this.modeloTabla.addRow(objeto);
		this.setVisible(true);
		
	}

	

}
