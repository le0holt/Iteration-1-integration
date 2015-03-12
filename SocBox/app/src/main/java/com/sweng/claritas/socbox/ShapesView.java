package com.sweng.claritas.socbox;

        import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.graphics.Rect;
        import android.graphics.RectF;
        import android.graphics.drawable.ShapeDrawable;
        import android.graphics.drawable.shapes.OvalShape;
        import android.util.AttributeSet;
        import android.view.View;

/**
 * Claritas: 'Clarity Through Innovation
 *
 * Project: SocBox
 * Purpose: Graphics Handler
 * Code File Name:
 *
 * Description: This class is a custom view for drawing primitive graphical shapes. It is designed
 * to be constructed from a layout XML file which specifies values for custom attributes which are
 * defined in a separate attributes XML file. Default values for every attribute if not defined
 * in the layout XML are also set at the top of this class. The view will contain the shape that
 * is specified in the layout XML with the colour, line thickness and other attributes as set
 * in the XML. The graphical view can then be positioned as required in the layout using the
 * available android attributes for the corresponding layout (viewGroup) being used.
 *
 * Initial Authors:
 *                  Richard Millen
 *                  Leo Holt
 *
 * Change History:
 * Version:0.1
 * Author: Richard Millen, Leo Holt
 * Change: Created original version
 * 2/3/15
 *
 * Traceability: [GM]
 *
 */

public class ShapesView extends View {

    //Easily accessible constants for the default values of attributes
    //if they have not been defined in the activity layout xml.
    private final int INIT_LINE_THICKNESS = 5;
    private final int INIT_SHAPE_HEIGHT = 50;
    private final int INIT_SHAPE_WIDTH = 50;
    private final int INIT_DRAW_SHAPE = 0;
    private final int INIT_SHAPE_COLOUR = 0;
    private final int INIT_SHAPE_FILL = 1;
    private final int INIT_LINE_ORIENTATION = 0;


    private int mDrawShape;
    private int mShapeColour;
    private int mShapeFill;
    private int mLineThickness;
    private int mShapeHeight;
    private int mShapeWidth;
    private int mLineOrientation;

    private Paint shapePaint;
    private RectF shapeRect;
    private Path shapeTriangle;

    //coordinates for drawing line (if selected)
    private float linex_start, liney_start, linex_end, liney_end;

    //View constructor initialises graphics to be drawn via information
    //present in attrs.XML.

    public ShapesView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //get attributes from attrs.XML. These are stored in TypedArray 'a' and then
        //extracted from 'a' in class variables.
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,R.styleable.ShapesView, 0, 0);


        try {
            mShapeHeight = a.getInteger(R.styleable.ShapesView_shape_height,
                    INIT_SHAPE_HEIGHT);
            mShapeWidth = a.getInteger(R.styleable.ShapesView_shape_width,
                    INIT_SHAPE_WIDTH);
            mLineThickness = a.getInteger(R.styleable.ShapesView_line_thickness,
                    INIT_LINE_THICKNESS);
            mDrawShape = a.getInteger(R.styleable.ShapesView_draw_shape,
                    INIT_DRAW_SHAPE);
            mShapeColour = a.getInteger(R.styleable.ShapesView_shape_colour,
                    INIT_SHAPE_COLOUR);
            mShapeFill = a.getInteger(R.styleable.ShapesView_shape_fill,
                    INIT_SHAPE_FILL);
            mLineOrientation = a.getInteger(R.styleable.ShapesView_line_orientation,
                    INIT_LINE_ORIENTATION);
        } finally {
            a.recycle();
        }

        init();


    }
//--------------------------------------------------------------------------------------------

    //Getters for every attribute to allow dynamic manipulation post-initialisation

    public int getmShapeHeight() { return mShapeHeight; }

    public int getmShapeWidth() { return mShapeWidth; }

    public int getmLineOrientation() {return mLineOrientation; }

    public int getmLineThickness() {
        return mLineThickness;
    }

    public int getmShapeColour() {
        return mShapeColour;
    }

    public int getmShapeFill() {
        return mShapeFill;
    }

    public int getmDrawShape() {
        return mDrawShape;
    }

