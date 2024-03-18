package fr.Rgld_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Représente un Paquet de 16 cartes (ensemble des cartes d'un joueur).
 */
public class Deck {

    private final List<Card> cards;

    /**
     * Constructeur pour créer un nouveau paquet
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Constructeur pour créer un nouveau paquet avec des cartes
     * @param cards les cartes
     */
    public Deck(List<Card> cards) {
        if(cards.size() != 16) throw new IllegalArgumentException("Le paquet doit contenir 16 cartes");
        this.cards = cards;
    }

    /**
     * Retourne les cartes du paquet
     * @return les cartes du paquet
     */
    public Collection<Card> getCards() {
        return cards;
    }

    /**
     * Ajoute une carte au paquet
     * @param card la carte à ajouter
     */
    public void addCard(Card card) {
        if(cards.size() == 16) throw new IllegalStateException("Le paquet est plein");
        cards.add(card);
    }

    /**
     * Retire une carte du paquet
     * @param card la carte à retirer
     *
     * @see fr.Rgld_.Strategy.Result#apply()
     */
    public void removeCard(Card card) {
        if(!cards.contains(card)) throw new IllegalArgumentException("La carte n'est pas dans le paquet");
        cards.remove(card);
    }

    /**
     * Retourne les couleurs des cartes du paquet
     * @return les couleurs des cartes du paquet
     *
     * @see fr.Rgld_.Strategies.Strategy1#runStrategy(Player, Player)
     */
    public Card.CardColor[] getColors() {
        List<Card.CardColor> colors = new ArrayList<>();
        for(Card card : cards) {
            if(!colors.contains(card.getColor())) {
                colors.add(card.getColor());
            }
        }
        return colors.toArray(new Card.CardColor[0]);
    }

    /**
     * Retourne les cartes d'une couleur
     * @param color la couleur
     * @return les cartes de la couleur
     */
    public Collection<Card> getCardsByColor(Card.CardColor color) {
        List<Card> cards = new ArrayList<>();
        for(Card card : this.cards) {
            if(card.getColor() == color) {
                cards.add(card);
            }
        }
        return cards;
    }

    /**
     * Retourne si une couleur est présente dans le paquet
     * @param color la couleur
     * @return si la couleur est présente
     *
     * @see fr.Rgld_.Strategies.Strategy1#runStrategy(Player, Player)
     * @see fr.Rgld_.Strategies.Strategy2#runStrategy(Player, Player)
     */
    public boolean isColorPresent(Card.CardColor color) {
        for(Card card : cards) {
            if(card.getColor() == color) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne la carte ayant le nombre de points minimum
     * @return la carte
     *
     * @see fr.Rgld_.Strategies.Strategy1#runStrategy(Player, Player)
     * @see fr.Rgld_.Strategies.Strategy2#runStrategy(Player, Player)
     */
    public Card getMinCard() {
        Card min = cards.get(0);
        for(Card card : cards) {
            if(card.getValue().getPoint() < min.getValue().getPoint()) {
                min = card;
            }
        }
        return min;
    }

    /**
     * Retourne la carte ayant le nombre de points minimum d'une couleur
     * @param color la couleur
     * @return la carte
     *
     * @see fr.Rgld_.Strategies.Strategy2#runStrategy(Player, Player)
     */
    public Card getMinByColor(Card.CardColor color) {
        Card min = cards.get(0);
        for(Card card : cards) {
            if(card.getColor() != color) continue;
            if(card.getValue().getPoint() < min.getValue().getPoint()) {
                min = card;
            }
        }
        return min;
    }

    /**
     * Trie les cartes d'un deck de carte selon leur valeur et leur couleur
     * @see Card#compareTo(Card)
     */
    public void order() {
        Collections.sort(cards);
    }

}
