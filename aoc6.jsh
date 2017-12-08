import java.io.File;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;

public String id(int cells[]){
    String id = "";
    for(int i = 0; i < cells.length; i++)
        id += ", " + cells[i];
    return id;
}

String cellsString = "2	8	8	5	4	2	3	1	5	5	1	2	15	13	5	14";
int cells[] =  Arrays.stream(cellsString.split("	")).mapToInt(l -> Integer.parseInt(l.trim())).toArray()


HashMap<String, Integer> seenBefore = new HashMap<String, Integer>();

seenBefore.put(id(cells),0);

int cycles = 0;
while(true){

    int maxIndex = 0;
    int maxBlocks = 0;
    for(int i = 0; i < cells.length; i++){
        if(cells[i] > maxBlocks){
            maxBlocks = cells[i];
            maxIndex = i;
        }
    }

    int blocksToRedistribute = cells[maxIndex];
    cells[maxIndex] = 0;
    int blocksPerCell = (int)Math.ceil(new Double(blocksToRedistribute)/cells.length);

    for(int i = 0; i < cells.length; i++){
        int index = ((maxIndex + 1 + i)%cells.length);
        cells[index] += blocksPerCell;
        blocksToRedistribute = blocksToRedistribute - blocksPerCell;
        if(blocksToRedistribute <= blocksPerCell){
            blocksPerCell = blocksToRedistribute;
        }
        
    }
    cycles++;

    String id = id(cells);

    if(seenBefore.get(id) != null){
        break;
    }
    seenBefore.put(id, cycles);

}

String id = id(cells);

System.out.println(cycles - seenBefore.get(id));