package loggers;

public abstract class Logger {
    protected final String ERROR = "error";
    protected final String INFO = "info";
    protected final String DEBUG = "debug";
    protected final String TRACE = "trace";

    protected Logger nextLogger;
    protected String thisLoggerLevel;

    public Logger(String thisLoggerLevel, Logger nextLogger) {
        this.thisLoggerLevel = thisLoggerLevel;
        this.nextLogger = nextLogger;
    }

    public void log(String level, String data) {
        if (thisLoggerLevel.equalsIgnoreCase(level)) {
            write(data);
            return;
        }

        if(this.nextLogger != null) {
            this.nextLogger.log(level, data);
        }
    }

    public abstract void write(String data);
}
