package com.sweng.claritas.socbox;

        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Typeface;
        import android.text.method.ScrollingMovementMethod;
        import android.widget.TextView;

public class TextHandler {

    Activity activity;
    TextView textPanel;

    public TextHandler(Activity calledFrom){
        this.activity = calledFrom;

        //create a text panel object
        textPanel = (TextView) this.activity.findViewById(R.id.outText);

    }
    public void showText(String inputText,int xpos, int ypos,  int textSize, Typeface chosenFont, int bold, int italics, int scroll, int underline){

        //set the position of the text panel
        textPanel.setX(xpos);
        textPanel.setY(ypos);
        //set the message content and style of message
        textPanel.setTextSize(textSize);
        textPanel.setText(inputText);
        textPanel.setTextColor(Color.GREEN);
        textPanel.setTypeface(chosenFont);


        //allow the use of the scroll function if required
        if (scroll == 1) {
            textPanel.setMovementMethod(new ScrollingMovementMethod());
        }

        //set the underlined, bold and italics options
        if (underline == 1){
            textPanel.setPaintFlags(textPanel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        if ((bold == 1)&&(italics == 0)){
            textPanel.setTypeface(null,Typeface.BOLD);
        }
        if ((italics == 1)&&(bold == 0)){
            textPanel.setTypeface(null,Typeface.ITALIC);
        }
        if ((italics == 1) && (bold == 1)){
            textPanel.setTypeface(null,Typeface.BOLD_ITALIC);
        }}

    public void moveActivity(){
        Intent intent = new Intent(this.activity, MainActivityText.class);
        this.activity.startActivity(intent);
    }
}
