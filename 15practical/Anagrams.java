import java.io.*;
import java.util.*;

public class Anagrams {

    public static void main(String[] args) {
        // Check command line argument
        if (args.length != 1) {
            System.out.println("Usage: java Anagrams <inputfile>");
            System.out.println("Example: java Anagrams ulysses.numbered");
            return;
        }

        String inputfile = args[0];
        File file = new File(inputfile);

        // Check if file exists
        if (!file.exists()) {
            System.err.println("ERROR: File '" + inputfile + "' not found!");
            System.err.println("Current directory: " + System.getProperty("user.dir"));
            return;
        }

        System.out.println("Data file: " + inputfile);

        // Read file and build word frequency map D
        Map<String, Integer> D = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputfile))) {
            String line;
            int lines = 0;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String w : words) {
                    String cleaned = cleanWord(w);

                    if (!cleaned.isEmpty()) {
                        if (D.containsKey(cleaned)) {
                            D.put(cleaned, D.get(cleaned) + 1);
                        } else {
                            D.put(cleaned, 1);
                        }
                    }
                }
                lines++;
            }

            System.out.println("Processed " + lines + " lines");
            System.out.println("Found " + D.size() + " unique words");

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Build anagram map A using signatures
        Map<String, List<String>> A = new HashMap<>();

        for (String word : D.keySet()) {
            String sig = signature(word);

            if (!A.containsKey(sig)) {
                List<String> wordList = new ArrayList<>();
                wordList.add(word);
                A.put(sig, wordList);
            } else {
                A.get(sig).add(word);
            }
        }

        // Create all permutations of lists and write to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("anagrams.txt"))) {
            for (Map.Entry<String, List<String>> entry : A.entrySet()) {
                List<String> words = entry.getValue();
                if (words.size() > 1) {
                    String anagramlist = String.join(" ", words);

                    // Print original order
                    writer.println(anagramlist + "\\\\");

                    // Print rotations (permutations)
                    for (int i = 0; i < words.size() - 1; i++) {
                        int spaceIndex = anagramlist.indexOf(' ');
                        anagramlist = anagramlist.substring(spaceIndex + 1) + " " +
                                anagramlist.substring(0, spaceIndex);
                        writer.println(anagramlist + "\\\\");
                    }
                }
            }
            System.out.println("Anagrams written to: anagrams.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Sort and display results
        try {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("anagrams.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            Collections.sort(lines);

            System.out.println("\n" + "=".repeat(50));
            System.out.println("SORTED ANAGRAMS:");
            System.out.println("=".repeat(50));

            char currentLetter = 'X';
            for (String lemma : lines) {
                if (!lemma.isEmpty()) {
                    char initial = lemma.charAt(0);
                    if (Character.toLowerCase(initial) != Character.toLowerCase(currentLetter)) {
                        currentLetter = initial;
                        System.out.println("\n" + Character.toUpperCase(initial) + ":");
                    }
                    System.out.println("  " + lemma);
                }
            }

        } catch (IOException e) {
            System.err.println("Error sorting results: " + e.getMessage());
        }
    }

    /**
     * Clean a word by removing punctuation but keeping apostrophes
     */
    public static String cleanWord(String word) {
        // Remove numbers and punctuation (but keep apostrophes)
        return word.replaceAll("[^a-zA-Z']", "").toLowerCase();
    }

    /**
     * Create signature by sorting letters alphabetically
     */
    public static String signature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}