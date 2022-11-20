package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollections {

    @Test
    void testPrintList() {
        //todo Распечатать содержимое используя for each
        for (HeavyBox e : list) {
            System.out.println(e);
        }
    }

    @Test
    void testChangeWeightOfFirstByOne() {
        //todo Изменить вес первой коробки на 1.
        HeavyBox first = list.get(0);
        first.setWeight(first.getWeight() + 1);
        assertEquals(new HeavyBox(1,2,3,5), list.get(0));
    }

    @Test
    void testDeleteLast() {
        //todo Удалить предпоследнюю коробку.
        list.remove(list.size() - 1);

        assertEquals(6, list.size());
        assertEquals(new HeavyBox(1,2,3,4), list.get(0));
        assertEquals(new HeavyBox(1,3,3,4), list.get(list.size()-2));
    }

    @Test
    void testConvertToArray() {
        //todo Получить массив содержащий коробки из коллекции тремя способами и вывести на консоль.
        HeavyBox[] arr = list.toArray(new HeavyBox[0]);

        assertArrayEquals(new HeavyBox[]{
                new HeavyBox(1,2,3,4),
                new HeavyBox(3,3,3,4),
                new HeavyBox(2,6,5,3),
                new HeavyBox(2,3,4,7),
                new HeavyBox(1,3,3,4),
                new HeavyBox(1,2,3,4),
                new HeavyBox(1,1,1,1)
        }, arr);
    }

    @Test
    void testDeleteBoxesByWeight() {
        // todo удалить все коробки, которые весят 4
        list.removeIf(o -> (o.getWeight() == 4));
        assertEquals(3, list.size());
    }

    @Test
    void testSortBoxesByWeight() {
        // отсортировать коробки по возрастанию веса. При одинаковом весе - по возрастанию объема
        list.sort(Comparator.comparing(HeavyBox::getWeight).thenComparing(HeavyBox::getVolume));

        assertEquals(new HeavyBox(1,1,1,1), list.get(0));
        assertEquals(new HeavyBox(2,3,4,7), list.get(6));
        assertEquals(new HeavyBox(1,2,3,4), list.get(3));
        assertEquals(new HeavyBox(1,3,3,4), list.get(4));
    }

    @Test
    void testClearList() {
        //todo Удалить все коробки.
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testReadAllLinesFromFileToList() {
        // todo Прочитать все строки в коллекцию
        List<String> lines = new ArrayList<>();
        String line;
        while(true) {
            try {
                if ((line = reader.readLine()) == null) break;
                lines.add(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        assertEquals(19, lines.size());
        assertEquals("", lines.get(8));
    }

    @Test
    void testReadAllWordsFromFileToList() {
        // todo прочитать все строки, разбить на слова и записать в коллекцию
        List<String> words = readAllWordsFromFileToList();
        assertEquals(257, words.size());
    }

    List<String> readAllWordsFromFileToList() {
        List<String> words = new ArrayList<>();
        String line;
        while(true) {
            try {
                if ((line = reader.readLine()) == null) break;
                if (line.isEmpty()) continue;
                words.addAll(List.of(line.split(REGEXP)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return words;
    }

    List<String> readAllWordsFromFileToList(boolean uniq) {
        List<String> words = readAllWordsFromFileToList();
        List<String> result = new ArrayList<>();
        if (uniq) {
            Set<String> uniqSet = new TreeSet<>();

            for (String word : words) uniqSet.add(word.toLowerCase());

            result = new ArrayList<>(uniqSet);
        }
        else for (String word : words) result.add(word.toLowerCase());

        result.sort(Comparator.naturalOrder());

        return result;
    }

    @Test
    void testFindLongestWord() {
        // todo Найти самое длинное слово
        assertEquals("conversations", findLongestWord());
    }

    private String findLongestWord() {

        String max = "";
        for (String w: readAllWordsFromFileToList()) if (w.length() > max.length()) max = w;

        return max;
    }

    @Test
    void testAllWordsByAlphabetWithoutRepeat() {
        // todo Получить список всех слов по алфавиту без повторов

        List<String> result = readAllWordsFromFileToList(true);

        assertEquals("alice", result.get(5));
        assertEquals("all", result.get(6));
        assertEquals("without", result.get(134));
        assertEquals(138, result.size());
    }

    @Test
    void testFindMostFrequentWord() {
        // todo Найти самое часто вcтречающееся слово
        assertEquals("the", mostFrequentWord());
    }

    @Test
    void testFindWordsByLengthInAlphabetOrder() {
        // todo получить список слов, длиной не более 5 символов, переведенных в нижний регистр, в порядке алфавита, без повторов
        List<String> strings = new ArrayList<>();
        for (String w: readAllWordsFromFileToList(false)) if (w.length() <=  5) strings.add(w);

        assertEquals(202, strings.size());
        assertEquals("a", strings.get(0));
        assertEquals("alice", strings.get(10));
        assertEquals("would", strings.get(strings.size() - 1));
    }

    private String mostFrequentWord() {
        List<String> words = readAllWordsFromFileToList(false);

        TreeMap<String, Integer> wordsMap = new TreeMap<>();

        for (String w: words)
            wordsMap.merge(w, 1, (oldValue, newValue) -> oldValue + 1 );

        Map.Entry<String, Integer> maxEntry = null;

        for(Map.Entry<String, Integer> entry: wordsMap.entrySet())
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) maxEntry = entry;

        if (maxEntry == null) return null;

        return maxEntry.getKey();
    }

    List<HeavyBox> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>(List.of(
                new HeavyBox(1,2,3,4),
                new HeavyBox(3,3,3,4),
                new HeavyBox(2,6,5,3),
                new HeavyBox(2,3,4,7),
                new HeavyBox(1,3,3,4),
                new HeavyBox(1,2,3,4),
                new HeavyBox(1,1,1,1)
        ));
    }

    static final String REGEXP = "\\W+"; // for splitting into words

    private BufferedReader reader;

    @BeforeEach
    public void setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(
                Paths.get("Text.txt"), StandardCharsets.UTF_8);
    }

    @AfterEach
    public void closeBufferedReader() throws IOException {
        reader.close();
    }
}
