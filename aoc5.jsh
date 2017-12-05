import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.stream.Stream;

public int getStepCount(String fileName, int threshold) throws IOException{
    int p[] = Files.lines(new File(fileName).toPath())
                        .mapToInt(l -> Integer.parseInt(l.trim()))
                        .toArray();

    int stepCount = 0;
    int prevPointer = 0;
    for(int pointer = 0; pointer < p.length; stepCount++){
        prevPointer = pointer;
        pointer += p[pointer];
        if(p[prevPointer] >= threshold){
            p[prevPointer] -= 1;
        }else{
            p[prevPointer] += 1;
        }
    }
    return stepCount;
}

public int part1(String fileName) throws IOException{
    return getStepCount(fileName, Integer.MAX_VALUE);
}

public int part2(String fileName) throws IOException{
        return getStepCount(fileName, 3);
}

System.out.println(part1("aoc5.txt"));
System.out.println(part2("aoc5.txt"));