//--------------------------------------------------------------------------------------------

    //Setters for every attribute to allow dynamic manipulation post-initialisation

    public void setmShapeHeight(int mShapeHeight) {
        this.mShapeHeight = mShapeHeight;
        invalidate();
        requestLayout();
    }

    public void setmShapeWidth(int mShapeWidth) {
        this.mShapeWidth = mShapeWidth;
        invalidate();
        requestLayout();
    }

    public void setmLineOrientation(int mLineOrientation) {
        this.mLineOrientation = mLineOrientation;
        invalidate();
        requestLayout();
    }

    public void setmLineThickness(int mLineThickness) {
        this.mLineThickness = mLineThickness;
        invalidate();
        requestLayout();
    }

    public void setmDrawShape(int mDrawShape) {
        this.mDrawShape = mDrawShape;
        invalidate();
        requestLayout();
    }

    public void setmShapeColour(int mShapeColour) {
        this.mShapeColour = mShapeColour;
        invalidate();
        requestLayout();
    }

    public void setmShapeFill(int mShapeFill) {
        this.mShapeFill = mShapeFill;
        invalidate();
        requestLayout();
    }

//-------------------------------------------------------------------------------------------

    //initialising function called in constructor avoids the needs to change create objects and
    //initialise settings every time the view is drawn. the setters can be used to dynamically
    //change these settings if required.

    public void init() {

        shapePaint = new Paint();
        shapeRect = new RectF();
        shapeTriangle = new Path();

        //Used to move the shape away from edge so all of line at specified thickness is displayed
        //and shape is not cropped. Used in line and rect initialisation.
        int noClip = (mLineThickness/2);

        //Toggles the fill of the shape on or off
        if (mShapeFill == 0)
            shapePaint.setStyle(Paint.Style.STROKE);
        else if (mShapeFill == 1)
            shapePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //Sets the fill colour of shape between one of the pre-defined colours
        if (mShapeColour == 0)
            shapePaint.setColor(Color.BLACK);
        else if (mShapeColour == 1)
            shapePaint.setColor(Color.WHITE);
        else if (mShapeColour == 2)
            shapePaint.setColor(Color.RED);
        else if (mShapeColour == 3)
            shapePaint.setColor(Color.BLUE);

        //Sets the stroke width for the shape
        shapePaint.setStrokeWidth(mLineThickness);

        //Initialise RectF object used for oval and rectangle if one of these shapes is
        //selected
        if (mDrawShape == 0 || mDrawShape == 2) {
            shapeRect.set(noClip, noClip, mShapeWidth-noClip, mShapeHeight-noClip);
        }

        //Initialise Path for triangle shape if this shapes is selected
        if (mDrawShape == 1) {
            shapeTriangle.setFillType(Path.FillType.EVEN_ODD);
            shapeTriangle.moveTo(0 + mLineThickness, mShapeHeight - mLineThickness);
            shapeTriangle.lineTo((mShapeWidth) / 2, mLineThickness);
            shapeTriangle.lineTo(mShapeWidth - mLineThickness, mShapeHeight - mLineThickness);
            shapeTriangle.close();
        }

        //Initialise settings for line if this shape is selected
        else if(mDrawShape == 3) {
            if(mLineOrientation == 0) {
                mShapeHeight = mLineThickness;
                linex_start = 0;
                liney_start = noClip;
                linex_end = mShapeWidth;
                liney_end = noClip;
            }
            else if(mLineOrientation == 1) {
                mShapeWidth = mLineThickness;
                linex_start = noClip;
                liney_start = 0;
                linex_end = noClip;
                liney_end = mShapeHeight;
            }
        }
    }

//-------------------------------------------------------------------------------------------

    //Views methods are overridden for custom view functionality

    //onDraw method override dictates how the contents of the view are drawn
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mDrawShape == 0)        //rectangle
            canvas.drawRect(shapeRect, shapePaint);
        else if (mDrawShape == 1)    //triangle
            canvas.drawPath(shapeTriangle, shapePaint);
        else if (mDrawShape == 2)   //oval
            canvas.drawOval(shapeRect, shapePaint);
        else if(mDrawShape == 3)    //line
            canvas.drawLine(linex_start, liney_start, linex_end, liney_end ,shapePaint);
    }

    //on measure override sets the view to the correct size in the layout
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //information extracted from MeasureSpecs of the view provided by
        //the onMeasure method
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(mShapeWidth, widthSize);
        } else {
            //Be whatever you want
            width = mShapeWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(mShapeHeight, heightSize);
        } else {
            //Be whatever you want
            height = mShapeHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }

}



