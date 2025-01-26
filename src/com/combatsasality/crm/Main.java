package com.combatsasality.crm;

import com.combatsasality.crm.persistence.entities.User;
import com.combatsasality.crm.persistence.repositories.TicketRepository;
import com.combatsasality.crm.persistence.repositories.UserRepository;
import com.combatsasality.crm.ui.UI;

import java.io.IOException;

public class Main {
    public static final UserRepository userRepository = new UserRepository();
    public static final TicketRepository ticketRepository = new TicketRepository();
    public static User currentUser = null;



    public static void main(String[] args) {
        // TODO: fix dumb terminal
        System.setProperty("org.jline.terminal.dumb", "true");

        try {
            new UI();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }

    }
}