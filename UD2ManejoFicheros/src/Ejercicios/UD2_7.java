package Ejercicios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class UD2_7 {

	public static void main(String[] args) {
		int num, cont1=0, cont2=0, cont3=0, cont4=0, cont5=0;
		try {
			DataOutputStream escribir = new DataOutputStream(
					new FileOutputStream("Ficheros\\puntuacion.dat"));
			
			for (int i = 0; i < 20; i++) {
				escribir.writeInt((int) (Math.random()*5)+1);
			}
			DataInputStream leer = new DataInputStream(new FileInputStream("Ficheros\\puntuacion.dat"));
			for(int i=0; i<20; i++) {
				num = leer.readInt();
				System.out.print(num + " ");
				if(num == 1)
					cont1++;
				if(num == 2)
					cont2++;
				if(num == 3)
					cont3++;
				if(num == 4)
					cont4++;
				if(num == 5)
					cont5++;
			}
			escribir.close();leer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nRepeticiones de cada número: ");
		System.out.println("1: " + cont1 + " veces"); 
		System.out.println("2: " + cont2 + " veces");
		System.out.println("3: " + cont3 + " veces");
		System.out.println("4: " + cont4 + " veces");
		System.out.println("5: " + cont5 + " veces");
	}

}
