import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Clase Deck
class Deck {
    private List<Card> cartas;

    // Constructor que inicializa las 52 cartas
    public Deck() {
        cartas = new ArrayList<>();
        String[] palos = { "Tréboles", "Corazones", "Picas", "Diamantes" };
        String[] colores = { "Negro", "Rojo" };
        String[] valores = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

        // Crear las cartas y añadirlas al mazo
        for (String palo : palos) {
            String color = (palo.equals("Corazones") || palo.equals("Diamantes")) ? "Rojo" : "Negro";
            for (String valor : valores) {
                cartas.add(new Card(palo, color, valor));
            }
        }
    }

    //Clase Card
    public class Card {
        //Atributos
        private String palo;
        private String color;
        private String valor;

        //Constructor
        public Card(String palo, String color, String valor) {
            if (!isValidPalo(palo) || !isValidColor(color) || !isValidValor(valor)) {
                throw new IllegalArgumentException("Palo, color o valor no son válidos.");
            }
            this.palo = palo;
            this.color = color;
            this.valor = valor;
        }

        //Métodos para validar los atributos
        private boolean isValidPalo(String palo) {
            return palo.equals("Tréboles") || palo.equals("Corazones") || palo.equals("Picas") || palo.equals("Diamantes");
        }

        private boolean isValidColor(String color) {
            return color.equals("Rojo") || color.equals("Negro");
        }

        private boolean isValidValor(String valor) {
            return valor.equals("A") || valor.equals("J") || valor.equals("Q") || valor.equals("K") ||
                    (valor.matches("[2-9]|10"));
        }

        //Metodos getter
        public String getPalo() {
            return palo;
        }

        public String getColor() {
            return color;
        }

        public String getValor() {
            return valor;
        }

        //Metodo toString para representar la carta
        @Override
        public String toString() {
            return valor + " de " + palo + " (" + color + ")";
        }
    }

    //Metodo para mezclar las cartas
    public void shuffle() {
        if (cartas.isEmpty()) {
            System.out.println("No quedan cartas para mezclar.");
            return;
        }
        Collections.shuffle(cartas);
        System.out.println("Se mezclo el Deck.");
    }

    //Metodo para obtener la primera carta (Head)
    public Card head() {
        if (cartas.isEmpty()) {
            System.out.println("No quedan cartas en el mazo.");
            return null;
        }
        Card primeraCarta = cartas.remove(0);
        System.out.println("Carta obtenida: " + primeraCarta); //Muestra la primera carta
        System.out.println("Quedan: " + cartas.size() + " cartas"); //Muestra las cartas restantes
        return primeraCarta;
    }

    //Metodo para obtener una carta aleatoria (Pick)
    public Card pick() {
        if (cartas.isEmpty()) {
            System.out.println("No quedan cartas en el mazo.");
            return null;
        }
        Random random = new Random();
        Card cartaAleatoria = cartas.remove(random.nextInt(cartas.size()));
        System.out.println("Carta aleatoria obtenida: " + cartaAleatoria);
        System.out.println("Quedan: " + cartas.size() + " cartas");
        return cartaAleatoria;
    }

    //Metodo para obtener una mano de 5 cartas (Hand)
    public List<Card> hand() {
        if (cartas.size() < 5) {
            System.out.println("No hay suficientes cartas en el mazo.");
            return null;
        }
        List<Card> mano = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mano.add(cartas.remove(0));
        }
        System.out.println("Mano obtenida: " + mano);
        System.out.println("Quedan: " + cartas.size() + " cartas");
        return mano;
    }

    //Metodo main para probar el mazo y las cartas
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Deck deck = new Deck();
        int number;

        System.out.println("\nBienvenido a tu juego de Poker!");
        do {
            System.out.println("\nElige una de las siguientes opciones: 1.Shuffle 2.Head 3.Pick 4.hand 0.Salir");
            System.out.print("Inserta la opcion deseada: ");
            number = reader.nextInt();

            switch (number) {
                case 1:
                    deck.shuffle();
                    break;
                case 2:
                    deck.head();
                    break;
                case 3:
                    deck.pick();
                    break;
                case 4:
                    deck.hand();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while (number != 0);
    }
}