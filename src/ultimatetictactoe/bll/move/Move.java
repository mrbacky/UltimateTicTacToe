package ultimatetictactoe.bll.move;

public class Move implements IMove {

    int x=0;
    int y=0;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

}
