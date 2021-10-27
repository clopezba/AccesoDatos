package Ejercicios;

import java.io.File;

public class UD2_2 {

	public static void main(String[] args) {
		if (args.length == 1) {
			File f = new File(args[0]);
			if (f.exists()) {
				System.out.println("El fichero existe");
			}
			else
				System.out.println("El fichero no existe");
		}
		else {
			System.out.println("No hay argumentos o hay más de uno");
			System.exit(-1);
		}
		
	}

}
