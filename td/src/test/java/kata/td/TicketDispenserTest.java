package kata.td;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by dwp on 15-2-8.
 */
public class TicketDispenserTest {

    @Test
    public void the_turn_number_should_be_21134_if_I_give_21134_to_the_turn_number_sequence_generator(){
        //Arrange
        MockedTurnNumberSequence turnNumberSequence = new MockedTurnNumberSequence();
        turnNumberSequence.assignTurnNumber(21134);
        TicketDispenser ticketDispenser = new TicketDispenser(turnNumberSequence);

        //act
        TurnTicket ticket = ticketDispenser.getTurnTicket();

        //Assert
        assertEquals(21134, ticket.getTurnNumber());
        turnNumberSequence.verifyMethodGetTurnNumberSequenceCalledOnce();
    }

    @Test
    public void the_turn_number_should_be_11_if_I_give_11_to_the_turn_number_sequence_generator(){
        //Arrange
        TurnNumberSequence mockedTurnNumberSequence = mock(TurnNumberSequence.class);
        when(mockedTurnNumberSequence.getNextTurnNumber()).thenReturn(11);
        TicketDispenser ticketDispenser = new TicketDispenser(mockedTurnNumberSequence);

        //act
        TurnTicket ticket = ticketDispenser.getTurnTicket();

        //Assert
        assertEquals(11, ticket.getTurnNumber());
        verify(mockedTurnNumberSequence).getNextTurnNumber();
    }
    
    @Test
    public void a_new_ticket_turn_number_should_subsequent_to_the_previous_one()
    {
        //Arrange
        TicketDispenser ticketDispenser = new TicketDispenser();
        TurnTicket previousTicket = ticketDispenser.getTurnTicket();

        //Act
        TurnTicket newTicket = ticketDispenser.getTurnTicket();

        //Assert
        assertEquals(newTicket.getTurnNumber(), previousTicket.getTurnNumber() + 1);
    }

    @Test
    public void a_new_ticket_turn_number_should_subsequent_to_the_previous_one_dispatch_from_another_dispenser()
    {
        //Arrange
        TicketDispenser anotherTicketDispenser = new TicketDispenser();
        TurnTicket previousTicket = anotherTicketDispenser.getTurnTicket();

        TicketDispenser ticketDispenser = new TicketDispenser();

        //Act
        TurnTicket newTicket = ticketDispenser.getTurnTicket();

        //Assert
        assertEquals(newTicket.getTurnNumber(), previousTicket.getTurnNumber() + 1);
    }



    @Test
    public  void vip_ticket_turn_number_should_starts_with_1001()
    {
        //Arrange
        TicketDispenser ticketDispenser = new TicketDispenser();

        //Act
        TurnTicket vipTicket = ticketDispenser.getVipTurnTicket();

        //assert
        assertEquals(1001, vipTicket.getTurnNumber());
    }
    
    @Test
    public void vip_ticket_turn_number_should_larger_than_1001_if_there_have_already_exist_one_vip_user()
    {
        //Arrange
        TicketDispenser ticketDispenser = new TicketDispenser();
        TurnTicket vipTicket = ticketDispenser.getVipTurnTicket();
        
        //Act
        vipTicket = ticketDispenser.getVipTurnTicket();
        
        //Assert
        assertNotEquals(1001, vipTicket.getTurnNumber());
        assertTrue(vipTicket.getTurnNumber() > 1001);
    }

    @Test
    public  void regular_user_ticket_turn_number_should_starts_with_2001()
    {
        //Arrange
        TicketDispenser ticketDispenser = new TicketDispenser();

        //Act
        TurnTicket vipTicket = ticketDispenser.getRegularTurnTicket();

        //assert
        assertEquals(2001, vipTicket.getTurnNumber());
    }
}
