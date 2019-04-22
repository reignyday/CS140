package assignment08;

// copies from the Wiley website: the Worked Example is at wiley.com/go/bjeo6examples
// and the code is in the file downloaded from 
// <a href="http://higheredbcs.wiley.com/legacy/college/horstmann/1119056446/sc/bigjava.tar.gz">http://higheredbcs.wiley.com/legacy/college/horstmann/1119056446/sc/bigjava.tar.gz</a>
// In your git-bash window you can use "gunzip" and "tar -xvf" to extract the files and
// maybe Eclipse knows how to extract it also.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Words {      
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            long count = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .count();
            System.out.println("Number of words with all five vowels: " + count);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> examples = lines 
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .limit(20)
                    .collect(Collectors.toList());
            System.out.println("20 words with all five vowels: " + examples);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            String shortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .min(Comparator.comparing(String::length))
                    .orElse("");
            System.out.println("Shortest: " + shortest);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> allShortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .filter(w -> w.length() == 7)
                    .collect(Collectors.toList());
            System.out.println("All of that length: " + allShortest);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            String longest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasFiveVowels(w))
                    .max(Comparator.comparing(String::length))
                    .orElse("");

            System.out.println("Longest: " + longest);
        }

        System.out.println(countVowels("counterrevolutionaries"));
        System.out.println("Expected : {a=1, e=3, u=2, i=2, o=3}");

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            long count = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasExactlyFiveVowels(w))
                    .count();
            System.out.println("Number of words with exactly five vowels: " + count);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> examples = lines 
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasExactlyFiveVowels(w))
                    .limit(20)
                    .collect(Collectors.toList());
            System.out.println("20 words with exactly five vowels: " + examples);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            String shortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasExactlyFiveVowels(w))
                    .min(Comparator.comparing(String::length))
                    .orElse("");
            System.out.println("Shortest: " + shortest);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            List<String> allShortest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasExactlyFiveVowels(w))
                    .filter(w -> w.length() == 7)
                    .collect(Collectors.toList());
            System.out.println("All of that length: " + allShortest);
        }

        try (Stream<String> lines = Files.lines(Paths.get("words.txt"))) {
            String longest = lines
                    .filter(w -> !w.endsWith("'s"))
                    .filter(w -> hasExactlyFiveVowels(w))
                    .max(Comparator.comparing(String::length))
                    .orElse("");

            System.out.println("Longest: " + longest);
        }
    }

    public static boolean hasFiveVowels(String word) {
        return word.toLowerCase().codePoints()
                .filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                .distinct()
                .count() == 5;
    }

    public static Map<Character, Long> countVowels(String word)
    {
        return word.toLowerCase().chars()
                .mapToObj(n -> (char)n)
                .filter(c -> c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static boolean hasExactlyFiveVowels(String word)
    {
        Map<Character,Long> comp = Map.of('a', 1L, 'e', 1L, 'i', 1L, 'o', 1L, 'u', 1L);

        return countVowels(word).equals(comp);
    }
}
