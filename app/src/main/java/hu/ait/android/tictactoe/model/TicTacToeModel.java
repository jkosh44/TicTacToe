package hu.ait.android.tictactoe.model;

/**
 * Created by joe on 9/21/15.
 */
public class TicTacToeModel {

    private static TicTacToeModel instance = null;

    private TicTacToeModel () {
    }

    public static TicTacToeModel getInstance() {
        if (instance == null) {
            instance = new TicTacToeModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;
    public static final short NOWINNER = -1;

    private static final short X_WON = CROSS * 3;
    private static final short O_WON = CIRCLE * 3;

    private short[] rows = {EMPTY, EMPTY, EMPTY};
    private short[] cols = {EMPTY, EMPTY, EMPTY};
    private short   diagTL = EMPTY;
    private short   diagTR = EMPTY;

    private short[] rowsFul = {0,0,0};
    private short[] colsFul = {0,0,0};
    private short   diagTLFul = 0;
    private short   diagTRFul = 0;

    private short[][] model = {
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY },
            { EMPTY, EMPTY, EMPTY }
    };
    private short nextPlayer = CIRCLE;
    private short winner = NOWINNER;

    public void resetModel() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
                rows[i] = EMPTY;
                cols[i] = EMPTY;
                diagTL = EMPTY;
                diagTR = EMPTY;
                rowsFul[i] = 0;
                colsFul[i] = 0;
                diagTLFul = 0;
                diagTRFul = 0;
            }
        }
        nextPlayer = CIRCLE;
        winner = NOWINNER;
    }

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short setFieldContent(int x, int y, short content) {
        rows[x]+= content;
        cols[y]+= content;
        rowsFul[x]++;
        colsFul[y]++;
        setDiagonals(x, y, content);
        checkWinner();

        return model[x][y] = content;
    }

    private void setDiagonals(int x, int y, short content) {
        if(x == 0){
            if (y == 0) {diagTL+=content; diagTLFul++;}
            else if(y == 2) {diagTR+=content; diagTRFul++;}
        }
        if(x==1 && y ==1) {diagTR+=content;diagTL+= content; diagTLFul++; diagTRFul++;}

        if(x==2) {
            if (y==2) {diagTL+=content; diagTLFul++;}
            else if(y==0) {diagTR+=content; diagTRFul++;}
        }
    }

    public short getNextPlayer() {
        return nextPlayer;
    }

    public void changeNextPlayer() {
        nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;
    }

    public short getWinner() {
        return winner;
    }


    public void checkWinner() {
        for(int i = 0; i < 3; i ++) {
            if(rows[i] == X_WON && rowsFul[i] == 3) {winner = CROSS;}
            if(rows[i] == O_WON && rowsFul[i] == 3) {winner = CIRCLE;}
            if(cols[i] == X_WON && colsFul[i] == 3) {winner = CROSS;}
            if(cols[i] == O_WON && colsFul[i] == 3) {winner = CIRCLE;}
        }
        if(diagTL == X_WON && diagTLFul == 3) {winner = CROSS;}
        if(diagTL == O_WON && diagTLFul == 3) {winner = CIRCLE;}
        if(diagTR == X_WON && diagTRFul == 3) {winner = CROSS;}
        if(diagTR == O_WON && diagTRFul == 3) {winner = CIRCLE;}
    }

   /* private void checkDiags(short player) {
        if(model[0][0] == player && model[1][1] == player && model[2][2] == player) {
            winner = player; }
        if(model[0][2] == player && model[1][1] == player && model[2][0] == player) {
            winner = player; }
    }*/
}
