package fr.Rgld_;

/**
 * Classe abstraite pour les stratégies
 */
public abstract class Strategy {

    /**
     * Méthode abstraite pour exécuter une stratégie
     * @param premierPlayer le premier joueur
     * @param secondPlayer le second joueur
     * @return le résultat de la stratégie
     */
    public abstract Result runStrategy(Player premierPlayer, Player secondPlayer);

    /**
     * Classe pour le résultat d'une stratégie
     */
    public static class Result {
        Card card;
        Player player;

        /**
         * Constructeur privé pour créer un résultat
         * @param card la carte
         * @param player le joueur
         */
        private Result(Card card, Player player) {
            this.card = card;
            this.player = player;
        }

        /**
         * Retourne la carte
         * @return la carte
         */
        public Card getCard() {
            return card;
        }

        /**
         * Retourne le joueur
         * @return le joueur
         */
        public Player getPlayer() {
            return player;
        }

        /**
         * Applique le résultat
         * càd: retire la carte du paquet du joueur et ajoute les points correspondants
         */
        public void apply() {
            player.getDeck().removeCard(card);
            System.out.println("Le joueur " + player.getName() + " gagne " + card.getValue().getPoint() + " points. Il en a maintenant " + (player.getPoints() + card.getValue().getPoint()) + ".");
            player.addPoints(card.getValue().getPoint());
        }

        @Override
        public String toString() {
            return "Le joueur " + player.getName() + " gagne le pli.";
        }

        /**
         * Méthode statique pour créer un résultat
         * @param player1 le premier joueur
         * @param card1 la carte du premier joueur
         * @param player2 le second joueur
         * @param card2 la carte du second joueur
         * @return le résultat
         */
        public static Result makeResult(Player player1, Card card1, Player player2, Card card2) {
            if(card1.getColor() == card2.getColor()) {
                if(card1.getValue().getPoint() > card2.getValue().getPoint()) {
                    return new Result(card1, player1);
                } else {
                    return new Result(card2, player2);
                }
            } else {
                return new Result(card1, player1);
            }
        }
    }

}
