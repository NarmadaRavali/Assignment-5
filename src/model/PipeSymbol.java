package model;

/**
 * @author Narmada Ravali
 * @Description: Class for pipe symbol
 * @since 03-06-2021
 */
public class PipeSymbol extends Symbol {
    public PipeSymbol(int x, int y) {
        super("||", x, y);
        int width = this.getPreferredSize().width;
        int height = this.getPreferredSize().height;
        new Pipe(10, 10, this);
        new Pipe(width - 25, 10, this);
    }
}
