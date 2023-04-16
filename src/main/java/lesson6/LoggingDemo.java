package lesson6;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.*;

public class LoggingDemo {

    /*
     * SEVERE (highest value)
     * WARNING
     * INFO
     * CONFIG
     *
     * FINE
     * FINER
     * FINEST (lowest value)
     */

    static DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static void main(String[] args) {
        Logger log = Logger.getLogger(LoggingDemo.class.getName());

        Handler customConsoleHandler = new ConsoleHandler();
        customConsoleHandler.setLevel(Level.WARNING);
        customConsoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return "[" + record.getLevel() + "] "
                        + format.format(Date.from(record.getInstant())) + " "
                        + record.getSourceClassName() + "#" + record.getSourceMethodName() + " "
                        + record.getMessage() + "\n";
            }
        });
        log.addHandler(customConsoleHandler);

        log.info("Info message");
        log.log(Level.WARNING, "Warning message");



    }

}
