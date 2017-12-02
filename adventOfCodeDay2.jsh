import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.io.IOException;
import java.util.Arrays;

public Stream<String> openFile(String name) throws IOException{
    return Files.lines(new File(name).toPath());
}

public int part1(String line) {
    return 
        Arrays.stream(line.split("	")).map((e) -> Integer.parseInt(e)).max((i1,i2) -> i1 - i2).get()
        - Arrays.stream(line.split("	")).map((e) -> Integer.parseInt(e)).min((i1,i2) -> i1 - i2).get()
    ;
}

public int part2(String line) {
    String[] nums = line.split("	");
    for(int i = 0; i < nums.length; i++){
        double first = Double.parseDouble(nums[i]);
        for(int j = 0; j < nums.length; j++)
        {
            double second = Double.parseDouble(nums[j]);
            if((first/second) % 1 == 0 && i != j){
                return new Double(first/second).intValue();
            }
        }
    }
    return 0;
}

System.out.println(openFile("spread.txt").mapToInt(l -> part1(l)).sum());
System.out.println(openFile("spread.txt").mapToInt(l -> part2(l)).sum());