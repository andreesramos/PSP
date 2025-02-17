package Ejercicio1;/* Tengo que crear una clase Ejercicio1.Cuenta compratida por varias personas que se va a encargar de los movimientos de dinero
* y otra clase Ejercicio1.Persona que en este caso será el hilo, la cual se encargará de ingresar y retirar dinero de la cuenta.
* Los métodos de la cuenta estarán sincronizados para poder compartirlo entre las personas.
* He añadido un sleep grande para cada que las operaciones se ejecuten en el orden deseado
* El código funciona, ejecuta los ingresos y las retiradas de las personas y despues imprime el saldo final.
* El unico problema es que me imprime el saldo final despues de que terminene las opceraciones de cada persona, pero
* aún así el saldo de la cuenta lo comparten.*/


public class Ejercicio1 {
    public static void main(String[] args) {
        // Creacion del objeto Ejercicio1.Cuenta con saldo inicial 0 y saldo máximo
        Cuenta cuenta = new Cuenta(0, 1000);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());

        // Creacion de los objetos personas con su nombre y la cuenta compartida
        Persona persona1 = new Persona("Andres", cuenta);
        Persona persona2 = new Persona("Julio", cuenta);

        persona1.start();
        persona2.start();
    }
}