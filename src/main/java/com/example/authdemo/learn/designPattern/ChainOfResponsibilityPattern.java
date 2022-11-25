package com.example.authdemo.learn.designPattern;

/**
 * 责任链
 */
public class ChainOfResponsibilityPattern {
    public static AbstractLogger build() {
        ConsoleLogger consoleLogger = new ConsoleLogger(AbstractLogger.ERROR);
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.DEBUG);
        FileLogger fileLogger = new FileLogger(AbstractLogger.INFO);

        consoleLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(fileLogger);

        return consoleLogger;
    }

    public static void main(String[] args) {
        AbstractLogger logger = build();

        logger.logMessage(AbstractLogger.ERROR, "testError");
        System.out.println();
        logger.logMessage(AbstractLogger.DEBUG, "testDebug");
        System.out.println();
        logger.logMessage(AbstractLogger.INFO, "testInfo");
    }

    abstract static class AbstractLogger {
        public static final int INFO = 1;
        public static final int DEBUG = 2;
        public static final int ERROR = 3;

        protected int level;
        protected AbstractLogger nextLogger;

        public void setNextLogger(AbstractLogger logger) {
            this.nextLogger = logger;
        }

        public void logMessage(int level, String message) {
            if (this.level <= level) {
                write(message);
            }
            if (nextLogger != null) {
                nextLogger.logMessage(level, message);
            }
        }

        abstract void write(String message);
    }

    static class ConsoleLogger extends AbstractLogger {

        public ConsoleLogger(int level) {
            this.level = level;
        }

        @Override
        void write(String message) {
            System.out.println("console logger: " + message);
        }
    }

    static class ErrorLogger extends AbstractLogger {

        public ErrorLogger(int level) {
            this.level = level;
        }

        @Override
        void write(String message) {
            System.out.println("error logger: " + message);
        }
    }

    static class FileLogger extends AbstractLogger {

        public FileLogger(int level) {
            this.level = level;
        }

        @Override
        void write(String message) {
            System.out.println("file logger: " + message);
        }
    }
}
