/**
 * The list class which is used to implement the methods of the list
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #2 for CSE 214
 * Date:
 *    February 20th, 2023
 */
public class SlideList
{
    private SlideListNode head;//Head of the list
    private SlideListNode tail;//Tail of the list
    private SlideListNode cursor;//Cursor points to the current list
    int size = 0;//To store the size of the list
    int track = 1;//To keep track of the cursor

    /**
     *  Default constructor which initializes this object to an empty list of Slides.
     *
     * @custom.Postcondition
     *  This SlideList has been initialized with head, tail, and cursor all set to null.
     */
    public SlideList()
    {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     *  Inserts the indicated Slide after the tail of the list
     *
     * @param newSlide
     *  The Slide object to be wrapped and inserted into the list after the tail of the list.
     *
     * @custom.Precondition
     *  The newSlide must is not null
     *
     * @custom.Postcondition
     *  newSlide has been wrapped in a new SlideListNode object
     *  If tail was previously not null, the newly created SlideListNode has been inserted into the list after the tail.
     *  If tail was previously null, the newly created SlideListNode has been set as the new head of the list (as well as the tail).
     *  The tail now references the newly created SlideListNode.
     *
     * @throws IllegalArgumentException
     *  Thrown if newSlide is null
     */
    public void appendToTail(Slide newSlide)
    {
        if(newSlide == null)//When the Slide is null
        {
            throw new IllegalArgumentException();
        }

        SlideListNode newNode = new SlideListNode(newSlide);

        if(tail == null)//When the list is empty
        {
            newNode.setNext(head);
            newNode.setPrev(null);
            head = newNode;
            tail = head;
            cursor = head;
        }
        else//All other cases
        {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     *  Removes the SlideListNode referenced by cursor and returns the Slide inside
     *
     * @custom.Precondition
     *  cursor is not null.
     *
     * @custom.Postcondition
     *  The SlideListNode referenced by cursor has been removed from the list.
     *  All other SlideListNodes in the list exist in the same order as before.
     *  The cursor now references the previous SlideListNode (or the head, if the cursor previously referenced the head of the list).
     *
     * @return
     *  The reference to the Slide contained within the SlideListNode which was just removed from the list.
     *
     * @throws EndOfListException
     *  Thrown if cursor is null
     */
    public Slide removeCursor()
    {
        if(cursor == null)//When list is empty
        {
            throw new IllegalArgumentException();
        }
        SlideListNode newCursor = cursor;

        if(size == 1)//Case where there is only one node
        {
            head = null;
            tail = null;
            cursor = null;
        }
        else if(cursor == tail)//case where tail has to be removed
        {
            cursor = cursor.getPrev();
            tail = cursor;
            cursor.setNext(null);
        }
        else if(cursor == head)//Case where head has to be removed
        {
            head = cursor.getNext();
            cursor = head;
            cursor.setPrev(null);
        }
        else//All other cases
        {
            cursor = cursor.getPrev();
            newCursor.getPrev().setNext(newCursor.getNext());
            newCursor.getNext().setPrev(newCursor.getPrev());
        }
        track--;
        size--;
        return newCursor.getData();
    }

    /**
     *  Gets the reference to the Slide wrapped by the SlideListNode currently referenced by cursor
     *
     * @return
     *  The reference of the Slide wrapped by the SlideListNode currently referenced by cursor.
     *  If the cursor is null, then this method should return null as well (i.e. the cursor does not reference a Slide).
     */
    public Slide getCursorSlide()
    {
        if(cursor == null)
        {
            return null;
        }
        return cursor.getData();
    }

    /**
     *  Moves the cursor to select the next SlideListNode in the list.
     *  If the cursor is at the tail of the list, this method throws an exception
     *
     * @throws EndOfListException
     *  Thrown if cursor is at the tail of the list.
     */
    public void cursorForward() throws EndOfListException
    {
        if(cursor == null || cursor.getNext() == null)
        {
            throw new EndOfListException();
        }
        track++;
        cursor = cursor.getNext();
    }

    /**
     *  Moves the cursor to select the previous SlideListNode in the list.
     *
     * @throws EndOfListException
     *  Thrown if cursor is at the head of the list.
     */
    public void cursorBackward() throws EndOfListException
    {
        if(cursor == null || cursor.getPrev() == null)
        {
            throw new EndOfListException();
        }
        track--;
        cursor = cursor.getPrev();
    }

    /**
     *  Returns the cursor to the start of the list.
     *
     * @custom.Postconditions
     *  If head is not null, the cursor now references the first SlideListNode in this list.
     *  If head is null, the cursor is set to null as well (there are no Slides in this list).
     */
    public void resetCursorToHead()
    {
        cursor = head;
        track = 1;
    }

    /**
     *  Inserts the indicated Slide before the cursor.
     *
     * @param newSlide
     *  The Slide object to be wrapped and inserted into the list before the cursor.
     *
     * @custom.Precondition
     *  newSlide is not null.
     *
     * @custom.PostCondition
     *  newSlide has been wrapped in a new SlideListNode object
     *  If cursor was previously not null, the newly created SlideListNode has been inserted into the list before the cursor.
     *  If cursor was previously null, the newly created SlideListNode has been set as the new head of the list (as well as the tail).
     *  The cursor now references the newly created SlideListNode.
     *
     * @throws IllegalArgumentException
     *  Thrown if newSlide is null.
     */
    public void insertBeforeCursor(Slide newSlide)
    {
        if(newSlide == null)
        {
            throw new IllegalArgumentException();
        }

        SlideListNode newNode = new SlideListNode(newSlide);

        if(size > 0 && track == 1)// When there is only one slide
        {
            newNode.setPrev(null);
            newNode.setNext(cursor);
            cursor.setPrev(newNode);
            head = newNode;
        }
        else if(cursor != null)// All cases where cursor is between
        {
            newNode.setNext(cursor);
            newNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
        }
        else// When the list is empty
        {
            head = newNode;
            tail = newNode;
            track = 1;
        }
        cursor = newNode;//The cursor now points to the newNode
        size++;
    }

    /**
     *  Returns the total number of bullet points in the slideshow.
     *
     * @return
     *  The sum of all bullet points of all individual Slides in the slideshow.
     */
    public int NumBullets()
    {
        if(size == 0)
        {
            return 0;
        }

        SlideListNode newCursor = head;
        int totalBullets = 0;
        while(newCursor != null)
        {
            totalBullets = totalBullets + newCursor.getData().getNumBullets();
            newCursor = newCursor.getNext();
        }
        return totalBullets;
    }

    /**
     *  Returns the total duration of the slideshow.
     *
     * @return
     *  The sum of all individual Slide durations in the slideshow.
     */
    public double duration()
    {
        double totalDuration = 0;
        SlideListNode newCursor = cursor;
        newCursor = head;
        for(int i=0 ; i<size() ; i++)
        {
            totalDuration = totalDuration + newCursor.getData().getDuration();
            newCursor = newCursor.getNext();
        }
        return totalDuration;
    }

    /**
     *  Returns the total number of Slides in the slideshow.
     *
     * @return
     *  The count of all Slides in the slideshow.
     */
    public int size()
    {
        return size;
    }

    /**
     * Prints a Table of current slides in the list
     */
    public void Print()
    {
        String present = "";// String that stores the output
        SlideListNode newCursor = head;// NewCursor to locate the cursor
        System.out.println("\nSlideshow summary: ");
        System.out.println("==============================================");
        System.out.println("Slide\tTitle\t\tDuration\tBullets");
        for(int i=0 ; i<size ; i++)
        {
            if(newCursor == cursor)
            {present = "->";}
            else
            {present = "  ";}
            System.out.print(present + (i+1) + " \t" + newCursor.getData().getTitle() + "\t\t");
            System.out.println(newCursor.getData().getDuration() + "\t\t\t" + newCursor.getData().getNumBullets());
            newCursor = newCursor.getNext();
        }
        System.out.println("==============================================");
        System.out.print("Total: " + size + " slide(s), ");
        System.out.format("%.1f",duration());
        System.out.println( "minute(s), "+ NumBullets() + " bullet(s)");
        System.out.println("==============================================\n");
    }
}
