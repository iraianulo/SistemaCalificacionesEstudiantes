/*
Escribe un programa que solicite al usuario introducir una frase y realice las siguientes operaciones:

- Contar caracteres: Mostrar el número total de caracteres de la frase (incluyendo espacios).
- Contar palabras: Indicar cuántas palabras contiene la frase.
- Invertir palabras: Mostrar la frase con el orden de las palabras invertido.
- Reemplazar vocales: Sustituir todas las vocales (mayúsculas y minúsculas, con o sin tilde) por el carácter *.

Si la frase incluye un número, hacer:
- Convertirlo a tipo int y mostrar su cuadrado.
- Convertirlo a tipo float y calcular su raíz cuadrada.
- Finalmente, convertir el número entero de nuevo a texto y añadirle el sufijo: " es el número procesado".

Usar Scanner para entrada
Try-catch para validar
Mensajes de error con System.err
El programa NO debe cerrarse por errores
*/

package SistemaCalificacionesEstudiantes;

import java.util.Scanner;

public class Parte2 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        String frase;
        
        System.out.print("Introduce una frase: ");
        frase = teclado.nextLine(); //nextLine() para leer la frase completa con espacios
        
        int caracteres = frase.length(); //.length() devuelve el total de caracteres (espacios incluidos)
        System.out.println("Tu frase tiene "+caracteres+" carateres.");
        
        //contar palabras
        int numPalabras = frase.split(" ").length; //.split(" ") corta la frase por los espacios y crea un array con los trozos y .legth (sin paréntesis) nos dice cuántos trozos (palabras) hay en el array
        
        System.out.println("Tu frase tiene "+numPalabras+" palabras.");
        
        //guardamos el arra de palabras
        String[] palabras = frase.split(" ");
        
        //Invertir palabras
        System.out.println("Tu palabra escrita al revés sería: ");
        for (int i = palabras.length - 1; i >= 0; i--){ //recorremos el array desde el final hasta el principio
            System.out.print(palabras[i]+ " "); //imprimimos sin salto de línea, para que quede seguido y separado por un espacio
        }
        
        System.out.println(""); //salto de línea para que no quede todo junto.
        
        //reemplazar vocales
        System.out.println("Tu palabra con las vocale reemplazadas por un asterisco sería así: "+frase.replaceAll("[AÁaáEÉeéIÍiíOÓoóUÚuú]", "*")); //.replaceAll("[expresión regular]", "reemplazo")
        
        //Buscar y procesar números en la frase
        for (int i = 0; i < palabras.length; i++){ //recorremos palabra por palabra para ver si alguna es un número
            try {
                int numInt = Integer.parseInt(palabras[i]); //intentamos convertir la palabra actual (palabras[i]) a entero
                //si es un número, continuamos con los cálculos
                int cuadrado = numInt * numInt;
                System.out.println("El cuadrado de tu número es: "+cuadrado);
                float numFloat = Float.parseFloat(palabras[i]); //convertimos a float
                System.out.println("La raíz cuadrada de tu número es: "+Math.sqrt(numFloat)); //Math.sqrt calcula la raíz
                String numATexto = Integer.toString(numInt); //convertimos el entero de nuevo a texto.
                System.out.println(numATexto+" es el número procesado.");
            }catch (NumberFormatException e){ //por cada palabra que no sea un número, salta al catch, que está vacío para que no imprima un mensaje de error por cada palabra
                
            }
        }       
    }

}
