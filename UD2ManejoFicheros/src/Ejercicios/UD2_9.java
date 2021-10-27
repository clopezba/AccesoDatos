package Ejercicios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class UD2_9 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		File archivo = new File("Ficheros\\ProfesFPSierraGuara.dat");
		try {
			RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
			System.out.println("Indica el ID del profesor que deseas borrar: ");
			int id = s.nextInt();
			
			int posicion = (id-1)*56;
			if(posicion > raf.length()-56) {
				System.out.println("No existe un empleado con ese identificador");
				System.exit(-1);
			}
			raf.seek(posicion);
			id = raf.readInt();
			if(id==0) {
				System.out.println("El profesor con el ID indicado ya ha sido borrado");
				System.exit(-1);
			}
			else {
				raf.seek(raf.getFilePointer()-4);
				raf.writeInt(0);
			}
			
			int departamento;
			double antiguedad;
			char profesores[]= new char[20];
			System.out.println("Id   Nombre          Departamento   Antigüedad");
			System.out.println("----------------------------------------------");
			raf.seek(0);
			while(raf.getFilePointer()!=raf.length()) {
				id = raf.readInt();
				for (int i = 0; i < profesores.length; i++) {
					profesores[i]=raf.readChar();
				}
				String profesor = new String(profesores);
				departamento = raf.readInt();
				antiguedad = raf.readDouble();
				System.out.println(id + "   " + profesor + " " + departamento + "             " 
						+ antiguedad);
			}
			raf.close();s.close();
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	/*El identificador del profesor esté dentro de los límites del fichero.
	- El identificador del profesor debe existir. Si ha sido borrado previamente se advertirá de
	la situación.
	- Antes de finalizar el código visualizar de manera secuencial todos los registros del
	fichero para comprobar la operación.*/
	
}
