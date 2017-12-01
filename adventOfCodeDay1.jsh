public int part1(String captcha){
    return countByStep(captcha, 1);
}

public int part2(String captcha){
    return countByStep(captcha, captcha.length());
}

public int countByStep(String captcha, int step){
    int equalNeighbours = 0;
    for(int i = 0; i < captcha.length(); i++){
        int nextI = (i + step)%captcha.length();
        if(captcha.charAt(i) == captcha.charAt(nextI)){
            equalNeighbours+=(captcha.charAt(i)-'0');
        }
    }
    return equalNeighbours;
}