import loggers.*;

public class App {
    public static void main(String[] args) throws Exception {
        Logger traceLogger = new TraceLogger("trace", null);
        Logger debugLogger = new DebugLogger("debug", traceLogger);
        Logger infoLogger = new InfoLogger("info", debugLogger);
        Logger logger = new ErrorLogger("error", infoLogger);

        logger.log("error", "this is an error log");
        logger.log("trace", "this is a trace log");
        logger.log("debug", "this is a debug log");
        logger.log("info", "this is an info log");

        logger.log("debug", "this is an error log");
    }
}
