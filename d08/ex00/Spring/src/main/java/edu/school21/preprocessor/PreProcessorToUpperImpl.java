package edu.school21.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String process(String data) {
        return data.toUpperCase();
    }
}
