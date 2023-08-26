package com.md.advice;



import java.util.List;
import java.util.Map;

public class ValidationException extends RuntimeException {
    private Map<String, List<String>> errors;
   public ValidationException(Map<String, List<String>> errors) {
       super(errors.toString());
       this.errors = errors;
   }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
