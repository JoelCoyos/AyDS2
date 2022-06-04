package MVCMonitor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class VistaMonitor extends JFrame implements IVistaMonitor {

	private JPanel contentPane;
	private JTable tablaEstado;
	private DefaultTableModel modeloTabla= new DefaultTableModel();

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
		setTitle("Monitor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tablaEstado = new JTable(){
	        public boolean isCellEditable(int rowIndex, int vColIndex) {
	            return false;
	        }};
		contentPane.add(tablaEstado, BorderLayout.NORTH);
		this.modeloTabla.addColumn("Servidor");
		this.modeloTabla.addColumn("Estado");
		this.tablaEstado.setModel(modeloTabla);
		this.setVisible(true);
		
	}

	@Override
	public void iniciarMonitor() {
		Object[] objeto1= new Object[2];
		objeto1[0]="A";
		objeto1[1]="ACTIVO";
		Object[] objeto2= new Object[2];
		objeto2[0]="B";
		objeto2[1]="INACTIVO";
		this.modeloTabla.addRow(objeto1);
		this.modeloTabla.addRow(objeto2);
		this.setVisible(true);
		
	}

	@Override
	public void actualizarMonitor() {
		this.modeloTabla.setValueAt("INACTIVO", 0, 1);
		this.modeloTabla.setValueAt("ACTIVO", 1, 1);
		this.setVisible(true);
		
	}

}
