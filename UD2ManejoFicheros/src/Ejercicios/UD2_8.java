package Ejercicios;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class UD2_8 {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		File fichero = new File("Ficheros\\antiguedad_obj.dat");
		String nombre;
		double antiguedad;
		
		try {
					
			if(!fichero.exists()) {
				ObjectOutputStream escribirNuevo = new ObjectOutputStream(new FileOutputStream(fichero, true));
				System.out.println("Nombre: ");
				nombre = s.next();
				System.out.println("Antigüedad: ");
				antiguedad = s.nextDouble();
				escribirNuevo.writeObject(new Profesor(nombre, antiguedad));
				escribirNuevo.close();
			}
			MiObjectOutputStream escribir = new MiObjectOutputStream(new FileOutputStream(fichero, true));
			ObjectInputStream leer = new ObjectInputStream(new FileInputStream(fichero));
									
			for (int i = 0; i < 2; i++) {
				System.out.println("Nombre: ");
				nombre = s.next();
				System.out.println("Antigüedad: ");
				antiguedad = s.nextDouble();
				escribir.writeObject(new Profesor(nombre, antiguedad));
			}
			escribir.close();
			while(true) {
				System.out.println(leer.readObject());
			}
		} catch (EOFException eof) {
			System.out.println("--Fin del fichero--");
			s.close();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
