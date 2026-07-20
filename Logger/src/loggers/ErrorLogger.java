package loggers;

public class ErrorLogger extends Logger {

    public ErrorLogger(String thisLoggerLevel, Logger nextLogger) {
        super(thisLoggerLevel, nextLogger);
    }

    @Override
    public void write(String data) {
        System.out.println("Log written by "+ thisLoggerLevel +" logger: " + data);
    }
}