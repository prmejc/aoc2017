import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public Stream<String> openFile(String name) throws IOException{
    return Files.lines(new File(name).toPath());
}

public boolean areWordsUnique(String line){
    Set<String> unique = new HashSet<String>();
    String []words = line.split(" ");
    for(String word: words){
        unique.add(word);
    }
    return unique.size() == words.length;
}
public boolean areWordsUniqueWithoutAnagrams(String line){
    Set<String> unique = new HashSet<String>();
    String []words = line.split(" ");
    for(String word: words){
        unique.add(
                //alphabetically ordered characters as String
                word.chars()
                    .sorted()
                    .mapToObj(c -> (char)c+"")
                    .collect(Collectors.joining(""))
        );
    }
    return unique.size() == words.length;
}

public int part1(String phraseFile) throws IOException {
    Stream<String> lines = openFile(phraseFile);
    return lines.filter(l -> areWordsUnique(l)).mapToInt(l -> 1).sum();
}

public int part2(String phraseFile) throws IOException {
    Stream<String> lines = openFile(phraseFile);
    return lines.filter(l -> areWordsUniqueWithoutAnagrams(l)).mapToInt(l -> 1).sum();
}

System.out.println(part1("passPhrases.txt"));
System.out.println(part2("passPhrases.txt"));