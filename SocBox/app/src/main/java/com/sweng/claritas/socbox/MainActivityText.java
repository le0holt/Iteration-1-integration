package com.sweng.claritas.socbox;
/*

Claritas: ‘Clarity through innovation’

Project: SocBox

Module: Text Module

Code File Name: TextModule

Description: this code is to accept infomation about text that needs to be displayed
and show it in the format required

Initial Authors: Mark Stonehouse

                              Harriet Taylor

Change History:

Version: 0.1

Author: Mark Stonehouse

Change: Created original version

Date: 25/02/2015

Version: 0.2

Author: Mark Stonehouse, Harriet Taylor

Change: added bold, italics and underlined user stories

Date: 01/03/2015

Traceabilty:

Tag: [D/TMF/01/01] - [D/TMF/08/01]

Requirement: show text in the correct format in a specified location

*/



        import android.app.Activity;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;


public class MainActivityText extends Activity {

    public TextHandler textHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set teh content to the activity main xml file
        setContentView(R.layout.activity_text_handler);
        //call the text module with the relevant information
        textHandler = new TextHandler(this);
        textHandler.showText("hello",100,100, 50, Typeface.SERIF, 1, 0, 0,0);

        //showText(
        //"hello iuh f siufh isdufh sidvhs dvisduvh sdvh sidvhis uh iuh ihu kjh khj ikjh kjh ig sidfh iuhv isdvh isdvhiduvh ouvh iuhh viduvh iuh duvh ihu ", 50, Typeface.SANS_SERIF, 1, 1,1,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_handler, menu);
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
}
