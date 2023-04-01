package src;

import java.io.*;
import java.util.Scanner;

public class actividadEvaluableUF3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Cambiar ruta dependiendo de tu ubicacion
        String ruta = "C:\\Users\\izhan\\OneDrive\\Escritorio\\MP03_UF03_Lara_Izhan\\src\\usuarios\\usuarios.txt";
        String opcion;
        boolean creado;
        String linea;
        boolean exit = false;

        menu();
        opcion = sc.nextLine();
        creado = crearFichero(ruta);

        while (!exit) {
            menu();
            opcion = sc.nextLine();
            String opcionM = opcion.toUpperCase();
            creado = crearFichero(ruta);

            switch (opcionM) {
                case "A":
                    if (creado) {
                        System.out.println("Fichero creado en la ruta: " + ruta);
                    } else {
                        System.out.println("No se ha podido crear el fichero en la ruta: " + ruta);
                    }
                    break;
                case "B":
                    if (creado) {
                        System.out.println(
                                "Escribe lo que quieres introducir en el fichero [EOF para finalizar la edición]: ");
                        linea = sc.nextLine();
                        while (!linea.equals("EOF")) {
                            escribirFichero(ruta, linea);
                            System.out.println(
                                    "Escribe lo que quieres introducir en el fichero [EOF para finalizar la edición]: ");
                            linea = sc.nextLine();
                        }
                    } else {
                        System.out.println("No se ha podido escribir en el fichero, porque no se ha creado correctamente.");
                    }
                    break;
                case "C":
                    if (creado) {
                        leerFichero(ruta);
                    } else {
                        System.out.println("No se puede leer un fichero que no existe.");
                    }
                    break;
                case "D":
                    eliminarFichero(ruta);
                    break;
                case "E":
                    System.out.println("Hasta la próxima!");
                    exit = true;
                    break;
                default:
                    System.out.println("La opción seleccionada no es correcta.");
                    break;
            }
        }

        sc.close();
    }

    // Función para crear un fichero en la ruta especificada
    public static boolean crearFichero(String ruta) {
        File f = new File(ruta);
        boolean creado = false;
        try {
            creado = f.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return creado;
    }

    // Función para escribir una línea en el fichero en la ruta especificada
    public static void escribirFichero(String ruta, String linea) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(ruta, true);
            bw = new BufferedWriter(fw);
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Función para leer el contenido de un fichero en la ruta especificada
    public static void leerFichero(String ruta) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Función para eliminar un fichero en la ruta especificada
    public static void eliminarFichero(String ruta) {
        File f = new File(ruta);
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Está seguro que desea eliminar el fichero en la ruta " + ruta + "? (S/N)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            if (f.delete()) {
                System.out.println("Fichero eliminado en la ruta: " + ruta);
            } else {
                System.out.println("No se ha podido eliminar el fichero en la ruta: " + ruta);
            }
        } else {
            System.out.println("El fichero no ha sido eliminado.");
        }
    }

    // Función para mostrar el menú de opciones
    public static void menu() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("--------------");
        System.out.println("A - Crear fichero");
        System.out.println("B - Escribir en fichero");
        System.out.println("C - Leer fichero");
        System.out.println("D - Eliminar fichero");
        System.out.println("E - Salir");
        System.out.println("--------------");
        System.out.println("Selecciona una opción: ");
    }
}
