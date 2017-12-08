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

class Program{

    public String name;
    public int weight;
    public LinkedList<Program> childs = new LinkedList<>();
    int totalWeight = -1;

    public Program(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public void addChild(Program child){
        childs.add(child);
    }

    public int getWeight(){
        if(totalWeight < 0){
            totalWeight =  childs.stream().mapToInt(c -> c.getWeight()).sum() + weight;
        }
       return totalWeight;
    }

    public boolean printProblematicChildren(){
        
        for(int i = 0; i < childs.size(); i++){
            if(childs.get(i).printProblematicChildren()){
                return true;
            }
        }

        if(childs.size() > 0 ){
            IntSummaryStatistics summary = childs.stream().mapToInt( c -> c.getWeight()).summaryStatistics();

            int shouldBe = 0;
            if(Math.abs(summary.getAverage() - summary.getMin()) <
               Math.abs(summary.getAverage() - summary.getMax())
            ){
                shouldBe = summary.getMin();
            }else{
                shouldBe = summary.getMax();
            }

            final int siblingWeight = shouldBe;
            Optional<Program> optProgram = childs.stream().filter(c -> c.getWeight() != siblingWeight).findAny();


            if(optProgram.isPresent()){
                Program problem = optProgram.get();
                int newWeight =  problem.weight - (problem.getWeight() - siblingWeight);
                System.out.println(problem.name + " is " +  problem.weight + ", but should be " + newWeight);
                return true;
            }
        }

        return false;
    }
}

public Stream<String> openFile(String name) throws IOException{
    return Files.lines(new File(name).toPath());
}


public String part1(String file) throws IOException{
    String[] lines = openFile(file).toArray(String[]::new);
    
    HashMap<String, String> isChild = new HashMap<>();

    for(String line: lines){
        if(line.indexOf("->") > -1){
            String programDetails[] = line.split(" ");
            for(int i = 3; i < programDetails.length; i++){
                isChild.put(programDetails[i].replace(",",""),"");
            }
        }
    }

    for(String line: lines){
        if(line.indexOf("->") > -1){
            String programDetails[] = line.split(" ");
            if(isChild.get(programDetails[0]) == null){
                return programDetails[0];
            }
        }
    }

    return "";

}

public String part2(String file) throws Exception{

    String[] lines = openFile(file).toArray(String[]::new);
    
    HashMap<String, Program> programms = new HashMap<>();

    for(String line: lines){
        String programDetails[] = line.split(" ");
        programms.put(programDetails[0],
            new Program(programDetails[0],
            Integer.parseInt(programDetails[1].replace("(","").replace(")","")))
        );
    }

    for(String line: lines){
        if(line.indexOf("->") > -1){
            String programDetails[] = line.split(" ");
            Program p = programms.get(programDetails[0]);
            int childCount = 0;
            for(int i = 3; i < programDetails.length; i++){
                p.addChild(programms.get(programDetails[i].replace(",","")));
                childCount++;
            }
            if(p.childs.size() != childCount){
                System.out.println("problem");
            }
            programms.put(p.name, p);
        }
    }

    programms.get(part1(file)).printProblematicChildren();

    return "";
}

System.out.println(part1("aoc7.txt"));
System.out.println(part2("aoc7.txt"));