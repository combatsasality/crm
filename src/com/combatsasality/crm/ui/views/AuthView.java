package com.combatsasality.crm.ui.views;

import com.combatsasality.crm.ui.History;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;

public class AuthView extends BaseView{


    public AuthView(Terminal terminal, LineReader reader, History history) {
        super(terminal, reader, history);
    }

    @Override
    public void show(boolean clear) {
        super.show(clear);
        writer.println("1. Авторизація");
        writer.println("2. Реєстрація");
        writer.println("3. Вихід");

        String option = reader.readLine("Оберіть варіант меню: ");

        switch (option) {
            case "1" -> this.history.changeMode(History.Mode.SIGN_IN);
            case "2" -> this.history.changeMode(History.Mode.SIGN_UP);
            case "3" -> System.exit(0);
            default -> show();
        }

    }



}
