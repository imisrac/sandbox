package codingame;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CrosswordTest {

    private Crossword crossword;

    @Test
    public void example() {
        crossword = new Crossword("SIN", "BUT", "SOB", "NOT");

        assertThat(crossword.printCrossword(),
                is("SIN\n" +
                        "O.O\n" +
                        "BUT"));
    }

    @Test
    public void color_mix() {
        crossword = new Crossword("GREEN", "YELLOW", "ORANGE", "INDIGO");

        assertThat(crossword.printCrossword(),
                is(".O..I.\n" +
                        "GREEN.\n" +
                        ".A..D.\n" +
                        ".N..I.\n" +
                        ".G..G.\n" +
                        "YELLOW"));
    }

    @AllArgsConstructor
    private static class Crossword {
        private final String h1;
        private final String h2;
        private final String v1;
        private final String v2;

        public String printCrossword() {
            int rows = Math.max(v1.length(), v2.length());
            int columns = Math.max(h1.length(), h2.length());
            Character[][] crossword = new Character[rows][columns];
            for (int i = 0; i < h1.length(); i++) {
                crossword[0][i] = h1.charAt(i);
                if (v1.charAt(0) == h1.charAt(i)) {
                    for (int j = 1; j < v1.length(); j++) {
                        crossword[j][i] = v1.charAt(j);
                    }
                } else if (v2.charAt(0) == h1.charAt(i)) {
                    for (int j = 1; j < v2.length(); j++) {
                        crossword[j][i] = v2.charAt(j);
                    }
                } else {
                    for (int j = 1; j < rows; j++) {
                        crossword[j][i] = '.';
                    }
                }
            }
            for (int i = 1; i < rows; i++) {
                if (h2.charAt(0) == crossword[i][0]) {
                    for (int j = 0; j < h2.length(); j++) {
                        crossword[i][j] = h2.charAt(j);
                    }
                }
            }

            StringBuilder printedVersion = new StringBuilder();
            for (Character[] characters : crossword) {
                for (Character character : characters) {
                    printedVersion.append(character);
                }
                printedVersion.append("\n");
            }
            return printedVersion.toString().trim();
        }
    }
}
