package com.combatsasality.crm.ui.views;

import com.combatsasality.crm.Main;
import com.combatsasality.crm.persistence.entities.User;
import com.combatsasality.crm.ui.History;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

public class LoginView extends BaseView{
    public LoginView(Terminal terminal, LineReader reader, History history) {
        super(terminal, reader, history);
    }

    public void show(boolean clear) {
        super.show(clear);

        String username = this.reader.readLine("Введіть ім'я користувача: ");
        String password = this.reader.readLine("Введіть пароль: ", '*');

        User user = Main.userRepository.authenticate(username, password);

        if (user == null) {
            pressAnyForContinue("Неправильне ім'я користувача або пароль");
            show(true);
            return;
        }

        this.history.changeMode(History.Mode.MAIN_MENU);

        Main.currentUser = user;

    }



}
