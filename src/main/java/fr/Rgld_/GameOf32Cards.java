package fr.Rgld_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Représente un Paquet de 32 cartes.
 */
public class GameOf32Cards {

    private final List<Card> cards;

    /**
     * Retourne les cartes du jeu
     * @return les cartes du jeu
     */
    public Collection<Card> getCartes() {
        return cards;
    }

    /**
     * Constructeur privé pour créer un jeu de 32 cartes
     */
    private GameOf32Cards() {
        cards = new ArrayList<>();
        for (Card.CardColor color : Card.CardColor.values()) {
            for (Card.Value value : Card.Value.values()) {
                cards.add(new Card(value, color));
            }
        }
    }

    /**
     * Mélange les cartes du jeu
     */
    public void melanger() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        String res = "Jeu de 32 cartes:\n";
        for(Card card : cards) {
            res += "\t" + card + "\n";
        }
        return res;
    }

    /**
     * Créer un nouveau jeu de 32 cartes
     * @return un jeu de 32 cartes
     */
    public static GameOf32Cards nouveauJeu32Cartes() {
        return new GameOf32Cards();
    }

    /**
     * Créer un nouveau jeu de 32 cartes et le mélanger
     * @return un jeu de 32 cartes mélangé
     */
    public static GameOf32Cards nouveauJeu32CartesMelanger() {
        GameOf32Cards jeu = new GameOf32Cards();
        jeu.melanger();
        return jeu;
    }

}
