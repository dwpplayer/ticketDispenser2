package kata.td;

/**
 * Created by twer on 2/8/15.
 */
public class MockedTurnNumberSequence extends TurnNumberSequence {
    private int turnNumber;
    private int callOnce = 0;

    public void assignTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void verifyMethodGetTurnNumberSequenceCalledOnce() {
        if(this.callOnce != 1){
            throw new IllegalStateException("the method getNextTurnNumber in mock doesn't be called once");
        }
    }

    @Override
    public int getNextTurnNumber(){
        ++this.callOnce;
        return turnNumber;
    }
}
