package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieView extends View {
    private int[] color={0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080};
    private float[] angle={30,90,60,45,45,60,30};
    private float startAngle=0;
    private Paint paint=new Paint();
    private int width,height;

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float currentStartAngle=startAngle;
        canvas.translate(width/2,height/2);
        float radius=(float)(Math.min(height,width)/4);
        RectF rectf=new RectF(-radius,-radius,radius,radius);
        for (int i=0;i<color.length;i++){
            paint.setColor(color[i]);
            canvas.drawArc(rectf,currentStartAngle,angle[i],true,paint);
            currentStartAngle+=angle[i];
        }
    }
}
