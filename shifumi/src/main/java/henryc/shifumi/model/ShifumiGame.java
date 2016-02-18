package henryc.shifumi.model;

public class ShifumiGame {

    private ShifumiChoices computerChoice;
    private ShifumiChoices playerChoice;
    private String winner;
    private int score[] = {0,0};

    public ShifumiGame() {
    }

    public ShifumiChoices randomComputerChoice(){
        int mini = 1;
        int max = 5;
        switch((int)( Math.random()*( max - mini + 1 ) ) + mini){
            case 1:
                this.computerChoice = ShifumiChoices.LIZARD;
                break;
            case 2:
                this.computerChoice = ShifumiChoices.SPOCK;
                break;
            case 3:
                this.computerChoice = ShifumiChoices.PAPER;
                break;
            case 4:
                this.computerChoice = ShifumiChoices.SCISSORS;
                break;
            case 5:
                this.computerChoice = ShifumiChoices.STONE;
                break;
        }
        return this.computerChoice;
    }

    public void playRound(ShifumiChoices choice){
        winner = "";
        playerChoice = choice;
        whoWinRounnd(ShifumiChoices.LIZARD, ShifumiChoices.SPOCK, ShifumiChoices.PAPER, ShifumiChoices.SCISSORS, ShifumiChoices.STONE);
        whoWinRounnd(ShifumiChoices.SPOCK, ShifumiChoices.SCISSORS, ShifumiChoices.STONE, ShifumiChoices.LIZARD, ShifumiChoices.PAPER);
        whoWinRounnd(ShifumiChoices.SCISSORS, ShifumiChoices.PAPER, ShifumiChoices.LIZARD, ShifumiChoices.SPOCK, ShifumiChoices.STONE);
        whoWinRounnd(ShifumiChoices.PAPER, ShifumiChoices.STONE, ShifumiChoices.SPOCK, ShifumiChoices.SCISSORS, ShifumiChoices.LIZARD);
        whoWinRounnd(ShifumiChoices.STONE, ShifumiChoices.LIZARD, ShifumiChoices.SCISSORS, ShifumiChoices.PAPER, ShifumiChoices.SPOCK);
    }

    public void whoWinRounnd(ShifumiChoices conditionOne, ShifumiChoices conditionTwo, ShifumiChoices conditionThree, ShifumiChoices conditionFour, ShifumiChoices conditionFive){
        //Si le joueur choisit le lézard
        if (playerChoice.equals(conditionOne)){
            //Si l'ordinateur choisit Spock ou Papier
            if (computerChoice.equals(conditionTwo) || computerChoice.equals(conditionThree)){
                //Alors le joueur gagne
                winner = "player";
                score[0] += 1;
            //Sinon si l'ordinateur choisit les ciseaux ou la pierre
            }else if (computerChoice.equals(conditionFour) || computerChoice.equals(conditionFive)){
                //Alors le joueur perd
                winner = "computer";
                score[1] += 1;
            }
        }
    }

    public String getWinner() {
        return winner;
    }

    public int[] getScore() {
        return score;
    }
}
