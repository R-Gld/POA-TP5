package fr.Rgld_;

import fr.Rgld_.Strategies.Strategy1;
import fr.Rgld_.Strategies.Strategy2;

import java.util.Iterator;
import java.util.Scanner;

/**
 * TP5 - Jeu de 32 cartes<br>
 * Selon le <a href="https://url.rgld.fr/poa-sujet-td5">PDF de consigne</a>.
 */
public class Game {

    GameOf32Cards jeu;
    Player player1;
    Player player2;

    /**
     * Constructeur pour créer un nouveau jeu
     */
    public Game() {
        jeu = GameOf32Cards.nouveauJeu32CartesMelanger();
        player1 = Player.createNewPlayer();
        player2 = Player.createNewPlayer();
    }

    /**
     * Distribue les cartes aux joueurs
     */
    public void distribute() {
        Deck paq1 = player1.getDeck();
        Deck paq2 = player2.getDeck();

        Iterator<Card> it = jeu.getCartes().iterator();
        for (int i = 0; i < 32; i++) {
            if (i % 2 == 0) {
                paq1.addCard(it.next());
            } else {
                paq2.addCard(it.next());
            }
        }
        paq1.order();
        paq2.order();
    }

    /**
     * Demande à l'utilisateur de choisir une stratégie pour le jeu
     * @return la stratégie choisie
     */
    private Strategy chooseStrategie() {
        Strategy[] strats = new Strategy[] { new Strategy1(), new Strategy2() };
        Scanner scan = new Scanner(System.in);
        System.out.println("Choisissez une stratégie pour le jeu (Se référer au pdf TD5):");
        for (int i = 0; i < strats.length; i++) {
            System.out.println("\t" + (i + 1));
        }
        int choice = scan.nextInt();
        if(choice < 1 || choice > strats.length) {
            System.out.println("Choix invalide");
            return chooseStrategie();
        }
        return strats[choice - 1];
    }


    /**
     * Méthode principale pour lancer le jeu.
     * <ul>
     * <li>On commence par distribuer les cartes aux joueurs</li>
     * <li>On affiche les paquets des joueurs</li>
     * <li>On demande à l'utilisateur de choisir une stratégie pour le jeu.</li>
     * <li>On exécute la stratégie jusqu'à ce qu'un des joueurs n'ait plus de cartes</li>
     * <li>On affiche le résultat du jeu.</li>
     * </ul>
     * @param args les arguments. Non utilisés
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.distribute();
        System.out.println("Paquet de " + game.getJoueur1().getName() + ":");
        for (Card card : game.getJoueur1().getDeck().getCards()) {
            System.out.println("\t" + card);
        }
        System.out.println("Paquet de " + game.getJoueur2().getName() + ":");
        for (Card card : game.getJoueur2().getDeck().getCards()) {
            System.out.println("\t" + card);
        }
        Strategy strat = game.chooseStrategie();
        Player j1 = game.getJoueur1();
        Player j2 = game.getJoueur2();
        Player lastWinner = null;
        while(!j1.getDeck().getCards().isEmpty() && !j2.getDeck().getCards().isEmpty()) {
            if(lastWinner == null) lastWinner = j1; // le cas de premier tour. On commence par le premier joueur.
            Strategy.Result res = strat.runStrategy(lastWinner, lastWinner == j1 ? j2 : j1);
            lastWinner = res.getPlayer();
            System.out.println(res);
            res.apply();
            System.out.println();
        }

        System.out.println("Le jeu est terminé.\n");
        int j1Points = j1.getPoints();
        int j2Points = j2.getPoints();
        System.out.println("Le joueur '" + j1.getName() + "' a " + j1Points + " points");
        System.out.println("Le joueur '" + j2.getName() + "' a " + j2Points + " points\n");
        if(j1Points > j2Points) {
            System.out.println("Le joueur '" + j1.getName() + "' a gagné");
        } else if(j1Points < j2Points) {
            System.out.println("Le joueur '" + j2.getName() + "' a gagné");
        } else {
            System.out.println("Match nul");
        }
    }


    /**
     * Renvoie le jeu de 32 cartes
     * @return le jeu de 32 cartes
     */
    public GameOf32Cards getJeu() {
        return jeu;
    }
    /**
     * Renvoie le joueur 1
     * @return le joueur 1
     */
    public Player getJoueur1() {
        return player1;
    }
    /**
     * Renvoie le joueur 2
     * @return le joueur 2
     */
    public Player getJoueur2() {
        return player2;
    }

}
