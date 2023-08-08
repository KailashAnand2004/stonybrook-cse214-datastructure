/**
 * The main class
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #2 for CSE 214
 * Date:
 *    February 20th, 2023
 */
import java.util.*;
public class PresentationManager
{
    static Scanner input = new Scanner(System.in);

    /**
     * Takes a double input and moves the pointer to the next line
     *
     * @return Returns the double input
     */
    public static double nextDoubleLine()
    {
        double newInput = input.nextDouble();
        input.nextLine();
        return newInput;
    }

    /**
     * Takes an integer input and moves the pointer to the next line
     *
     * @return Returns the integer input
     */
    public static int nextIntLine() {
        int newInput = input.nextInt();
        input.nextLine();
        return newInput;
    }

    public static void main(String[] args) throws EndOfListException {

        String index = "";// To store the user's menu choice
        boolean check = true;// The loop condition. It terminates when the value becomes false at index = "Q"
        SlideList newList = new SlideList();// Initializing a new list

        System.out.println("Welcome to PresentationManager!\n");
        while (check) {
            System.out.println("Please select an option:");
            System.out.println("F) Move cursor forward");
            System.out.println("B) Move cursor backward");
            System.out.println("D) Display cursor slide");
            System.out.println("E) Edit cursor slide");
            System.out.println("P) Print presentation summary");
            System.out.println("A) Append new slide to tail");
            System.out.println("I) Insert new slide before cursor");
            System.out.println("R) Remove slide at cursor");
            System.out.println("H) Reset cursor to head");
            System.out.println("Q) Quit\n");
            System.out.print("Select a menu option: ");
            index = input.nextLine();

            switch (index.toUpperCase()) {
                case "F":
                    try {
                        newList.cursorForward();
                        System.out.println("The cursor moved forward to slide \"" + newList.getCursorSlide().getTitle() + "\"\n");
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move forward\n");
                    }
                    break;
                case "B":
                    try {
                        newList.cursorBackward();
                        System.out.println("The cursor moved backward to slide \"" + newList.getCursorSlide().getTitle() + "\"\n");
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move backward\n");
                    }
                    break;
                case "D":
                    try {
                        if (newList.getCursorSlide() == null) {
                            throw new IllegalArgumentException();
                        }
                        System.out.println("\n==============================================");
                        System.out.println(newList.getCursorSlide().getTitle());
                        System.out.println("==============================================");
                        for (int i = 0; i < newList.getCursorSlide().getNumBullets(); i++) {
                            System.out.println((i + 1) + ". " + newList.getCursorSlide().getBullet(i + 1));
                        }
                        System.out.println("==============================================\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Empty slideshow\n");
                    }
                    break;
                case "E":
                    try {
                        if (newList.size == 0) {
                            throw new IllegalArgumentException();
                        }
                        System.out.print("Edit title,duration or bullets? (t/d/b) ");
                        String edit = input.nextLine();
                        if (edit.equalsIgnoreCase("t")) {
                            System.out.print("Enter the new title: ");
                            newList.getCursorSlide().setTitle(input.nextLine());
                            System.out.println();
                        } else if (edit.equalsIgnoreCase("d")) {
                            try {
                                System.out.print("Enter the new duration: ");
                                newList.getCursorSlide().setDuration(input.nextDouble());
                                input.nextLine();
                                System.out.println("\n");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid Duration\n");
                            }
                        } else if (edit.equalsIgnoreCase("b")) {
                            System.out.print("Bullet index: ");
                            int bulletIndex = input.nextInt();
                            if(bulletIndex > 5 || bulletIndex <= 0)
                            { System.out.println("Invalid index\n");continue; }
                            input.nextLine();
                            System.out.print("Delete or edit? (d/e) ");
                            String option = input.nextLine();
                            if (option.equalsIgnoreCase("d")) {
                                newList.getCursorSlide().setBullets(null, bulletIndex);
                                System.out.println();
                                System.out.println("Bullet " + bulletIndex + " has been deleted");
                            } else if (option.equalsIgnoreCase("e")) {
                                if (bulletIndex > newList.getCursorSlide().getNumBullets()) {
                                    bulletIndex = newList.getCursorSlide().getNumBullets() + 1;
                                }
                                System.out.print("Bullet " + bulletIndex + ": ");
                                newList.getCursorSlide().setBullets(input.nextLine(), bulletIndex);
                                System.out.println();
                            } else {
                                System.out.println("Invalid option\n");
                            }
                        } else {
                            System.out.println("Invalid option\n");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Empty slideshow\n   ");
                    }
                    break;
                case "P":
                    newList.Print();
                    break;
                case "A":
                    try {
                        Slide newSlide = new Slide();
                        System.out.print("Enter the slide title: ");
                        newSlide.setTitle(input.nextLine());
                        System.out.print("Enter the slide duration: ");
                        try {
                            newSlide.setDuration(nextDoubleLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Duration\n");
                            continue;
                        }
                        String s = "";
                        System.out.print("Bullet 1: ");
                        s = input.nextLine();
                        newSlide.setBullets(s, 1);
                        boolean c=true;
                        for (int i = 1; i < 5; i++)
                        {
                            System.out.print("Add another bullet point? (y/n) ");
                            s = input.nextLine();
                            if (s.equals("y"))
                            {
                                System.out.print("Bullet " + (i + 1) + ": ");
                                s = input.nextLine();
                                newSlide.setBullets(s, (i + 1));
                                if (i == 4)
                                {
                                    System.out.println("No more bullets allowed. Slide is full.");
                                    break;
                                }
                            } else if(s.equals("n"))
                            {
                                break;
                            }
                            else {
                                c=false;
                                break;
                            }
                        }
                        if(c==false)
                        {
                            System.out.println("Invalid option");
                        }
                        else {
                            newList.appendToTail(newSlide);
                            System.out.println("\nSlide \"" + newSlide.getTitle() + "\" added to presentation\n");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Slide is null\n");
                    }
                    break;
                case "I":
                    try {
                        Slide newSlide = new Slide();
                        System.out.print("Enter the slide title: ");
                        newSlide.setTitle(input.nextLine());
                        System.out.print("Enter the slide duration: ");
                        try {
                            newSlide.setDuration(nextDoubleLine());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid Duration\n");
                            continue;
                        }
                        String s = "";
                        System.out.print("Bullet 1: ");
                        s = input.nextLine();
                        newSlide.setBullets(s, 1);
                        boolean d =true;
                        for (int i = 1; i < 5; i++)
                        {
                            System.out.print("Add another bullet point? (y/n) ");
                            s = input.nextLine();
                            if (s.equals("y"))
                            {
                                System.out.print("Bullet " + (i + 1) + ": ");
                                s = input.nextLine();
                                newSlide.setBullets(s, (i + 1));
                                if (i == 4)
                                {
                                    System.out.println("No more bullets allowed. Slide is full.");
                                    break;
                                }
                            }
                            else if(s.equals("n"))
                            {
                                break;
                            }
                            else {
                                d=false;
                                break;
                            }
                        }
                        if(d==false)
                        {
                            System.out.println("Invalid option");
                            continue;
                        }
                        else {
                            newList.insertBeforeCursor(newSlide);
                            System.out.println("\nSlide \"" + newSlide.getTitle() + "\" added to presentation\n");
                        }
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println("Slide is null\n");
                    }
                    break;
                case "R":
                    try {
                        System.out.println("Slide \"" + newList.removeCursor().getTitle() + "\" has been removed\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Empty slideshow\n");
                    }
                    break;
                case "H":
                    try {
                        if (newList.size() == 0) {
                            throw new IllegalArgumentException();
                        }
                        newList.resetCursorToHead();
                        System.out.println("Cursor has been reset to the head\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Empty slideshow\n");
                    }
                    break;
                case "Q":
                    check = false;
                    System.out.println("\nProgram terminating normally....");
                    break;

                default :
                    System.out.println("Invalid option");
            }

        }
    }
}