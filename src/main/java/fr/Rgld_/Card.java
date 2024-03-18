package fr.Rgld_;

/**
 * Représente une carte
 */
public class Card implements Comparable<Card> {

    private final Value value;
    private final CardColor color;

    /**
     * Crée une carte avec une valeur et une couleur
     * @param value la valeur de la carte
     * @param color la couleur de la carte
     */
    public Card(Value value, CardColor color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Renvoie la valeur de la carte
     * @return la valeur de la carte
     */
    public Value getValue() {
        return value;
    }

    /**
     * Renvoie la couleur de la carte
     * @return la couleur de la carte
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Renvoie la valeur et la couleur de la carte sous forme de chaîne de caractères<br>
     * Exemples : "7♥", "10♦", "Valet♠", "Roi♣", ...
     * @return la valeur et la couleur de la carte sous forme de chaîne de caractères
     */
    public String toString() {
        String value = switch (this.value) {
            case SEPT -> "7";
            case HUIT -> "8";
            case NEUF -> "9";
            case DIX -> "10";
            case VALET -> "Valet";
            case DAME -> "Dame";
            case ROI -> "Roi";
            case AS -> "As";
        };

        String color = switch (this.color) {
            case COEUR -> "♥";
            case CARREAU -> "♦";
            case PIQUE -> "♠";
            case TREFLE -> "♣";
        };
        return value + color;
    }

    /**
     * Renvoie la valeur de la carte multipliée par 100 et additionnée à la valeur de la couleur
     * @return la valeur de la carte multipliée par 100 et additionnée à la valeur de la couleur
     */
    public int getOrderValue() {
        return color.getOrderValue() * 100 + value.getPoint();
    }

    /**
     * Compare deux cartes en fonction de leur valeur et de leur couleur
     * @param card la carte à comparer
     * @return un entier négatif, 0 ou positif si la carte est plus petite, égale ou plus grande que la carte passée en paramètre
     */
    @Override
    public int compareTo(final Card card) {
        return Integer.compare(this.getOrderValue(), card.getOrderValue());
    }


    /**
     * Enumération des valeurs des cartes<br>
     * SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI, AS<br>
     * Chaque valeur est associée à un nombre de points<br>
     * SEPT(1), HUIT(2), NEUF(3), DIX(5), VALET(6), DAME(7), ROI(8), AS(11)
     * @see Value#getPoint()
     * @see CardColor
     */
    public enum Value {
        /**
         * Sept (1 point)
         */
        SEPT(1),
        /**
         * Huit (2 points)
         */
        HUIT(2),
        /**
         * Neuf (3 points)
         */
        NEUF(3),
        /**
         * Dix (5 points)
         */
        DIX(5),
        /**
         * Valet (6 points)
         */
        VALET(6),
        /**
         * Dame (7 points)
         */
        DAME(7),
        /**
         * Roi (8 points)
         */
        ROI(8),
        /**
         * As (11 points)
         */
        AS(11);

        private final int point;

        /**
         * Crée une valeur avec un nombre de points
         * @param point le nombre de points
         */
        Value(int point) {
            this.point = point;
        }

        /**
         * Renvoie le nombre de points associé à la valeur
         * @return le nombre de points associé à la valeur
         */
        public int getPoint() {
            return point;
        }
    }

    /**
     * Enumération des couleurs des cartes<br>
     * COEUR, CARREAU, PIQUE, TREFLE
     * @see Value
     */
    public enum CardColor {
        /**
         * Coeur
         */
        COEUR(2),
        /**
         * Carreau
         */
        CARREAU(1),
        /**
         * Pique
         */
        PIQUE(3),
        /**
         * Trèfle
         */
        TREFLE(4);

        private final int orderValue;

        /**
         * Crée une couleur avec une valeur
         * @param orderValue la valeur de la couleur
         */
        CardColor(int orderValue) {
            this.orderValue = orderValue;
        }

        /**
         * Renvoie la valeur de la couleur
         * @return la valeur de la couleur
         */
        public int getOrderValue() {
            return orderValue;
        }

        /**
         * Renvoie une couleur aléatoire
         * @return une couleur aléatoire
         */
        public static CardColor getRandomColor() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

}
