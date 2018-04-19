package com.orders;

public class Ticket {
    private Long EventID;

    private Long TicketID;

    private String TicketStatus;

    public Ticket() {
    }


    public Ticket(Long eventID, Long ticketID, String ticketStatus) {
        EventID = eventID;
        TicketID = ticketID;
        TicketStatus = ticketStatus;
    }

    public Long getEventID() {
        return EventID;
    }

    public void setEventID(Long eventID) {
        EventID = eventID;
    }

    public Long getTicketID() {
        return TicketID;
    }

    public void setTicketID(Long ticketID) {
        TicketID = ticketID;
    }

    public String getTicketStatus() {
        return TicketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        TicketStatus = ticketStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "EventID=" + EventID +
                ", TicketID=" + TicketID +
                ", TicketStatus='" + TicketStatus + '\'' +
                '}';
    }
}
