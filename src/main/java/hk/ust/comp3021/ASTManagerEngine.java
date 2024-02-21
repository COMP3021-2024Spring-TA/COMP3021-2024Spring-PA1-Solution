package hk.ust.comp3021;

import java.util.*;

public class ASTManagerEngine {
	public ASTManagerEngine() {
		
	}
	
	public void userInterface() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("ASTManager is running...");
        
        while (true) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please select the following operations with the corresponding numbers:");
            System.out.println("  0: Register an account");
            System.out.println("  1: Search papers");
            System.out.println("  2: Upload papers");
            System.out.println("  3: Download papers");
            System.out.println("  4: Add labels");
            System.out.println("  5: Add comments");
            System.out.println("  6: Exit");
            System.out.println("----------------------------------------------------------------------");
            Scanner scan1 = new Scanner(System.in);
            if (scan1.hasNextInt()) {
                int i = scan1.nextInt();
                if (i < 0 || i > 6) {
                    System.out.println("You should enter 0~6.");
                    continue;
                }
               
                
                if (i == 6) break;
            } else {
                System.out.println("You should enter integer 0~6.");
            }
        }
    }
}
