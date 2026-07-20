package loggers;

public class DebugLogger extends Logger {

    public DebugLogger(String thisLoggerLevel, Logger nextLogger) {
        super(thisLoggerLevel, nextLogger);
    }

    @Override
    public void write(String data) {
        System.out.println("Log written by "+ thisLoggerLevel +" logger: " + data);
    }
}