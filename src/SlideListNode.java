/**
 * This class contains the Slide data and links to connect and create nodes.
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #2 for CSE 214
 * Date:
 *    February 20th, 2023
 */
public class SlideListNode
{
    private Slide data = new Slide();//
    private SlideListNode next;
    private SlideListNode prev;

    /**
     * Default constructor.
     *
     * @param initData
     *  The data to be wrapped by this SlideListNode.
     *  This parameter should not be null, since we should never have a SlideListNode with null data
     *
     * @Preconditions
     *  initData is not null.
     *
     * @PostConditions
     *  This SlideListNode has been initialized to wrap the indicated Slide, and prev and next have been set to null.
     *
     * @Throws IllegalArgumentException
     *   Thrown if initData is null.
     */
    public SlideListNode(Slide initData)
    {
        if(initData == null)
        {
            throw new IllegalArgumentException();
        }

        data.setTitle(initData.getTitle());
        data.setDuration(initData.getDuration());

        for(int i=0 ; i< initData.getNumBullets() ; i++)
        {
            data.setBullets(initData.getBullet((i+1)),(i+1));
        }

        prev = null;
        next = null;
    }

    /**
     * Gets the reference to the data member variable of the list node.
     *
     * @return
     *  The reference of the data member variable.
     */
    public Slide getData()
    {
        return data;
    }

    /**
     * Updates the data member variable with a reference to a new Slide
     *
     * @param newData
     *  Reference to a new Slide object to update the data member variable
     *  This parameter should not be null, since we should never have a SlideListNode with null data
     *
     * @Preconditions
     *  newData is not null
     *
     * @Throws IllegalArgumentException
     *  Thrown if newData is null
     */
    public void SetData(Slide newData)
    {
        if(newData == null)
        {
            throw new IllegalArgumentException();
        }

        data.setTitle(newData.getTitle());
        data.setDuration(newData.getDuration());

        for(int i=0 ; i< newData.getNumBullets() ; i++)
        {
            data.setBullets(newData.getBullet(i),i);
        }
    }

    /**
     * Updates the data member variable with a reference to a new Slide.
     *
     * @return
     *  The reference of the next member variable.
     *  Note that this return value can be null, meaning that there is no next SlideListNode in the list.
     */
    public SlideListNode getNext()
    {
        return next;
    }

    /**
     * Updates the next member variable with a new SlideListNode reference.
     *
     * @param newNext
     *  Reference to a new SlideListNode object to update the next member variable.
     *  This parameter may be null, since it is okay to have no next SlideListNode
     */
    public void setNext(SlideListNode newNext)
    {
        next = newNext;
    }

    /**
     * Gets the reference to the prev member variable of the list node.
     *
     * @return
     *  The reference of the prev member variable.
     *  Note that this return value can be null, meaning that there is no previous SlideListNode in the list.
     */
    public SlideListNode getPrev()
    {
        return prev;
    }

    /**
     * Updates the prev member variable with a new SlideListNode reference.
     *
     * @param newPrev
     *  Reference to a new SlideListNode object to update the prev member variable.
     *  This parameter may be null, since it is okay to have no previous SlideListNode
     */
    public void setPrev(SlideListNode newPrev)
    {
        prev = newPrev;
    }
}
