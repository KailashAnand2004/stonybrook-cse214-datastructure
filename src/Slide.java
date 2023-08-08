/**
 * This class creates a Slide which contins a title, duration and bullets for the presentation.
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #2 for CSE 214
 * Date:
 *    February 20th, 2023
 */
public class Slide
{
    public static final int MAX_BULLETS = 5;
    private String title;
    private String[] bullets = new String[MAX_BULLETS];
    private double duration;

    /**
     * Default constructor.
     *
     * @Preconditions
     *  This object has been initialized to an empty Slide (title and all bullets are null, duration = 0.0)
     */
    public Slide()
    {}

    /**
     * Public getter method for the title member variable.
     *
     * @return
     *  The title of the Slide.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Public setter method for the title member variable.
     *
     * @param newTitle
     *  The new title of this slide. This parameter should not be null
     *
     * @Preconditions
     *  newTitle is not null.
     *
     * @Throws IllegalArgumentException
     *  Thrown if newTitle is null.
     */
    public void setTitle(String newTitle)
    {
        if(newTitle == null)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            title = newTitle;
        }
    }

    /**
     * Public getter method for the duration member variable.
     *
     * @return
     *  The duration of the Slide.
     */
    public double getDuration()
    {
        return duration;
    }

    /**
     * Public setter method for the duration member variable.
     *
     * @param newDuration
     *  The new duration of this slide. This parameter should be greater than 0.
     *
     * @Preconditions
     *  newDuration is greater than 0.
     *
     * @Throws IllegalArgumentException
     *  Thrown if newDuration is less than or equal to 0.
     */
    public void setDuration(double newDuration)
    {
        if(newDuration <= 0.0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            duration = newDuration;
        }
    }

    /**
     * Gets the total number of bullet point in the Slide.
     * 
     * @return
     *  The number of bullet points in the Slide.
     */
    public int getNumBullets()
    {
        int counter = 0;// Stores the number of bullets
        int i=0;// i is the variable to control the while loop
        while( i < 5 && bullets[i] != null )
        {
            counter++;
            i++;
        }
        return counter;
    }

    /**
     * Gets the bullet point at index i.
     *
     * @param i
     *  The index to retrieve from the array. This value must be between 1 and MAX_BULLETS, inclusive.
     *
     * @Preconditions
     *  1 ≤ i ≤ MAX_BULLETS.
     *
     * @return
     *  The String representing the bullet point at the given
     *
     * @Throws IllegalArgumentException
     *  Thrown if i is not in the valid range.
     */
    public String getBullet(int i)
    {
        if(i<1 || i>MAX_BULLETS)
        {
            throw new IllegalArgumentException();
        }
        return bullets[i-1];
    }

    /**
     * Sets bullet as the i'th bullet point for bullets.
     * If bullet is null, this method erases the bullet point at index i and pushes all bullet points greater than i back one index
     *
     * @param bullet
     *  The String to place as the ith bullet point in bullets.
     *  This parameter may be null, indicating that the bullet at index i is to be erased from the slide.
     *
     * @param i
     *  The index to place bullet in the array.
     *  This value must be between 1 and MAX_BULLETS, inclusive.
     *
     * @Preconditions
     *  1 ≤ i ≤ MAX_BULLETS.
     *
     * @Postconditions
     *  The bullet point at index i has been updated to the String bullet.
     *  There are no holes in the bullets array. All bullet points occupy the lowest possible indices of the array.
     *
     * @Throws IllegalArgumentException
     *  Thrown if i is not in the valid range.
     */
    public void setBullets(String bullet, int i)
    {
        if(i<1 || i>MAX_BULLETS)
        {
            throw new IllegalArgumentException();
        }

        if(bullet == null )
        {
            bullets[i-1] = null;
            int counter = 0;
           String[] newBullets = new String[MAX_BULLETS]; //Using a new array to remove holes
           for(int j=0 ; j<MAX_BULLETS ; j++)
           {
               if(bullets[j] != null)// Case if bullet is null
               {
                   newBullets[counter] = bullets[j];
                   counter++;
               }
               else
               { }
           }
           bullets = newBullets;
        }
        else
        { bullets[i-1] = bullet; }// General Case
    }
}
