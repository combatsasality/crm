package com.combatsasality.crm.ui.views;

import com.combatsasality.crm.ui.History;
import org.jline.reader.LineReader;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseView {
    protected final Terminal terminal;
    protected final LineReader reader;
    protected final PrintWriter writer;
    protected final History history;

    protected BaseView(Terminal terminal, LineReader reader, History history) {
        this.terminal = terminal;
        this.reader = reader;
        this.writer = terminal.writer();
        this.history = history;
    }

    public void clear() {
        this.terminal.puts(InfoCmp.Capability.clear_screen);
        this.terminal.flush();
    }


    public void show(boolean clear) {
        if (clear) clear();
    }

    public void show() {
        show(true);
    }

    public void pressAnyForContinue(String message) {
        try {
            this.writer.println(message);
            this.writer.println("Щоб продовжити нажміть будь-яку клавішу");
            this.writer.flush();
            this.terminal.reader().read();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
            System.exit(0);
        }
    }

}
