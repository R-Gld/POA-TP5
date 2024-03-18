package fr.Rgld_.Strategies;

import fr.Rgld_.Card;
import fr.Rgld_.Player;
import fr.Rgld_.Strategy;

import java.util.Collection;

/**
 * <h2>Stratégie 2</h2>
 * <em>Extrait du <a href="https://url.rgld.fr/poa-sujet-td5">PDF de consigne</a>.</em>
 * <ul>
 *     <li>Pour le joueur qui commence le pli, on sélectionne dans tout son paquet la carte qui a le nombre de points maximum. Appelons cette carte <em>c1</em>. Appelons <em>couleur</em> la couleur de la carte de <em>c1</em>.</li>
 *     <li>L’autre joueur «répond» à la carte <em>c1</em> de la façon suivante:<br> S’il a dans son paquet, des cartes de couleur <em>couleur</em>, alors il cherche à mettre une carte la plus proche de <em>c1</em> ayant un nombre de points supérieur à la valeur des points de la carte <em>c1</em>. S’il n’a pas de carte supérieure de couleur <em>couleur</em>, alors il sélectionne sa plus petite carte de couleur <em>couleur</em>. S’il n’a pas de carte de couleur <em>couleur</em>, alors il sélectionne la plus petite carte de son paquet.</li>
 * </ul>
 */
public class Strategy2 extends Strategy {

    @Override
    public Result runStrategy(final Player premierPlayer, final Player secondPlayer) {
        Card c1 = getCardByMaxValue(premierPlayer);
        System.out.println("Le joueur " + premierPlayer.getName() + " pose la carte " + c1);
        Card.CardColor couleur = c1.getColor();
        int c1Point = c1.getValue().getPoint();

        Card c2;
        if(secondPlayer.getDeck().isColorPresent(couleur)) {
            if (c1Point != Card.Value.AS.getPoint()) { // Si c1 n'est pas un AS (carte avec les plus hauts points.
                /*

                On itère chaque carte de couleur `couleur`, si l'on en trouve une
                avec ses points > à `c1`, c'est la carte la plus proche de c1 avec
                des points supérieur. On joue celle-ci.

                 */
                for (Card c : secondPlayer.getDeck().getCards()) {
                    if (c.getColor() != couleur) continue;
                    int c2Point = c.getValue().getPoint();
                    if (c2Point > c1Point) {
                        System.out.println("Le joueur " + secondPlayer.getName() + " répond la carte " + c);
                        return Result.makeResult(premierPlayer, c1, secondPlayer, c);
                    }
                }

            }
            /*

            Si l'on arrive ici, c'est que j2 n'a pas de carte de points supérieur à
            `c1` et de même couleur que `couleur`. Dans ce cas, on prends la carte
            de couleur `couleur` ayant les points minimum.

            */
            c2 = secondPlayer.getDeck().getMinByColor(couleur);

        } else {
            c2 = secondPlayer.getDeck().getMinCard();
        }

        System.out.println("Le joueur " + secondPlayer.getName() + " répond la carte " + c2);

        return Result.makeResult(premierPlayer, c1, secondPlayer, c2);
    }

    /**
     * Retourne la carte ayant le nombre de points maximum.
     * @param player le joueur
     * @return la carte
     */
    private Card getCardByMaxValue(Player player) {
        Collection<Card> cards = player.getDeck().getCards();
        Card max = null;
        for(Card card : cards) {
            if(max == null || card.getValue().getPoint() > max.getValue().getPoint()) {
                max = card;
            }
        }
        return max;
    }

}
