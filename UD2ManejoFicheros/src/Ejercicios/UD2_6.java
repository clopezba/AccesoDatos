package Ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UD2_6 {

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("No se ha encontrado el fichero o la palabra a buscar");
			System.exit(-1);
		}
		
		try {
			BufferedReader bf = new BufferedReader(new FileReader(args[0]));
			String linea;
			int cont=0;
			
			while((linea = bf.readLine())!= null) {
				int palabra = linea.toLowerCase().indexOf(args[1]);
				linea = linea.toLowerCase();
				while(palabra!=-1) {
					cont++;
					linea = linea.substring(palabra +args[1].length(), linea.length());
					palabra = linea.indexOf(args[1]);							
				}
			}
			System.out.println("La palabra '" + args[1] + "' aparece " + cont + " veces");
			bf.close();		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
