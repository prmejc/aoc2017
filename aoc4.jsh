import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public Stream<String> openFile(String name) throws IOException{
    return Files.lines(new File(name).toPath());
}

public boolean areWordsUnique(String line){
    return Arrays.stream(line.split(" "))
                    .collect(Collectors.toSet())
                    .size() 
            == line.split(" ").length;
}

public boolean areWordsUniqueWithoutAnagrams(String line){
    return Arrays.stream(line.split(" "))
                 .map(word -> word.chars()
                                  .sorted()
                                  .mapToObj(c -> (char)c+"")
                                  .collect(Collectors.joining(""))
                 )
                 .collect(Collectors.toSet()).size()
           == line.split(" ").length;
}

public long part1(String phraseFile) throws IOException {
    return openFile(phraseFile).filter(line -> areWordsUnique(line)).count();
}

public long part2(String phraseFile) throws IOException {
    return openFile(phraseFile).filter(line -> areWordsUniqueWithoutAnagrams(line)).count();
}

System.out.println(part1("passPhrases.txt"));
System.out.println(part2("passPhrases.txt"));