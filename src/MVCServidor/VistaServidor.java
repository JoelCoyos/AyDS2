package MVCServidor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VistaServidor extends JFrame implements IVistaServidor {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JTable tabla_EmergenciasRecibidas;
	private JScrollPane scrollPane;
	private DefaultTableModel tabla_Recibidos;
	private JLabel estadoLabel;

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
		setResizable(false);
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		estadoLabel = new JLabel("Estado:");
		estadoLabel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		panelNorte.add(estadoLabel);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelCentral.add(scrollPane);
		
		tabla_EmergenciasRecibidas = new JTable();
		tabla_EmergenciasRecibidas.setRowSelectionAllowed(false);
		this.tabla_Recibidos = new DefaultTableModel();
		scrollPane.setViewportView(tabla_EmergenciasRecibidas);
		this.tabla_Recibidos.addColumn("Fecha y Hora");
		this.tabla_Recibidos.addColumn("Evento Ocurrido");
		this.tabla_EmergenciasRecibidas.setModel(this.tabla_Recibidos);
		tabla_EmergenciasRecibidas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tabla_EmergenciasRecibidas.getColumnModel().getColumn(0).setPreferredWidth(200);
		this.tabla_EmergenciasRecibidas.getColumnModel().getColumn(1).setPreferredWidth(574);
		scrollPane.setViewportView(this.tabla_EmergenciasRecibidas);
		this.setVisible(true);
		
		this.setAlwaysOnTop(true);
		
	}


	@Override
	public void AgregarLog(Log log) {
		Object[] objeto= new Object[2];
		objeto[0]=log.fechaHora;
		objeto[1]=log.mensaje;
		this.tabla_Recibidos.addRow(objeto);
		this.setVisible(true);
		
	}
	
	@Override
	public void CambiarEstado(String estado)
	{
		this.estadoLabel.setText("Estado: "+estado);
		this.setVisible(true);
	}

}
