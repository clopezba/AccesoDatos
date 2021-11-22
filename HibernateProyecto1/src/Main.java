
public class Main {

	public static void main(String[] args) {
		vistaEmp vista = new vistaEmp();
		Controlador contr = new Controlador(vista);
		vista.conectaControlador(contr);

	}

}
