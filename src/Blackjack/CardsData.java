package Blackjack;

public enum CardsData {
    BACK("Card Back",             "card_back",    0,        0 ),

    KS(    "King of Spades",         "KS",    13,        1 ),
    KH(    "King of Hearts",         "KH",    13,        2 ),
    KD(    "King of Diamonds",     "KD",    13,        3 ),
    KC(    "King of Clovers",        "KC",    13,        4 ),

    QS(    "Queen of Spades",         "QS",    12,        1 ),
    QH(    "Queen of Hearts",         "QH",    12,        2 ),
    QD(    "Queen of Diamonds",     "QD",    12,        3 ),
    QC(    "Queen of Clovers",        "QC",    12,        4 ),

    JS (    "Jack of Spades",         "JS",    11,        1 ),
    JH(    "Jack of Hearts",         "JH",    11,        2 ),
    JD(    "Jack of Diamonds",     "JD",    11,        3 ),
    JC(    "Jack of Clovers",        "JC",    11,        4 ),

    S10(    "10 of Spades",         "10S",    10,        1 ),
    H10(    "10 of Hearts",         "10H",    10,        2 ),
    D10(    "10 of Diamonds",     "10D",    10,        3 ),
    C10(    "10 of Clovers",        "10C",    10,        4 ),

    S9(    "9 of Spades",         "9S",        9,        1 ),
    H9(    "9 of Hearts",         "9H",        9,        2 ),
    D9(    "9 of Diamonds",         "9D",        9,        3 ),
    C9(    "9 of Clovers",        "9C",        9,        4 ),

    S8(    "8 of Spades",         "8S",        8,        1 ),
    H8(    "8 of Hearts",         "8H",        8,        2 ),
    D8(    "8 of Diamonds",         "8D",        8,        3 ),
    C8(    "8 of Clovers",        "8C",        8,        4 ),

    S7(    "7 of Spades",         "7S",        7,        1 ),
    H7(    "7 of Hearts",         "7H",        7,        2 ),
    D7(    "7 of Diamonds",         "7D",        7,        3 ),
    C7(    "7 of Clovers",        "7C",        7,        4 ),

    S6(    "6 of Spades",         "6S",        6,        1 ),
    H6(    "6 of Hearts",         "6H",        6,        2 ),
    D6(    "6 of Diamonds",         "6D",        6,        3 ),
    C6(    "6 of Clovers",        "6C",        6,        4 ),

    S5(    "5 of Spades",         "5S",        5,        1 ),
    H5(    "5 of Hearts",         "5H",        5,        2 ),
    D5(    "5 of Diamonds",         "5D",        5,        3 ),
    C5(    "5 of Clovers",        "5C",        5,        4 ),

    S4(    "4 of Spades",         "4S",        4,        1 ),
    H4(    "4 of Hearts",         "4H",        4,        2 ),
    D4(    "4 of Diamonds",         "4D",        4,        3 ),
    C4(    "4 of Clovers",        "4C",        4,        4 ),

    S3(    "3 of Spades",         "3S",        3,        1 ),
    H3(    "3 of Hearts",         "3H",        3,        2 ),
    D3(    "3 of Diamonds",         "3D",        3,        3 ),
    C3(    "3 of Clovers",        "3C",        3,        4 ),

    S2(    "2 of Spades",         "2S",        2,        1 ),
    H2(    "2 of Hearts",         "2H",        2,        2 ),
    D2(    "2 of Diamonds",         "2D",        2,        3 ),
    C2(    "2 of Clovers",        "2C",        2,        4 ),

    AS(    "Ace of Spades",         "AS",        1,        1 ),
    AH(    "Ace of Hearts",         "AH",        1,        2 ),
    AD(    "Ace of Diamonds",     "AD",        1,        3 ),
    AC(    "Ace of Clovers",        "AC",        1,        4 );

    public final String label;
    public final String fileName;
    public final int value;
    public final int set;

    CardsData(String label, String fileName, int value, int set) {
        this.label = label;
        this.fileName = fileName;
        this.value = value;
        this.set = set;
    }

}
