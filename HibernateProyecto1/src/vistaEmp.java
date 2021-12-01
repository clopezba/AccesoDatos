import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class vistaEmp {

	protected JFrame frame;
	protected JTextField txtApellido;
	protected JTextField txtOficio;
	protected JTextField txtSalario;
	protected JTextField txtComision;
	protected JTextField txtFecha;
	protected JFormattedTextField txtEmpNum;
	protected JButton btnConsultar,btnInsertar, btnModificar, btnEliminar, btnSalir, btnLimpiar;
	protected JComboBox<String> cmbDep, cmbDirec;
	

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
		frame.setBounds(100, 100, 494, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("GESTI\u00D3N DE EMPLEADOS");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitulo.setBounds(60, 11, 173, 25);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblEmpNum = new JLabel("N\u00BA EMPLEADO:");
		lblEmpNum.setBounds(10, 54, 93, 25);
		frame.getContentPane().add(lblEmpNum);
		
		JLabel lblApellido = new JLabel("APELLIDO:");
		lblApellido.setBounds(10, 77, 93, 25);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblOficio = new JLabel("OFICIO:");
		lblOficio.setBounds(10, 101, 93, 25);
		frame.getContentPane().add(lblOficio);
		
		JLabel lblSalario = new JLabel("SALARIO:");
		lblSalario.setBounds(10, 124, 93, 25);
		frame.getContentPane().add(lblSalario);
		
		JLabel lblComision = new JLabel("COMISI\u00D3N:");
		lblComision.setBounds(10, 147, 93, 25);
		frame.getContentPane().add(lblComision);
		
		
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
		lblFecha.setBounds(209, 150, 87, 25);
		frame.getContentPane().add(lblFecha);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		txtFecha.setBounds(296, 152, 86, 20);
		frame.getContentPane().add(txtFecha);
		
		JLabel lblFormat = new JLabel("(yyyy-MM-dd)");
		lblFormat.setBounds(382, 150, 86, 25);
		frame.getContentPane().add(lblFormat);
		
		btnConsultar = new JButton("CONSULTAR EMPLEADO");
		btnConsultar.setBounds(222, 52, 202, 24);
		frame.getContentPane().add(btnConsultar);
		
		cmbDep = new JComboBox<String>();
		cmbDep.setModel(new DefaultComboBoxModel<String>(new String[] {"Elige Departamento"}));
		cmbDep.setBounds(286, 92, 138, 22);
		frame.getContentPane().add(cmbDep);
		
		cmbDirec = new JComboBox<String>();
		cmbDirec.setModel(new DefaultComboBoxModel<String>(new String[] {"Elige Director"}));
		cmbDirec.setBounds(286, 120, 138, 22);
		frame.getContentPane().add(cmbDirec);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(60, 186, 349, 64);
		frame.getContentPane().add(panel);
		
		btnInsertar = new JButton("INSERTAR");
		btnInsertar.setPreferredSize(new Dimension(100, 20));
		panel.add(btnInsertar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setPreferredSize(new Dimension(100, 20));
		panel.add(btnEliminar);
		
		btnModificar = new JButton("MODIFICAR");
		btnModificar.setPreferredSize(new Dimension(100, 20));
		panel.add(btnModificar);
		
		btnSalir = new JButton("SALIR");
		btnSalir.setPreferredSize(new Dimension(100, 20));
		panel.add(btnSalir);
		
		btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setPreferredSize(new Dimension(100, 20));
		panel.add(btnLimpiar);
		
		
		NumberFormat format = NumberFormat.getNumberInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(9999);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);
	    txtEmpNum = new JFormattedTextField(formatter);
		txtEmpNum.setBounds(103, 56, 86, 20);
		frame.getContentPane().add(txtEmpNum);
		
	    
		
		frame.setVisible(true);
	}
	public void conectaControlador(Controlador con) {
		btnConsultar.addActionListener(con);
		btnConsultar.setActionCommand("CONSULTAR");
		
		btnInsertar.addActionListener(con);
		btnInsertar.setActionCommand("INSERTAR");
		
		btnEliminar.addActionListener(con);
		btnEliminar.setActionCommand("ELIMINAR");
		
		btnModificar.addActionListener(con);
		btnModificar.setActionCommand("MODIFICAR");
		
		btnSalir.addActionListener(con);
		btnSalir.setActionCommand("SALIR");
		
		btnLimpiar.addActionListener(con);
		btnLimpiar.setActionCommand("LIMPIAR");
	}
}
