/*
Diseña un programa para gestionar las calificaciones de estudiantes en diferentes asignaturas durante varios trimestres.

El programa debe solicitar al usuario, validando siempre la entrada:
Número de estudiantes (2-20).
Número de asignaturas (3-8).
Número de trimestres (2 o 3).

Para almacenar las notas de cada estudiante en cada asignatura y trimestre utiliza un array tridimensional. 
Genera las calificaciones de forma aleatorias entre 0.0 y 10.0 (usa Math.random()).

El programa debe calcular y mostrar: 
- Nota media de cada estudiante en cada asignatura.
- Nota media de cada estudiante en el curso completo.
- Nota media de cada asignatura (todos los estudiantes).
- El estudiante con mejor nota media global.
- La asignatura con peor nota media.

Usar Scanner para entrada
Try-catch para validar
Mensajes de error con System.err
El programa NO debe cerrarse por errores
 */

package SistemaCalificacionesEstudiantes; //Indica el paquete en el que está el archivo

import java.util.Scanner; //Scanner para leer del teclado
import java.util.InputMismatchException; //Para poder usar el try-catch a modo de letras en vez de números

public class Parte1 {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        //Configuramos las variables a 0:
        int numEstudiantes = 0;
        int numAsignaturas = 0;
        int numTrimestres = 0;
        
        System.out.println("=== GESTIONADOR DE CALIFICACIONES DE ESTUDIANTES ===");
        
        do { //do-while para repetir la pregunta hasta que el dato sea válido
            try{ //Intentamos leer el número y comprobamos que no sean letras.
                System.out.print("Introuzca el número de estudiantes (2-20): ");
                numEstudiantes = teclado.nextInt();
                
                if (numEstudiantes < 2 || numEstudiantes > 20){
                    System.err.println("ERROR: Debes introducir un valor entre 2 y 20.");
                }
            } catch (InputMismatchException e){ //si el usuario escribe letras, salta el catch
                System.err.println("ERROR: No se trata de un número entero.");
                teclado.nextLine(); //limpiamos el buffer
            }
        } while (numEstudiantes < 2 || numEstudiantes > 20); //repetimos si está fuera de rango
        
        //Misma estructura de validación para las asignaturas
        do {
            try{
                System.out.print("Introduzca el número de asignaturas (3-8): ");
                numAsignaturas = teclado.nextInt();
                
                if (numAsignaturas < 3 || numAsignaturas > 8){
                    System.err.println("ERROR: Debes introducir un valor entre 3 y 8.");
                }
            } catch (InputMismatchException e){
                System.err.println("ERROR: No se trata de un número entero.");
                teclado.nextLine();
            }
        } while (numAsignaturas < 3 || numAsignaturas > 8);
        
        //Repetimos otra vez para los trimestres
        do {
            try{
                System.out.print("Introduza el número de trimestres (2 ó 3): ");
                numTrimestres = teclado.nextInt();
                
                if (numTrimestres != 2 && numTrimestres != 3){
                    System.err.println("ERROR: Debes introducir un valor que debe ser 2 ó 3.");
                }
            } catch (InputMismatchException e){
                System.err.println("ERROR: No se trata de un número entero.");
                teclado.nextLine();
            }
        } while (numTrimestres != 2 && numTrimestres != 3);
        
       // declaramos el array tridimensional
        double[][][] notas = new double[numEstudiantes][numAsignaturas][numTrimestres];
        
        //bucles for anidados para visitar cada rincón del array
        // [i](estudiantes)[j](asignaturas)[k](trismetres)
        for (int i = 0; i < numEstudiantes; i++){
            for (int j = 0; j < numAsignaturas; j++){
                for (int k = 0; k < numTrimestres; k++){
                    notas[i][j][k] = Math.random() * 10; //generamos nota aleatoria y multiplicamos x10 porque Math.random() te da un número del 0.0 al 1.0
                }
            }
        }
        
        //nota meia de cada estudiante en cada asignatura
        for (int i = 0; i < numEstudiantes; i++){
            for (int j = 0; j < numAsignaturas; j++){
                double suma = 0; //reiniciamos la suma para cada nueva asignatura
                for (int k = 0; k < numTrimestres; k++){
                    suma += notas[i][j][k]; //acumulamos las notas de los trimestres                     
                }                
                double media = suma / numTrimestres; //calculamos la media
                System.out.println("Estudiante "+ i + ", asignatura "+ j + " - media: "+media);                            
            }
        }
        
        //nota media global y mejor alumno
        double mejorNota = 0; //variable auxiliar para guardar la mejor nota
        int auxiliarAlumno = 0; //variable auxiliar para recordar qué alumno tiene la mejor nota
        
        for (int i = 0; i < numEstudiantes; i++){
            double sumaGlobal = 0; //acumulador para todas las notas del alumno
            for (int j = 0; j < numAsignaturas; j++){
                for (int k = 0; k < numTrimestres; k++){
                    sumaGlobal += notas[i][j][k]; //sumamos todo                  
                }              
            }
            //total de notas = asignaturas * trimestres
            int notasGlobal = numAsignaturas * numTrimestres;
            double mediaGlobal = sumaGlobal / notasGlobal;
            if (mediaGlobal > mejorNota){ //comprobamos si este alumno supera la mejor nota
                mejorNota = mediaGlobal; //actualizamos la mejor nota
                auxiliarAlumno = i; //guardamos el número del alumno
            }
            System.out.println("Estudiante "+ i + " - media global: "+mediaGlobal);
        }
        
        System.out.println("El alumno con mejor nota es el alumno "+auxiliarAlumno+", con una nota de "+mejorNota);
        
        //peor asignatura
        double peorNota = 10; //variable auxiliar con la nota máxima posible
        int auxiliarAsignatura = 0;
        
        for (int j = 0; j < numAsignaturas; j++){ //el bucle principal es j (asignaturas) porque queremos evaluar por materias
            double sumaAsignatura = 0;
            for (int i = 0; i < numEstudiantes; i++){ //recorremos todos los alumnos y trimestres para ESA asignatura
                for (int k = 0; k < numTrimestres; k++){
                    sumaAsignatura += notas[i][j][k];
                }                
            }
            //total de notas en ESA asignatura = alumnos * trimestres.
            int AsignaturasGlobal = numEstudiantes * numTrimestres;
            double mediaAsignatura = sumaAsignatura / AsignaturasGlobal;
            if (mediaAsignatura < peorNota){ //buscamos el mínimo
                peorNota = mediaAsignatura;
                auxiliarAsignatura = j;
            }
        }
        
        System.out.println("La asignatura con peor nota media es la asignatura " + auxiliarAsignatura + ", con una nota de " + peorNota);
        
    }
}
