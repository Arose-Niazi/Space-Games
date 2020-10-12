package Blackjack;

public enum Cards {

    DECK("Total No of Cards",52),
    BLACKJACK("Value of Blackjack", 21),
    CARDSPOSSIBLE("Total Cards possible in hand", 6);

    public final String label;
    public final int value;

    Cards(String label,int value) {
        this.label = label;
        this.value = value;
    }


}
