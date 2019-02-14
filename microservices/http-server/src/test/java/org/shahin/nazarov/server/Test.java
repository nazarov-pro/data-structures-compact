package org.shahin.nazarov.server;

public class Test {
    public static int index = 0;
    public static int maxTests = 1000;

    public static void main(String[] args) {
        for(int i = 0; i < maxTests; i++){
            Thread thread = new Thread(new GetRequest());
            thread.start();
        }
    }

    public synchronized static int getIndex(){
        return index++;
    }
    public synchronized static int success(){
        return maxTests--;
    }

}
