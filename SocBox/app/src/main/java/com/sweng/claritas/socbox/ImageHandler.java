package com.sweng.claritas.socbox;

/*   Claritas: Clarity through innovation

     Project: Socbox
     Module: Image Media Module
     Code file name: ImageHandler.java

     Description: This class is built to contain and test the Image handler. It therefore
     embeds images within frames using the reference IDs of the images and frames to locate them.
     While it is probably complete. It has not been tested yet and may still need to be modified.
     It will be modified to allow for image streaming and some of this work is completed and commented out.
     It will also be modified to allow for images to be links when there is something to link to.

    Initial authors: Anton Zijlstra

    Change History:
    Version 0.1
    Author: Anton Zijlstra
    Change: Created original image handler and partially completed image streaming
    Date: 01/03/15

    Traceability

    Tag: U/IM/01/01
    Requirement: Images can be displayed

    Tag: U/IM/02/01
    Requirement: Images are automatically resized

    Tag: U/IM/03/01
    Requirement: There can be multiple scrolling images placed within a frame

    Tag: U/IM/04/01
    Requirement: These can scroll vertically or horizontally

    Tag: U/IM/05/01
    Requirement: Some images act as links

    Other information:
    Note: This file has not yet been tested and may be incomplete. It is also awaiting Image streaming
    completion.
    To do: Test to see if the Image handler works as intended
           Finish Image streaming when possible
    */



//Variable for image streaming, please ignore
//private ImageView imageView;

//import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

//imports for image streaming
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;

//In order to use the image handler you need to do the following:
//Step 1: Define the Strings for the Image file and imageView frame names
//Step 2: Use getResources to obtain the ID's of the Image and frame
//Step 3: Call the embed_image module with those arguments
/*E.g.
*  Step 1:
*   String imageToEmbed = "test1";
*   String frameToEmbedImage = "imageView1";
*
*  Step 2:
*   int imageID = getResources().getIdentifier(imageToEmbed, "drawable", "com.claritas.anton.socbox");
    int frameID = getResources().getIdentifier(frameToEmbedImage, "id", "com.claritas.anton.socbox");
*
*  Step 3
*   embed_image(imageID, frameID);
* */

public class ImageHandler extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //These are the strings for use in the Frame and Image ID's. These are the names of the files and imageView Frames
        //that the handler will use
        String imageToEmbed = "test1";
        String frameToEmbedImage = "imageView1";
        String scrollingImageToEmbed = "test1";
        String scrollingFrameToEmbedImage = "imageView3";
        String scrollingImageToEmbed2 = "test1";
        String scrollingFrameToEmbedImage2 = "imageView4";
        String scrollingImageToEmbed3 = "test1";
        String scrollingFrameToEmbedImage3 = "imageView5";

        super.onCreate(savedInstanceState);
        //Set the layout to the main activity layout
        setContentView(R.layout.activity_image_handler);

        //Define the frame and Image IDs based upon the strings for the file and frame names (above) so that these can
        // be passed to the image handler and used to embed the given images in the given ImageViews
        int imageID = getResources().getIdentifier(imageToEmbed, "drawable", "com.sweng.claritas.socbox");
        int frameID = getResources().getIdentifier(frameToEmbedImage, "id", "com.sweng.claritas.socbox");

        int scrollingImageID = getResources().getIdentifier(scrollingImageToEmbed, "drawable", "com.sweng.claritas.socbox");
        int scrollingFrameID = getResources().getIdentifier(scrollingFrameToEmbedImage, "id", "com.sweng.claritas.socbox");

        int scrollingImageID2 = getResources().getIdentifier(scrollingImageToEmbed2, "drawable", "com.sweng.claritas.socbox");
        int scrollingFrameID2 = getResources().getIdentifier(scrollingFrameToEmbedImage2, "id", "com.sweng.claritas.socbox");

        int scrollingImageID3 = getResources().getIdentifier(scrollingImageToEmbed3, "drawable", "com.sweng.claritas.socbox");
        int scrollingFrameID3 = getResources().getIdentifier(scrollingFrameToEmbedImage3, "id", "com.sweng.claritas.socbox");

        //Embed a single image in a frame
        embed_image(imageID, frameID);
        //Embed multiple images in a (scrolling) frame
        embed_image(scrollingImageID, scrollingFrameID);
        embed_image(scrollingImageID2, scrollingFrameID2);
        embed_image(scrollingImageID3, scrollingFrameID3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_handler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //This class embeds a given image within a given Image view in the main layout. It is fully modular.
    //The only requirement for use is that the Image and Frame ID must be defined beforehand (see above)
    //and that there must be an Image in storage and an ImageViewer defined in the layout xml file
    /*Inputs: The image and file to be displayed
    * Outputs: Nothing*/
    public void embed_image(int imageID, int frameID)
    {
        //ImageView imageView = new ImageView(this);

        //This code will be for streaming an image
        /*try {
            URL url = new URL("@string/address");
            InputStream content = (InputStream) url.getContent();
            Drawable image = Drawable.createFromStream(content , "src");
            imageView.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Find the correct ImageView frame
        ImageView embedded_image = (ImageView) findViewById(frameID);
        //Embed the given image within it
        embedded_image.setImageResource(imageID);
    }

}