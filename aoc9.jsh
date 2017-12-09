import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.function.Consumer;

class GroupAccomulator implements Consumer<Character>{

    enum Exclamation{
        EXCLAMATION,
        NO_EXCLAMATION
    }

    enum Garbage{
        GARBAGE,
        NO_GARBAGE
    }

    Exclamation exclState = Exclamation.NO_EXCLAMATION;
    Garbage garbageState = Garbage.NO_GARBAGE;

    int groupLevel = 0;

    int groupSum = 0;

    int garbageSum = 0;

    public void accept(Character c){

        if(exclState == Exclamation.EXCLAMATION){
            exclState = Exclamation.NO_EXCLAMATION;
            return;
        }

        if(c == '!'){
            exclState = Exclamation.EXCLAMATION;
            return;
        }

       if(garbageState == Garbage.GARBAGE){
            garbageSum++;
        }

        if(c == '<'){
            garbageState = Garbage.GARBAGE;
        }

        if(c == '>'){
            garbageSum--;
            garbageState = Garbage.NO_GARBAGE;
        }

        if(garbageState == Garbage.NO_GARBAGE){
            if(c == '{'){
                groupLevel++;
            }

            if(c == '}'){
                groupSum += groupLevel;
                groupLevel--;
            } 
        }
    }

    public int getSum(){
        return groupSum;
    }
    public int getGarbage(){
        return garbageSum;
    }
}


public Stream<String> openFile(String name){
    try{
        return Files.lines(new File(name).toPath());
    } catch(IOException e){
        System.out.println("something wung");
    }
    return null;
}

public String part1(String fileName){
    openFile(fileName).forEach(
        l ->
        {
            GroupAccomulator groups = new GroupAccomulator();
            for(char c: l.toCharArray()){
                groups.accept(c);
            }
            System.out.println("group score: " + groups.getSum());
            System.out.println("group garbage: " + groups.getGarbage());
        }
    );

    return "done";
}

part1("aoc9.txt");