public int part1(String captcha){
    int equalNeighbours = 0;
    for(int i = 0; i < captcha.length(); i++){
        int nextI = (i + 1)%captcha.length();
        if(captcha.charAt(i) == captcha.charAt(nextI)){
            equalNeighbours += captcha.charAt(i) - '0';
        }
    }
    return equalNeighbours;
}


public int part2(String captcha){
    int equalNeighbours = 0;
    for(int i = 0; i < captcha.length(); i++){
        int nextI = (i + (captcha.length()/2))%captcha.length();
        if(captcha.charAt(i) == captcha.charAt(nextI)){
            equalNeighbours+=(captcha.charAt(i)-'0');
        }
    }
    return equalNeighbours;
}