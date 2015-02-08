package kata.td;

/**
 * Created by dwp on 15-2-8.
 */
public class TicketDispenser {
    private TurnNumberSequence turnNumberSequence;

    public TicketDispenser(TurnNumberSequence turnNumberSequence) {
        this.turnNumberSequence = turnNumberSequence;
    }

    public TicketDispenser() {
        this(new TurnNumberSequence());
    }

    public TurnTicket getTurnTicket()
    {
        int newTurnNumber = turnNumberSequence.getNextTurnNumber();
        return generateTurnTicket(newTurnNumber);
    }

    private TurnTicket generateTurnTicket(int newTurnNumber) {
        TurnTicket newTurnTicket = new TurnTicket(newTurnNumber);
        return newTurnTicket;
    }

    public TurnTicket getVipTurnTicket() {
        int newTurnNumber = turnNumberSequence.getNextTurnNumberWithType("vip");
        return generateTurnTicket(newTurnNumber);
    }

    public TurnTicket getRegularTurnTicket(){
        int newTurnNumber = turnNumberSequence.getNextTurnNumberWithType("regular");
        return generateTurnTicket(newTurnNumber);
    }
}
