package fr.Rgld_.Strategies;

import fr.Rgld_.Card;
import fr.Rgld_.Player;
import fr.Rgld_.Strategy;

/**
 * <h2>Stratégie 1</h2>
 * <em>Extrait du <a href="https://url.rgld.fr/poa-sujet-td5">PDF de consigne</a>.</em>
 * <ul>
 *     <li>Pour le joueur qui commence le pli:<br> on tire au hasard une couleur de son paquet, puis on sélectionne parmi les cartes de cette couleur celle ayant le nombre de points maximum. Appelons cette carte <em>c1</em>. Appelons <em>couleur</em> la couleur de la carte de <em>c1</em>. Pour commenter cette sélection, on dit que le joueur pose la carte <em>c1</em>.</li>
 *     <li>L’autre joueur «répond» à la carte c1de la façon suivante:<br> S’il a dans son paquet, des cartes de couleur <em>couleur</em>, alors la carte sélectionnée est tirée au hasard parmi les cartes de cette couleur. S’il n’a pas de carte de couleur <em>couleur</em>, alors il sélectionne la plus petite carte de son paquet.</li>
 * </ul>
 */
public class Strategy1 extends Strategy {

    @Override
    public Result runStrategy(final Player premierPlayer, final Player secondPlayer) {
        Card.CardColor[] fstColors = premierPlayer.getDeck().getColors();
        // on tireau hasard une couleur de son paque
        Card.CardColor couleur = fstColors[(int) (fstColors.length * Math.random())];
        // on sélectionne parmi les cartes de cette couleur celleayant le nombre de points maximum.
        Card c1 = getCardByColorByMaxValue(premierPlayer, couleur);
        System.out.println("Le joueur " + premierPlayer.getName() + " pose la carte " + c1);

        if(secondPlayer.getDeck().isColorPresent(couleur)) {
            // `S’il a dans son paquet, des cartes de couleur "couleur", alors la carte sélectionnée est tirée au hasard parmi les cartes de cette couleur`
            Card c2 = getCardByColorByRandom(secondPlayer, couleur);
            System.out.println("Le joueur " + secondPlayer.getName() + " répond la carte " + c2);
            return Result.makeResult(premierPlayer, c1, secondPlayer, c2);
        } else {
            // `S’il n’a pas de carte de couleur couleur, alors il sélectionne la plus petite carte de son paquet`
            Card c2 = secondPlayer.getDeck().getMinCard();
            System.out.println("Le joueur " + secondPlayer.getName() + " répond la carte " + c2);
            return Result.makeResult(premierPlayer, c1, secondPlayer, c2);
        }
    }

    /**
     * Retourne la carte de couleur `color` ayant le nombre de points maximum.
     * @param player le joueur
     * @param color la couleur
     * @return la carte
     */
    private Card getCardByColorByMaxValue(final Player player, final Card.CardColor color) {
        int max = 0;
        Card maxCard = null;
        for(Card card : player.getDeck().getCardsByColor(color)) {
            if(card.getValue().getPoint() > max) {
                max = card.getValue().getPoint();
                maxCard = card;
            }
        }
        return maxCard;
    }

    /**
     * Retourne une carte de couleur `color` tirée au hasard.
     * @param player le joueur
     * @param color la couleur
     * @return la carte
     */
    private Card getCardByColorByRandom(final Player player, final Card.CardColor color) {
        Card[] cards = player.getDeck().getCardsByColor(color).toArray(new Card[0]);
        return cards[(int) (cards.length * Math.random())];
    }
}
