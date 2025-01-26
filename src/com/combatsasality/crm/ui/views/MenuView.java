package com.combatsasality.crm.ui.views;

import com.combatsasality.crm.Main;
import com.combatsasality.crm.persistence.entities.Ticket;
import com.combatsasality.crm.persistence.entities.User;
import com.combatsasality.crm.ui.History;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

import java.util.Optional;
import java.util.Set;

public class MenuView extends BaseView{
    public MenuView(Terminal terminal, LineReader reader, History history) {
        super(terminal, reader, history);
    }


    @Override
    public void show() {
        super.show();

        this.writer.println("1. Перевірити дійсність квитка");
        this.writer.println("2. Вихід");
        boolean isAdmin = Main.currentUser.getRole().equals(User.Role.ADMIN);
        if (isAdmin) {
            this.writer.println("3. Список всіх користувачів");
            this.writer.println("4. Надати корисувачу квиток");
        }


        String option = this.reader.readLine("Оберіть варіант меню: ");
        switch (option) {
            case "1" -> {
                Optional<Ticket> ticket = Main.ticketRepository.findAvailableTicketByUserId(Main.currentUser.getId());
                if (ticket.isEmpty()) {
                    pressAnyForContinue("В вас нема квитка, для придбання квитка зверніться до адміністратора");
                    break;
                }
                pressAnyForContinue(String.format("Квиток дійсний до %s", ticket.get().getValidUntil()));
            }
            case "3" -> {
                if (isAdmin) {
                    String format = "%-20s | %-20s";
                    Set<User> users = Main.userRepository.findAll();
                    this.writer.println(String.format(format, "Ім'я користувача", "Квиток дійсний до"));
                    users.forEach(u -> {
                        Optional<Ticket> ticket = Main.ticketRepository.findAvailableTicketByUserId(u.getId());
                        if (ticket.isPresent()) {
                            this.writer.println(String.format(format, u.getUsername(), ticket.get().getValidUntil()));
                        } else {
                            this.writer.println(String.format(format, u.getUsername(), "-"));
                        }
                    });
                }
            }
            case "4" -> {
                if (isAdmin) {
                    User user = getUser();
                    Optional<Ticket> optionalTicket = Main.ticketRepository.findAvailableTicketByUserId(user.getId());
                    int days = getDays(optionalTicket.isPresent());
                    long timeToAdd = (long) days * 24 * 60 * 60 * 1000;
                    if (optionalTicket.isEmpty()) {
                        Ticket ticket = new Ticket(
                                System.currentTimeMillis() + timeToAdd,
                                user.getId()
                        );
                        Main.ticketRepository.add(ticket);
                    } else {
                        Ticket ticket = optionalTicket.get();
                        ticket.addTime(timeToAdd);
                    }
                    Main.ticketRepository.save();
                    this.writer.println(optionalTicket.isPresent() ? "Квиток успішно продовжено" : "Квиток успішно видано");
                }
            }
            case "2" -> {
                this.history.changeMode(History.Mode.AUTHORIZATION);
                Main.currentUser = null;
            }
            default -> {
                // NO-OP
            }
        }
    }

    private User getUser() {
        String username = this.reader.readLine("Введіть ім'я користувача якому хочете надати квиток: ");
        Optional<User> user = Main.userRepository.findByUsername(username);

        if (user.isEmpty()) {
            this.writer.println("Такого користувача не існує");
            return getUser();
        }

        return user.get();

    }


    private int getDays(boolean add) {
        String toParse = this.reader.readLine(add ? "Кількість днів (продовження): " : "Кількіть днів: ");
        try {
            return Integer.parseInt(toParse);
        } catch (NumberFormatException e) {
            this.writer.println("Потрібно ввести цифру!");
            return getDays(add);
        }
    }

}
