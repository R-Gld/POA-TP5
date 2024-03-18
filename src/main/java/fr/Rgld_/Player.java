package fr.Rgld_;

import java.util.Scanner;

/**
 * Représente un joueur
 */
public class Player {

    private final String name;
    private final Deck deck;
    private int points;

    /**
     * Constructeur privé pour créer un nouveau joueur
     * @param name le nom du joueur
     * @param deck le paquet du joueur
     *
     * @see #createNewPlayer()
     */
    private Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.points = 0;
    }

    /**
     * Retourne le nom du joueur
     * @return le nom du joueur
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le paquet du joueur
     * @return le paquet du joueur
     *
     * @see fr.Rgld_.Game#distribute()
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Retourne les points du joueur
     * @return les points du joueur
     */
    public int getPoints() {
        return points;
    }

    /**
     * Ajoute des points au joueur
     * @param points les points à ajouter
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /* ********************************************** */

    /**
     * Méthode statique pour créer un nouveau joueur<br>
     * Créer un nouveau joueur
     * @return un nouveau joueur
     */
    public static Player createNewPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du joueur : ");
        String nom = scanner.nextLine();
        return new Player(nom, new Deck());
    }

}
