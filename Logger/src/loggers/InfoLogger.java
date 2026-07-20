package loggers;

public class InfoLogger extends Logger {

    public InfoLogger(String thisLoggerLevel, Logger nextLogger) {
        super(thisLoggerLevel, nextLogger);
    }

    @Override
    public void write(String data) {
        System.out.println("Log written by "+ thisLoggerLevel +" logger: " + data);
    }
}