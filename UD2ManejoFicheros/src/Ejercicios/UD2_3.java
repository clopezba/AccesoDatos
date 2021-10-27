package Ejercicios;

import java.io.File;
import java.io.IOException;

public class UD2_3 {

	public static void main(String[] args) {
		boolean resultado;
		File directorio = new File("C:\\DAM2");
		resultado = directorio.mkdir();
		if (resultado)
			System.out.println("Directorio creado");
		else {
			System.out.println("No se pudo crear el directorio");
			//System.exit(-1);
		}
		File fichero = new File("C:\\DAM2\\Cristina.txt");
		try {
			resultado = fichero.createNewFile();
			if (resultado)
				System.out.println("Archivo creado");
			else
				System.out.println("No se pudo crear el archivo");
		} catch (IOException e) {
			System.out.println("Se produjo el error: " + e.getMessage());
		}
		
		//Borrar el fichero
		resultado = fichero.delete();
		if(resultado)
			System.out.println("El fichero ha sido borrado");
		else
			System.out.println("No se ha podido eliminar el fichero");
		
		//Borrar directorio
		resultado = directorio.delete();
		if(resultado)
			System.out.println("El directorio ha sido borrado");
		else
			System.out.println("No se ha podido eliminar el directorio");
			
	}

}
