import java.util.Scanner;
public class T1494 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] stack = new int[num + 1];
        int top = 0;
        int now = 1;
        boolean isProof = true;
        for (int i = 0; i < num; i++) {
            int ball = scanner.nextInt();
            while (stack[top] != ball && now <= num) {
                top++;
                stack[top] = now++;
            }
            if (stack[top] == ball && top > 0) {
                top--;
            } else {
                isProof = false;
                break;
            }
        }
        if (isProof) {
            System.out.println("Not a proof");
        } else {
            System.out.println("Cheater");
        }
    }
}















