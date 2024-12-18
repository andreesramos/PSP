import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyHilo hilo = new MyHilo();
        Scanner scanner = new Scanner(System.in);
        boolean hiloIniciado = false; // Booleano para iniciar el hilo una sola vez

        while (true) {
            System.out.print("Introduce un comando (S=suspender, R=reanudar, *=salir, cualquier otra para incrementar): ");
            String comando = scanner.nextLine();

            if (comando.equals("*")) {
                hilo.finalizar(); // Llama al metodo de Myhilo para finalizar el hilo
                break;
            } else if (comando.equalsIgnoreCase("S")) {
                hilo.Suspende(); //Llama al metodo de Myhilo para suspender el hilo
                System.out.println("Hilo suspendido.");
            } else if (comando.equalsIgnoreCase("R")) {
                hilo.Reanuda(); //Llama al metodo de Myhilo para reanudar el hilo
                System.out.println("Hilo reanudado.");
            } else {
                // Verificar si el hilo está suspendido
                if (!hilo.incrementarContador()) {
                    System.out.println("El hilo está suspendido. Introduzca otra opción.");
                }
            }

            // Iniciar el hilo solo la primera vez que se introduce un comando válido
            if (!hiloIniciado) {
                hilo.start();
                hiloIniciado = true;
            }
        }

        try {
            hilo.join(); // Esperar a que el hilo termine
        } catch (InterruptedException e) {
            System.out.println("El hilo principal ha sido interrumpido.");
        }

        // Mostrar el valor final del contador
        System.out.println("Valor final del contador: " + hilo.getContador());
        scanner.close();
    }
}



