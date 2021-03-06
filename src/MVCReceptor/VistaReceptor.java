package MVCReceptor;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import clasesComunes.Emergencia;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VistaReceptor extends JFrame implements IVistaReceptor {

	private JPanel contentPane;
	private String[] columnasTabla= new String[] {"Fecha y Hora","Tipo de Emergencia","Ubicacion"};
	private Object[] datos;
	private JTable tablaEmergencias;
	private JLabel ubicacionLabel;
	private DefaultTableModel modeloTabla= new DefaultTableModel();


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public VistaReceptor() {
		setTitle("Receptor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panelNorte.add(panel_2);
		
		ubicacionLabel = new JLabel("Ubicacion: ");
		ubicacionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_2.add(ubicacionLabel);
		
		JPanel panel_1 = new JPanel();
		panelNorte.add(panel_1);
		
		JPanel panel = new JPanel();
		panelNorte.add(panel);
		
		JLabel solicitudesLabel = new JLabel("Solicitudes Recibidas");
		panel.add(solicitudesLabel);
		solicitudesLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 2));
		
		JScrollPane scrollPane = new JScrollPane();
		panelCentral.add(scrollPane);
		
		tablaEmergencias = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tablaEmergencias.setRowSelectionAllowed(false);
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

	@Override
	public void actualizar_ubicacion(String ubicacion) {
		this.ubicacionLabel.setText("Ubicacion: " + ubicacion);
		this.setVisible(true);
		
	}

	

}
