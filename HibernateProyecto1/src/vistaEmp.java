import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class vistaEmp {

	private JFrame frame;
	private JTextField txtEmpNum;
	private JTextField txtApellido;
	private JTextField txtOficio;
	private JTextField txtSalario;
	private JTextField txtComision;
	private JTextField txtFecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vistaEmp window = new vistaEmp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vistaEmp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblTitulo.setBounds(60, 23, 129, 25);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblEmpNum = new JLabel("N\u00BA EMPLEADO:");
		lblEmpNum.setBounds(24, 57, 77, 25);
		frame.getContentPane().add(lblEmpNum);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(24, 80, 77, 25);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblOficio = new JLabel("OFICIO:");
		lblOficio.setBounds(24, 104, 77, 25);
		frame.getContentPane().add(lblOficio);
		
		JLabel lblSalario = new JLabel("SALARIO:");
		lblSalario.setBounds(24, 127, 77, 25);
		frame.getContentPane().add(lblSalario);
		
		JLabel lblComision = new JLabel("COMISI\u00D3N:");
		lblComision.setBounds(24, 150, 77, 25);
		frame.getContentPane().add(lblComision);
		
		txtEmpNum = new JTextField();
		txtEmpNum.setBounds(103, 59, 86, 20);
		frame.getContentPane().add(txtEmpNum);
		txtEmpNum.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(103, 82, 152, 20);
		frame.getContentPane().add(txtApellido);
		
		txtOficio = new JTextField();
		txtOficio.setColumns(10);
		txtOficio.setBounds(103, 106, 152, 20);
		frame.getContentPane().add(txtOficio);
		
		txtSalario = new JTextField();
		txtSalario.setColumns(10);
		txtSalario.setBounds(103, 129, 86, 20);
		frame.getContentPane().add(txtSalario);
		
		txtComision = new JTextField();
		txtComision.setColumns(10);
		txtComision.setBounds(103, 152, 86, 20);
		frame.getContentPane().add(txtComision);
		
		JLabel lblFecha = new JLabel("FECHA ALTA:");
		lblFecha.setBounds(199, 150, 71, 25);
		frame.getContentPane().add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(266, 152, 86, 20);
		frame.getContentPane().add(txtFecha);
		
		JLabel lblFormat = new JLabel("(yyyy-MM-dd)");
		lblFormat.setBounds(353, 150, 71, 25);
		frame.getContentPane().add(lblFormat);
		
		JButton btnNewButton = new JButton("CONSULTAR EMPLEADO");
		btnNewButton.setBounds(222, 52, 202, 24);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox cmbDep = new JComboBox();
		cmbDep.setModel(new DefaultComboBoxModel(new String[] {"Elige Departamento"}));
		cmbDep.setBounds(286, 92, 138, 22);
		frame.getContentPane().add(cmbDep);
		
		JComboBox cmbDirec = new JComboBox();
		cmbDirec.setModel(new DefaultComboBoxModel(new String[] {"Elige Director"}));
		cmbDirec.setBounds(286, 120, 138, 22);
		frame.getContentPane().add(cmbDirec);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(60, 186, 307, 64);
		frame.getContentPane().add(panel);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.setPreferredSize(new Dimension(91, 20));
		panel.add(btnInsertar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setPreferredSize(new Dimension(91, 20));
		panel.add(btnEliminar);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setPreferredSize(new Dimension(91, 20));
		panel.add(btnModificar);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setPreferredSize(new Dimension(91, 20));
		panel.add(btnSalir);
		
		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setPreferredSize(new Dimension(91, 20));
		panel.add(btnLimpiar);
	}
}
