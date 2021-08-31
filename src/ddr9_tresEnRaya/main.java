package ddr9_tresEnRaya;

import java.util.Scanner;

//https://www.youtube.com/watch?v=ulDLbSIY0jY
//empezado  y finalizado: 30-8-21

/*
 * Para obtener el número de filas de la matriz, podemos recurrir a la propiedad “length” de los arrays, de la siguiente manera:

int filas = matriz.length;

 

Para el caso del número de columnas sería de la siguiente forma :

int columnas = matriz[0].length;
 * 
 */

public class main {

	static Scanner teclado = new Scanner(System.in); // se pone en globar porq lo utilizamos en varios sitios

	static char J1 = 'X';
	static char J2 = 'O';

	public static void main(String[] args) {
		jugar();

	}

	private static void jugar() {

		char vacio = '-';
		boolean turno = true; // un turno true para uno, y el false para el otro
		char tablero[][] = new char[3][3];

		rellenarMatriz(tablero, vacio); // inicio el tablero con los guiones
		int fila, columna;
		boolean postValida, correcto;

		while (!finPartida(tablero, vacio)) {

			// todo esto es para validar la casilla

			do {

				mostrarTurno(turno);
				mostrarMatriz(tablero);

				fila = pedirInteger("Introduce el numero de fila");
				correcto = false;

				columna = pedirInteger("Introduce el numero de columna");

				postValida = validarPosicion(tablero, fila, columna);

				if (postValida) {

					if (!hayValorPosicion(tablero, fila, columna, vacio)) {

						correcto = true;

					} else {
						for (int i = 0; i < 10; ++i) System.out.println();
						System.out.println("la posicion elegida ya está ocupada, elige otra nueva");

					}

				} else {
					for (int i = 0; i < 10; ++i) System.out.println();
					System.out.println("La posicion introducida esta fuera del tablero");

				}

			} while (!correcto); // hasta que no sea correcto no acaba

			if (turno) {
				insertarEn(tablero, fila, columna, J1);

			} else {
				insertarEn(tablero, fila, columna, J2);
			}

			turno = !turno; // para cambiar de jugador

		}
		mostrarMatriz(tablero);
		mostrarGanador(tablero, J1, J2, vacio);

	}  //jugar

	public static void mostrarTurno(boolean turno) {
		if (turno) {
			System.out.println("le toca al jugador 1" + "(" + J1 + ")");
		} else {
			System.out.println("le toca al jugador 2" + "(" + J2 + ")");
		}

	}

	private static void mostrarMatriz(char[][] matriz) {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println("");
		}

	}


	

	private static void rellenarMatriz(char[][] matriz, char simbolo) {

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = simbolo;
			}

		}
	}

	public static int pedirInteger(String mensaje) {

		System.out.println(mensaje);
		int numero = teclado.nextInt();

		return numero;

	}

	public static boolean validarPosicion(char[][] tablero, int fila, int columna) {

		// la posicion esta dentro del rango del tablero
		if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {

			return true;
		}
		return false;
	}

	public static boolean hayValorPosicion(char[][] matriz, int fila, int columna, char simboloDef) {

		if (matriz[fila][columna] != simboloDef) {

			return true;
		}
		return false; // no hay ninguna ficha puesta y se puede sustituir

	}

	public static void insertarEn(char[][] matriz, int fila, int columna, char simbolo) {

		matriz[fila][columna] = simbolo;

	}

	public static boolean matrizLlena(char[][] matriz, char simboloDef) {
		for (int i = 0; i < matriz.length; i++) {  //tamaño filas
			for (int j = 0; j < matriz[0].length; j++) {  //tamaño columnas
				if (matriz[i][j] == simboloDef) {
					return false; // hay todavia alguna posicion con guion q 
									//se puede rellenar
				}
			}

		}
		
		return true; // esta lleno con fichas de los jugadores
		

	}


	
	
	public static boolean finPartida(char[][] matriz, char simboloDef) {

	
	//la partida acaba cuando se da alguna de estas 4 opciones
		if (matrizLlena(matriz, simboloDef) 
				|| coincidenciaLinea(matriz, simboloDef) != simboloDef
				|| coincidenciaColumna(matriz, simboloDef) != simboloDef
				|| coincidenciaDiagonal(matriz, simboloDef) != simboloDef) {
		
			return true;
	
		}

		return false;

	}

	public static char coincidenciaLinea(char[][] tablero, char simboloDef) {

	
		char simbolo;
		boolean coincidencia;

		for (int i = 0; i < tablero.length; i++) {
			coincidencia = true;
			simbolo = tablero[i][0];

			if (simbolo != simboloDef) { // si es distinto al simbolo por defecto continua

				for (int j = 1; j < tablero[0].length; j++) {
					if (simbolo != tablero[i][j]) {
						coincidencia = false;
					}

				}
				if (coincidencia) { // es decir no se ha cambaido
					return simbolo;

				}

			}

		} // for


		return simboloDef;
		

	}

	public static char coincidenciaColumna(char[][] tablero, char simboloDef) {
		System.out.println("columna");
		char simbolo;
		boolean coincidencia;

		for (int j = 0; j < tablero.length; j++) {
			coincidencia = true;
			simbolo = tablero[0][j];

			if (simbolo != simboloDef) { // si es distinto al simbolo por defecto continua

				for (int i = 1; i < tablero[0].length; i++) {
					if (simbolo != tablero[i][j]) {
						coincidencia = false;
					}

				}
				if (coincidencia) { // es decir no se ha cambaido
					return simbolo;

				}

			}

		} // for

		return simboloDef;

	}

	public static char coincidenciaDiagonal(char[][] matriz, char simboloDef) {

		System.out.println("diagonal");
		char simbolo;
		boolean coincidencia = true;

		// diagonal principal

		simbolo = matriz[0][0];
		if (simbolo != simboloDef) { // si es distinto al simbolo por defecto continua

			for (int i = 1; i < matriz.length; i++) {
				if (simbolo != matriz[i][i]) {
					coincidencia = false;
				}
			}
			if (coincidencia) {
				return simbolo;
			}

		}

		simbolo = matriz[0][2];
		if (simbolo != simboloDef) { // si es distinto al simbolo por defecto continua

			for (int i = 1, j = 1; i < matriz.length; i++, j--) {
				if (simbolo != matriz[i][j]) {
					coincidencia = false;
				}
			}
			if (coincidencia) {
				return simbolo;
			}

		}

		return simboloDef;

	}

	public static void mostrarGanador(char[][] matriz, char J1, char J2, char simboloDef) {

		char simbolo = coincidenciaLinea(matriz, simboloDef);

		if (simbolo != simboloDef) {
			if (simbolo == J1) {
				System.out.println("ha ganado el jugador 1 por linea");
			} else {
				System.out.println("ha ganado el jugador 2 por linea");
			}
		}

		simbolo = coincidenciaColumna(matriz, simboloDef);

		if (simbolo != simboloDef) {
			if (simbolo == J1) {
				System.out.println("ha ganado el jugador 1 por columna");
			} else {
				System.out.println("ha ganado el jugador 2 por columna");
			}
		}

		simbolo = coincidenciaDiagonal(matriz, simboloDef);

		if (simbolo != simboloDef) {
			if (simbolo == J1) {
				System.out.println("ha ganado el jugador 1 por diagonal");
			} else {
				System.out.println("ha ganado el jugador 2 por diagonal");
			}
		}

	}

}  //end class
