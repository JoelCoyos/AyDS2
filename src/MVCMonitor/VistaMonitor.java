package MVCMonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;

public class VistaMonitor extends JFrame implements IVistaMonitor {

	private JPanel contentPane;
	private JTable tablaEstado;
	private JLabel ubicacion_Label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaMonitor frame = new VistaMonitor();
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
	public VistaMonitor() {
		setResizable(false);
		setTitle("Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		ubicacion_Label = new JLabel("Ubicacion: ");
		ubicacion_Label.setFont(new Font("Arial", Font.PLAIN, 15));
		panel.add(ubicacion_Label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		tablaEstado = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		tablaEstado.setModel(new DefaultTableModel(
			new Object[][] {
				{"Servidor","Estado"},
				{"A", ""},
				{"B", ""},
			},
			new String[] {
				"Servidor", "Estado"
			}
		));
		panel_1.add(tablaEstado);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
	}

	
	public void actualizarServidorA(Servidor servidor)
	{
		this.tablaEstado.getModel().setValueAt(servidor.getEstado(), 1, 1);
	}
	
	public void actualizarServidorB(Servidor servidor)
	{
		this.tablaEstado.getModel().setValueAt(servidor.getEstado(), 2, 1);
	}
	
	@Override
	public void actualizar_ubicacion(String ubicacion) {
		this.ubicacion_Label.setText("Ubicacion: "+ubicacion);
		this.setVisible(true);
		
	}

}
