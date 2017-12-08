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

public Stream<String> openFile(String name)
{
    try{
        return Files.lines(new File(name).toPath());
    } catch(IOException ioe){
        System.out.println("file error");
    }
    return null;
}



public String part1(String file) throws Exception{
    String instructions[] = openFile(file).toArray(String[]::new);

    HashMap<String, Integer> registers = new HashMap<>();


    int max = 0;
    for(String line: instructions){

        String operation = line.split(" ")[1];
        String resultReg = line.split(" ")[0];
        String op1RegName = line.split(" ")[4] ;

        if(!registers.containsKey(resultReg)){
            registers.put(resultReg, 0);
        }

        if(!registers.containsKey(op1RegName)){
            registers.put(op1RegName, 0);
        }

        int diff = Integer.parseInt( line.split(" ")[2] );
        if(operation.equals("dec")){
            diff = -diff;
        }



        int op1 = registers.get(op1RegName);
        int op2 = Integer.parseInt( line.split(" ")[6]);
        if(
            (line.contains(">=") && op1 >= op2) ||
            (line.contains("<=") && op1 <= op2) ||
            (line.contains("==") && op1 == op2) ||
            (line.contains("!=") && op1 != op2) ||
            (line.contains(">") && op1 > op2) ||
            (line.contains("<") && op1 < op2) 
        ){
            registers.put(resultReg,  
                    registers.get(resultReg) + diff
                ); 
        }

        //part 2
        int currentMax = registers.values().stream().mapToInt(i -> i.intValue()).max().getAsInt();
        if(currentMax > max){
            max = currentMax;
        }
    }
    return "max: " + max + ", lastState: " + registers.values().stream().mapToInt(i -> i.intValue()).max().getAsInt();
}

System.out.println(part1("aoc8.txt"));