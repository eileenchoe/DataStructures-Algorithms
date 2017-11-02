import java.util.Arrays;

/**
 *
 * @author Eileen Choe
 * @since 2017-04-19
 *
 * Crown And Anchor Monte Carlo Simulation
 * Uses Monte Carlo simulation to determine whether the game Crown and Anchor
 * favors the house, the player, or neither.
 *
 */


public class CrownAndAnchor {

  /**
  * @param NUM_FACES_ON_DICE
  * @param DEFAULT_NUM_SIMULATIONS
  * @param DEFAULT_NUM_GAMES
  * @param DEFAULT_WAGER_AMOUNT
  */
  static int NUM_FACES_ON_DICE = 6;
  static int DEFAULT_NUM_SIMULATIONS = 1000;
  static int DEFAULT_NUM_GAMES = 10000;
  static int DEFAULT_WAGER_AMOUNT = 1;

  /**
  *
  * Runner for the Crown and Anchor game simulator.
  * @param args Command line arguments
  *
  */

  public static void main (String [] args) {

    if (args.length == 0 || args.length == 3) {
      CrownAndAnchor game = args.length == 0 ? new CrownAndAnchor() : new CrownAndAnchor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
      game.monteCarloSimulator();
    } else {
      printUsage();
    }
  }

  /**
  * Prints instructions for the command line usage.
  *
  */

  public static void printUsage() {
    System.out.println("Expected command line usage: java CrownAndAnchor [number of games] [wager amount] [number of simulations]");
  }

  int numGames;
  int wagerAmount;
  int numSimulations;

  /**
  *
  * Constructor with no arguments will set the number of games, wager amount, and number of simulations to
  * default values.
  *
  */

  CrownAndAnchor() {
    this.numGames = DEFAULT_NUM_GAMES;
    this.wagerAmount = DEFAULT_WAGER_AMOUNT;
    this.numSimulations = DEFAULT_NUM_SIMULATIONS;
  }

  /**
  *
  * Constructor with 3 arguments sets up a custom simulation of the Crown and Anchor game
  * @param numGames Number of games the player plays
  * @param wagerAmount Amount of money player wagers each game
  * @param numSimulations Number of simulations of Crown and Anchor played numGames times
  */

  CrownAndAnchor(int numGames, int wagerAmount, int numSimulations) {
    this.numGames = numGames;
    this.wagerAmount = wagerAmount;
    this.numSimulations = numSimulations;
  }

  /**
  *
  * Algorithm to perform Monte Carlo simulation. Will print out whether under the simulated conditions,
  * the game favors the house, the player, or neither, and by what percentage.
  *
  */

  public void monteCarloSimulator() {
    int amountOfMoneyWagered = this.numGames * this.wagerAmount;
    int total = 0;

    for (int k = 0; k < this.numSimulations; k++) {
      int balance = 0;
      for (int i = 0; i < this.numGames; i++) {
        balance -= this.wagerAmount;
        int bet = rollDice();
        int[] diceRolls = {rollDice(), rollDice(), rollDice()};
        int numMatches = 0;
        for (int j = 0; j < diceRolls.length; j++) {
          if(bet == diceRolls[j]) {
            numMatches++;
          }
        }
        balance += (numMatches > 0) ? (numMatches + 1) * this.wagerAmount : 0;
      }
      total += balance;
    }

    double avgNetBalance = (double) total / (double) this.numSimulations;
    double percent = (avgNetBalance / (double) amountOfMoneyWagered) * 100;

    System.out.println("\n***** CROWN AND ANCHOR SIMULATION *****");
    System.out.println(outputMessage(percent, avgNetBalance) + "\n");
  }

  private static int rollDice() {
    return (int)Math.floor(Math.random() * NUM_FACES_ON_DICE + 1);
  }

  private String outputMessage(double percent, double avgNetBalance) {
    if (percent > 0) {
      return (
      "The game favors the PLAYER. The player wins " + percent + "% of the money wagered.\n" +
      "The player's average net balance after " + this.numGames + " games are played in " + this.numSimulations +
      " simulations when wagering $" + this.wagerAmount + " per game is: $" + avgNetBalance + "."
      );
    } else if (percent < 0) {
      return (
      "The game favors the HOUSE. The player loses " + Math.abs(percent) + "% of the money wagered.\n" +
      "The player's average net balance after " + this.numGames + " games are played in " + this.numSimulations +
      " simulations when wagering $" + this.wagerAmount + " per game is: -$" + Math.abs(avgNetBalance) + "."
      );
    } else {
      return "The game NEITHER favors the house or player.";
    }
  }

}
