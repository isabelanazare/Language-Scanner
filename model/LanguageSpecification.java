package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguageSpecification {
    private List<String> separators = Arrays.asList("[", "]", "{", "}", "(", ")", ";", ":");
    private List<String> operators = Arrays.asList("+", "-", "*", "/", "%", "<", "<=", "=", ">=", ">", "==", "!=", "++", "--");
    private List<String> reservedWords = Arrays.asList(
            "if", "else", "for", "while", "print", "break",
            "continue", "void", "return", "int", "char", "array");

    private List<String> language = Stream.concat(Stream.concat(separators.stream(), operators.stream()), reservedWords.stream()).collect(Collectors.toList());
    private List<Pair<String, Integer>> codificationTable = new ArrayList<>();

    public LanguageSpecification() {
        for (int i = 0; i < language.size(); i++) {
            this.codificationTable.add(new Pair<String, Integer>(language.get(i), i + 2));
        }
    }

    public List<String> getOperators() {
        return this.operators;
    }

    public List<String> getSeparators() {
        return this.separators;
    }

    public List<Pair<String, Integer>> getCodificationTable() {
        return this.codificationTable;
    }

    public List<String> getLanguage() {
        return this.language;
    }
}
