enum Direction{
    UP{
         public int nextY(int y){return y-1;}
         public Direction nextDirection(){
            return LEFT;
        }
    },
    DOWN{
        public int nextY(int y){return y+1;}

        public Direction nextDirection(){
            return RIGHT;
        }
    },
    LEFT{
        public int nextX(int x){return x-1;}
        
        public Direction nextDirection(){
            return DOWN;
        }
    },
    RIGHT{
        public int nextX(int x){return x+1;}
        
        public Direction nextDirection(){
            return UP;
        }
    };

    public int lookX(int x) { return nextDirection().nextX(x); }
    public int lookY(int y) { return nextDirection().nextY(y); } 
    public int nextX(int x) { return x;}
    public int nextY(int y) { return y;}

    public Direction nextDirection(){
        return UP;
    }
}



public int part1(int cellNumber){
    int dimension = (int)Math.sqrt(cellNumber)+10;
    
    if(dimension % 2 == 0){
        dimension++;
    }
    int grid[][] = new int[dimension][dimension];
    int x=dimension/2, y=dimension/2;
    Direction dir = Direction.DOWN;

    for(int i = 1; i < cellNumber; i++){
        grid[x][y] = 1;
        int lookUpX = dir.lookX(x);
        int lookUpY = dir.lookY(y);
        if(grid[lookUpX][lookUpY] == 0){
            dir = dir.nextDirection();
        }
        x = dir.nextX(x);
        y = dir.nextY(y);
    }

    return Math.abs((dimension/2)-x) + 
           Math.abs((dimension/2)-y);
}

public int getNeigbourSum(int x, int y, int grid[][]){
    int sum = 0;
    for(int i=-1;i<2;i++)
        for(int j=-1;j<2;j++)
            sum += grid[x+i][y+j];
    if(sum==0) return 1;
    return sum;
}

public int part2(int cellNumber){
    int dimension = (int)Math.sqrt(cellNumber)+10;
    
    if(dimension % 2 == 0){
        dimension++;
    }
    int grid[][] = new int[dimension][dimension];
    int x=dimension/2, y=dimension/2;
    Direction dir = Direction.DOWN;

    for(int i = 1; i < cellNumber; i++){
        int sum = getNeigbourSum(x, y, grid);
        if(sum > cellNumber){
            return sum;
        }
        grid[x][y] = sum;
        
        int lookUpX = dir.lookX(x);
        int lookUpY = dir.lookY(y);
        if(grid[lookUpX][lookUpY] == 0){
            dir = dir.nextDirection();
        }
        x = dir.nextX(x);
        y = dir.nextY(y);
    }

    return Math.abs((dimension/2)-x) + 
           Math.abs((dimension/2)-y);
}



System.out.println(part1(368078));
System.out.println(part2(368078));