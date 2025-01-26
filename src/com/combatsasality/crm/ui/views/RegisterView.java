package com.combatsasality.crm.ui.views;

import com.combatsasality.crm.HelpHandler;
import com.combatsasality.crm.Main;
import com.combatsasality.crm.persistence.entities.User;
import com.combatsasality.crm.persistence.exceptions.BadValidationException;
import com.combatsasality.crm.ui.History;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

public class RegisterView extends BaseView{
    public RegisterView(Terminal terminal, LineReader reader, History history) {
        super(terminal, reader, history);
    }

    public void show(boolean clear) {
        super.show(clear);
        this.history.changeMode(History.Mode.AUTHORIZATION);

        String username = getUsername();
        String password = getPassword();

        User user = new User(username, password);

        Main.userRepository.add(user);
        Main.userRepository.save();
    }


    public String getUsername() {
        String username = this.reader.readLine("Введіть ім'я користувача: ");
        if (Main.userRepository.findByUsername(username).isPresent()) {
            this.writer.println("Ім'я користувача вже зайнято");
            show(false);
            return getUsername();
        }

        return username;
    }

    public String getPassword() {
        String password = this.reader.readLine("Введіть пароль: ", '*');

        try {
            return HelpHandler.validatePassword(password);
        } catch (BadValidationException e) {
            this.writer.println(e.getMessage());
            return getPassword();
        }

    }



}
