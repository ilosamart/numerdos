package com.tramasoli;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class NumErdos {

    public static void main(String[] args) {
        Integer articles, test=1;
        String erdos = "P. Erdos";
        while (!(articles = articleCount()).equals(0)) {
            Map<String, Integer> authorsErdosNum = new HashMap<>();
            while (articles-- > 0) {
                String line = System.console().readLine();
                line = line.replaceAll(".$","");
                int minErosNum = line.contains(erdos) ? 1: Integer.MAX_VALUE;
                for (String author : line.split(", ")) {
                    for (String key : line.split(", ")) {
                        if (authorsErdosNum.containsKey(key) && authorsErdosNum.get(key)<minErosNum) {
                            minErosNum = authorsErdosNum.get(key)+1;
                        }
                    }
                    if (authorsErdosNum.containsKey(author) && authorsErdosNum.get(author)>minErosNum) {
                        authorsErdosNum.remove(author);
                    }
                    authorsErdosNum.putIfAbsent(author,minErosNum);
                }
            }
            System.out.println("Teste "+test++);
            authorsErdosNum.entrySet().stream()
                                .filter(a -> !a.getKey().equals(erdos))
                                .sorted(Comparator.comparing(e -> e.getKey().split("\\.")[1]))
                                .map(a -> a.getKey()+": "+(a.getValue().equals(Integer.MAX_VALUE)?"infinito":a.getValue()))
                                .forEach(System.out::println);
        }
    }

    private static int articleCount() {
        return Integer.parseInt(System.console().readLine());
    }
}
