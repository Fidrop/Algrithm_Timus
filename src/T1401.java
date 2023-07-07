import java.util.Scanner;

public class T1401 {
    static int[][] net;
    static int x0, y0;
    static int CurrentValue = 0;
    static int netSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        netSize = sc.nextInt();
        netSize = (int) Math.pow(2, netSize);
        net = new int[512][512];
        x0 = sc.nextInt() - 1;
        y0 = sc.nextInt() - 1;
        fillval(x0,y0,0,0,netSize);
        net[x0][y0] = -1;
        for(int i = 0;i<netSize;i++){
            for(int j = 0;j<netSize;j++){
                if(net[i][j] == 0){
                    CurrentValue+=1;
                    net[i][j] = CurrentValue;
                    if(net[i+1][j] == 0){
                        net[i+1][j] = CurrentValue;
                    }
                    if(net[i][j+1] == 0){
                        net[i][j+1] = CurrentValue;
                    }
                    if(net[i+1][j+1] == 0){
                        net[i+1][j+1] = CurrentValue;
                    }
                    if(net[i+1][j-1] == 0){
                        net[i+1][j-1] = CurrentValue;
                    }
                }
            }
        }
        net[x0][y0] = 0;
        for(int i = 0;i<netSize;i++){
            for(int j = 0;j<netSize-1;j++){
                System.out.print("\t"+net[i][j]+ " ");
            }
            System.out.println("\t"+net[i][netSize-1]);
        }

    }

    public static void fillval(int relX, int relY, int absX, int absY, int length) {
        if (length == 2) {
            CurrentValue += 1;
            net[absX][absY] = CurrentValue;
            net[absX + 1][absY + 1] = CurrentValue;
            net[absX][absY + 1] = CurrentValue;
            net[absX + 1][absY] = CurrentValue;
            net[absX + relX][absY + relY] = 0;
            return;
        }
        //The "0" is located in upper-left.
        int newlength = length/2;
        if (relX < newlength && relY < newlength) {
            fillval(relX, relY, absX, absY, newlength);
            fillval(0,newlength-1,absX+newlength,absY,newlength);
            fillval(newlength-1,0,absX,absY+newlength,newlength);
            fillval(0,0,absX+newlength,absY+newlength,newlength);
        }
        //The "0" is located in lower-left.
        else if( relX>=newlength && relY<newlength ){
            fillval(newlength-1,newlength-1,absX,absY,newlength);
            fillval(relX-newlength,relY,absX+newlength,absY,newlength);
            fillval(newlength-1,0,absX,absY+newlength,newlength);
            fillval(0,0,absX+newlength,absY+newlength,newlength);
        }
        //The "0" is located in upper-right.
        else if(relX<newlength){
            fillval(newlength-1,newlength-1,absX,absY,newlength);
            fillval(0,newlength-1,absX+newlength,absY,newlength);
            fillval(relX,relY-newlength,absX,absY+newlength,newlength);
            fillval(0,0,absX+newlength,absY+newlength,newlength);
        }
        //The "0" is located in lower-right.
        else {
            fillval(newlength-1,newlength-1,absX,absY,newlength);
            fillval(0,newlength-1,absX+newlength,absY,newlength);
            fillval(newlength-1,0,absX,absY+newlength,newlength);
            fillval(relX-newlength,relY-newlength,absX+newlength,absY+newlength,newlength);
        }
    }
}
