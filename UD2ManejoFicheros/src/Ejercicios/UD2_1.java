package Ejercicios;

import java.io.File;

public class UD2_1 {

	public static void main(String[] args) {
		 File f = new File("C:\\WINDOWS");
		 File[] archivos = f.listFiles();
		 
		 for (File archivo : archivos) {
			if(archivo.isFile() && archivo.isHidden()) {
				System.out.println("Nombre: " + archivo.getName());
				System.out.println("Longitud: " + archivo.length());
				System.out.println("Se puede leer: " + archivo.canRead());
				System.out.println("Se puede escribir: " + archivo.canWrite());
			}
		}

	}

}
