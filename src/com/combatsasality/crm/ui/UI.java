package com.combatsasality.crm.ui;

import com.combatsasality.crm.ui.views.AuthView;
import com.combatsasality.crm.ui.views.LoginView;
import com.combatsasality.crm.ui.views.MenuView;
import com.combatsasality.crm.ui.views.RegisterView;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;

public class UI {
    private final Terminal terminal;
    private final LineReader reader;
    private final History history;


    private final AuthView authView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final MenuView menuView;

    public UI() throws IOException {
        this.terminal = TerminalBuilder.builder().system(true).encoding("utf-8").build();
        this.reader = LineReaderBuilder.builder().terminal(terminal).build();
        this.history = new History();

        // VIEWS
        this.authView = new AuthView(this.terminal, this.reader, this.history);
        this.registerView = new RegisterView(this.terminal, this.reader, this.history);
        this.loginView = new LoginView(this.terminal, this.reader, this.history);
        this.menuView = new MenuView(this.terminal, this.reader, this.history);

        init();
    }

    private void init() {
        clear();
        while (true) {
            switch (this.history.getCurrentMode()) {
                case AUTHORIZATION -> this.authView.show();
                case SIGN_UP -> this.registerView.show();
                case SIGN_IN -> this.loginView.show();
                case MAIN_MENU -> this.menuView.show();
                default -> this.authView.show();
            }
        }

    }

    public void clear() {
        this.terminal.puts(InfoCmp.Capability.clear_screen);
        this.terminal.flush();
    }


}
