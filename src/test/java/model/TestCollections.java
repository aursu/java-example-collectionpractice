package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollections {

    @Test @Disabled
    void testPrintList() {
        //todo Распечатать содержимое используя for each
    }

    @Test @Disabled
    void testChangeWeightOfFirstByOne() {
        //todo Изменить вес первой коробки на 1.
        assertEquals(new HeavyBox(1,2,3,5), list.get(0));
    }

    @Test @Disabled
    void testDeleteLast() {
        //todo Удалить предпоследнюю коробку.
        assertEquals(6, list.size());
        assertEquals(new HeavyBox(1,2,3,4), list.get(0));
        assertEquals(new HeavyBox(1,3,3,4), list.get(list.size()-2));
    }

    @Test @Disabled
    void testConvertToArray() {
        //todo Получить массив содержащий коробки из коллекции тремя способами и вывести на консоль.
        HeavyBox[] arr = null;
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

    @Test @Disabled
    void testDeleteBoxesByWeight() {
        // todo удалить все коробки, которые весят 4
        assertEquals(3, list.size());
    }

    @Test @Disabled
    void testSortBoxesByWeight() {
        // отсортировать коробки по возрастанию веса. При одинаковом весе - по возрастанию объема
        assertEquals(new HeavyBox(1,1,1,1), list.get(0));
        assertEquals(new HeavyBox(2,3,4,7), list.get(6));
        assertEquals(new HeavyBox(1,2,3,4), list.get(3));
        assertEquals(new HeavyBox(1,3,3,4), list.get(4));
    }

    @Test @Disabled
    void testClearList() {
        //todo Удалить все коробки.
        assertTrue(list.isEmpty());
    }

    @Test @Disabled
    void testReadAllLinesFromFileToList() {
        // todo Прочитать все строки в коллекцию
        List<String> lines = null;
        assertEquals(19, lines.size());
        assertEquals("", lines.get(8));
    }

    @Test @Disabled
    void testReadAllWordsFromFileToList() throws IOException {
        // todo прочитать все строки, разбить на слова и записать в коллекцию
        List<String> words = readAllWordsFromFileToList();
        assertEquals(257, words.size());
    }

    List<String> readAllWordsFromFileToList() {

        return null;
    }

    @Test @Disabled
    void testFindLongestWord() {
        // todo Найти самое длинное слово
        assertEquals("conversations", findLongestWord());
    }

    private String findLongestWord() {
        return null;
    }

    @Test @Disabled
    void testAllWordsByAlphabetWithoutRepeat() {
        // todo Получить список всех слов по алфавиту без повторов
        List<String> result = null;

        assertEquals("alice", result.get(5));
        assertEquals("all", result.get(6));
        assertEquals("without", result.get(134));
        assertEquals(138, result.size());
    }

    @Test @Disabled
    void testFindMostFrequentWord() {
        // todo Найти самое часто вcтречающееся слово
        assertEquals("the", mostFrequentWord());
    }

    @Test @Disabled
    void testFindWordsByLengthInAlphabetOrder() throws IOException {
        // todo получить список слов, длиной не более 5 символов, переведенных в нижний регистр, в порядке алфавита, без повторов
        List<String> strings = null;

        assertEquals(202, strings.size());
        assertEquals("a", strings.get(0));
        assertEquals("alice", strings.get(10));
        assertEquals("would", strings.get(strings.size() - 1));
    }

    private String mostFrequentWord() {
        return null;
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
