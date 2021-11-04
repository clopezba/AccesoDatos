package AnexoV;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Vista extends JFrame{

	private JFrame frame;
	protected JTextField txtID;
	protected JTextField txtNombre;
	protected JTextField txtEstatura;
	protected JTextField txtEdad;
	protected JTextField txtLocalidad;
	protected JTable table;
	protected JButton btnAñadir, btnBorrar, btnEditar;
	protected DefaultTableModel dtm;
	private JScrollPane scroll;
	protected Object [][]datos;
	protected String [] cabecera;
	

	/**
	 * Create the application.
	 */
	Vista() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 333);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSocioId = new JLabel("Socio ID: ");
		lblSocioId.setBounds(24, 21, 81, 14);
		frame.getContentPane().add(lblSocioId);
		
		txtID = new JTextField();
		txtID.setBounds(117, 18, 56, 20);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setBounds(24, 46, 81, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblEstatura = new JLabel("Estatura: ");
		lblEstatura.setBounds(24, 71, 56, 14);
		frame.getContentPane().add(lblEstatura);
		
		JLabel lblEdad = new JLabel("Edad: ");
		lblEdad.setBounds(24, 96, 46, 14);
		frame.getContentPane().add(lblEdad);
		
		JLabel lblLocalidad = new JLabel("Localidad: ");
		lblLocalidad.setBounds(24, 122, 81, 14);
		frame.getContentPane().add(lblLocalidad);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(116, 43, 126, 20);
		frame.getContentPane().add(txtNombre);
		
		txtEstatura = new JTextField();
		txtEstatura.setColumns(10);
		txtEstatura.setBounds(115, 68, 46, 20);
		frame.getContentPane().add(txtEstatura);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		txtEdad.setBounds(115, 93, 32, 20);
		frame.getContentPane().add(txtEdad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(116, 119, 126, 20);
		frame.getContentPane().add(txtLocalidad);
		
		scroll = new JScrollPane();
		cabecera = new String[] {"Socio ID", "Nombre", "Estatura", "Edad", "Localidad"};
		dtm = new DefaultTableModel(datos, cabecera);
		table = new JTable(dtm);
		scroll.setViewportView(table);
		frame.getContentPane().add(scroll);
		scroll.setBounds(24, 147, 376, 103);
		
		btnAñadir = new JButton("Añadir");
		btnAñadir.setBounds(48, 261, 89, 23);
		frame.getContentPane().add(btnAñadir);
		
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(174, 261, 89, 23);
		frame.getContentPane().add(btnBorrar);
		
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(295, 261, 89, 23);
		frame.getContentPane().add(btnEditar);
		
		frame.setVisible(true);
		
	}

	public void conectarControlador(Controlador control) {
		btnAñadir.addActionListener(control);
		btnAñadir.setActionCommand("AÑADIR");
		
		btnBorrar.addActionListener(control);
		btnBorrar.setActionCommand("BORRAR");
		
		btnEditar.addActionListener(control);
		btnEditar.setActionCommand("EDITAR");
		
		table.addMouseListener(control);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}