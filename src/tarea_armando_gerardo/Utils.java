/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_armando_gerardo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

/**
 * 
 * @author Gerardo Vargas Fernández
 * @author Armando Sibaja Elizondo
 */

public class Utils {

    public static String specialChar(String sp, int times) {
        String str = "";
        for (int i = 0; i < times; i++) {
            str += sp;
        }
        str += "\n";
        return str;
    }

    public static void answers() {
        System.out.println(
                specialChar("=", 60)+
                "Questions\n"+
                specialChar("=", 60)
                + "1)Why Java compressing libraries are "
                + "not considered efficient.\n"
                + "If it is true, what are they criticized for?\n"
                + specialChar("=", 60)
                + "\tAnswer:\n"
                + "\tThere are some limitations and concerns associated with zip files.\n"
                + "\tOne of these main concerns is about the possibility through an\n"
                + "\texploit of hiding a big amount of content in a apparently normal\n"
                + "\tfile as a gif.\n"
                + specialChar("=", 60)
                + "2)Which conditions must be achieved to be able to do a binary search?\n"
                + "How many comparisons have to be done in order to find a data in a\n"
                + "15000 position matrix?\n"
                + specialChar("=", 60)
                + "\tAnswer:\n"
                + "\tLet's suppose the worst case scenario, which is where the number is\n"
                + "\tnot in the array.In this case, the algorithm will eventually set\n"
                + "\tthe size of the array to 1 element. The following number list\n"
                + "\trepresents the size of the array per iteration:\n"
                + "\n\t15000-7500-3750-1875-937-468-234-117-58-29-14-7-3-1\n"
                + "\tIn the worst case scenario- the maximum comparison for a 15000\n"
                + "\tposition array would be 14. Which is great!");
    }

    public static void clean(int option) {
        for (int i = 0; i < option; i++) {
            System.out.println("\n");
        }
    }

    public static void searchMenu() throws IOException {
        int[][] myMtr = null;
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String menuIndicator = "#>";
        Boolean errorState = false;
        int menuOption = 0;
        String menu = "======================\n"
                + "Binary Search" + "\n======================"
                + "\n1)Generate matrix\n2)Show matrix\n"
                + "3)Find number\n4)Exit";
        while (menuOption != 4) {
            clean(5);
            System.out.println(menu);
            if (errorState) {
                System.out.print("\n" + menuIndicator + "?? ");
                errorState = false;
            } else {
                System.out.print("\n" + menuIndicator + " ");
            }
            try {
                menuOption = Integer.valueOf(rd.readLine());
            } catch (Exception e) {
                errorState = true;
            }
            switch (menuOption) {
                case 1:
                    clean(10);
                    myMtr = Search.setMatrix();
                    System.out.println(menuIndicator + "Your matrix has been generated!");
                    System.out.print("Press enter key; ");
                    rd.readLine();
                    break;
                case 2:
                    clean(10);
                    if (myMtr == null) {
                        System.out.println("You have not set a matrix yet!");
                    } else {
                        Search.printMatrx(myMtr);
                    }
                    System.out.print("Press enter key; ");
                    rd.readLine();
                    clean(10);
                    break;
                case 3:
                    clean(10);
                    if (myMtr == null) {
                        System.out.println("You have not set a matrix yet!");
                    } else {
                        clean(10);
                        System.out.println("Your matrix:\n");
                        Search.printMatrx(myMtr);
                        System.out.print(menuIndicator + " Write a number: ");
                        Search.binariSearch(myMtr, Integer.valueOf(rd.readLine()));
                        System.out.print("\nPress enter key; ");
                    }
                    rd.readLine();
                    break;
                default:
                    errorState = true;
            }
        }

    }

    public static void zipMenu() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String menuIndicator = "#>";
        Boolean errorState = false;
        int menuOption = 0;
        String menu = "======================\n"
                + "Zip tools" + "\n======================"
                + "\n1)Compress a file\n2)Decompress a file\n"
                + "3)Compress a folder tree\n4)Decompress a folder tree"
                + "\n5)Compress file with password"
                + "\n6)Exit";
        while (menuOption != 6) {
            clean(5);
            System.out.println(menu);
            if (errorState) {
                System.out.print("\n" + menuIndicator + "?? ");
                errorState = false;
            } else {
                System.out.print("\n" + menuIndicator + " ");
            }
            try {
                menuOption = Integer.valueOf(rd.readLine());
            } catch (Exception e) {
                errorState = true;
            }
            switch (menuOption) {
                case 1:
                    clean(10);
                    System.out.print(menuIndicator + " File name: ");
                    Zip.zipFile(rd.readLine());
                    break;
                case 2:
                    clean(10);
                    System.out.print(menuIndicator + " File name: ");
                    String zipFilePath = rd.readLine();
                    //String destDir = rd.readLine();
                    String destDir = "./";
                    Zip.unzip(zipFilePath, destDir);
                    break;
                case 3:
                    clean(10);
                    System.out.print(menuIndicator + " Write a folder path: ");
                    String p = rd.readLine();
                    Zip.zipp(p);
                    break;
                case 4:
                    clean(10);
                    System.out.print(menuIndicator + " File path: ");
                    zipFilePath = rd.readLine();
                    System.out.print(menuIndicator + "destination path: ");
                    destDir = rd.readLine();
                    Zip.unzip(zipFilePath, destDir);
                    break;
                case 5:
                    clean(10);
                    System.out.print(menuIndicator + " File name: ");
                    String fileName = rd.readLine();
                    System.out.print(menuIndicator + " Password: ");
                    String password = rd.readLine();
                    Zip.zipWithPassword(fileName, password);
                    break;
                default:
                    errorState = true;
            }
        }
    }

    public static void mainMenu() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        String menuIndicator = "#>";
        Boolean errorState = false;
        int menuOption = 0;
        LocalDate myObj = LocalDate.now();
        String menu = "Today is " + myObj + "\n======================\n"
                + "Select an option" + "\n======================"
                + "\n1)Zip Tools\n2)Binary Search\n3)Questions\n4)Exit";
        while (menuOption != 4) {
            clean(10);
            System.out.println(menu);
            if (errorState) {
                System.out.print("\n" + menuIndicator + "?? ");
                errorState = false;
            } else {
                System.out.print("\n" + menuIndicator + " ");
            }
            try {
                menuOption = Integer.valueOf(rd.readLine());
            } catch (Exception e) {
                errorState = true;
            }
            switch (menuOption) {
                case 1:
                    zipMenu();
                    break;
                case 2:
                    searchMenu();
                    break;
                case 3:
                    clean(10);
                    answers();
                    System.out.print("\n" + menuIndicator + " ");
                    rd.readLine();
                    break;
                default:
                    errorState = true;
            }
        }
        clean(10);
        System.out.println("Bye!");

    }
}
