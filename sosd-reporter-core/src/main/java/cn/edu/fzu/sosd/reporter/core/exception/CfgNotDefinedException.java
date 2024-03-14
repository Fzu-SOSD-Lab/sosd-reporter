package cn.edu.fzu.sosd.reporter.core.exception;

public class CfgNotDefinedException extends RuntimeException{

    @Override
    public String getMessage() {
        return "No cfg defined for reporter!";
    }
}
