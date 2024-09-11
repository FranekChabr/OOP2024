public abstract class Player{
    private final Symbol symbol;
    private Board board;

    public Player(Symbol symbol) {
        if(symbol == Symbol.E){ throw new IllegalArgumentException("Gracz nie moze byc pustym polem"); }
        this.symbol = symbol;
        //this.board = board;
    }

    public abstract String playTurn();
    public abstract String inform();

    public Symbol getSymbol() {
        return symbol;
    }
}
