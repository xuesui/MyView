package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class RadarView extends View {
    private Paint cornerPaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint initPaint = new Paint();
    private int centerX, centerY;
    private String[] text = {"a", "b", "c", "d", "e", "f"};

    public RadarView(Context context) {
        this(context, null);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        cornerPaint.setStyle(Paint.Style.STROKE);
        cornerPaint.setColor(Color.GRAY);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);
        initPaint.setColor(Color.BLUE);
        cornerPaint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        initPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCorners(canvas);
        drawLines(canvas);
        drawTest(canvas);
        drawInit(canvas);
    }

    private void drawInit(Canvas canvas) {
        float radius = Math.min(centerX, centerY) * 0.8f;
        Path path = new Path();
        initPaint.setStyle(Paint.Style.FILL);
        initPaint.setAlpha(255);
        canvas.drawCircle(-0.6f * radius, 0, 10, initPaint);
        canvas.drawCircle(-radius / 2, (float) (radius * Math.sin(30)), 10, initPaint);
        canvas.drawCircle(radius / 10, (float) (radius * Math.sin(30) / 5), 10, initPaint);
        canvas.drawCircle(radius, 0, 10, initPaint);
        canvas.drawCircle(radius * 0.3f, -(float) (radius * Math.sin(30) * 0.6f), 10, initPaint);
        canvas.drawCircle(-radius * 0.3f, -(float) (radius * Math.sin(30) * 0.6f), 10, initPaint);
        initPaint.setAlpha(127);
        initPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        path.moveTo(-0.6f * radius, 0);
        path.lineTo(-radius / 2, (float) (radius * Math.sin(30)));
        path.lineTo(radius / 10, (float) (radius * Math.sin(30) / 5));
        path.lineTo(radius, 0);
        path.lineTo(radius * 0.3f, -(float) (radius * Math.sin(30) * 0.6f));
        path.lineTo(-radius * 0.3f, -(float) (radius * Math.sin(30) * 0.6f));
        canvas.drawPath(path,initPaint);
    }

    private void drawTest(Canvas canvas) {
        float radius = Math.min(centerX, centerY) * 0.8f;
        canvas.drawText(text[0], -radius - 40, 0, textPaint);
        canvas.drawText(text[1], -radius / 2, -(float) (radius * Math.sin(30) - 40), textPaint);
        canvas.drawText(text[2], radius / 2, -(float) (radius * Math.sin(30) - 40), textPaint);
        canvas.drawText(text[3], radius + 40, 0, textPaint);
        canvas.drawText(text[4], radius / 2, (float) (radius * Math.sin(30) - 40), textPaint);
        canvas.drawText(text[5], -radius / 2, (float) (radius * Math.sin(30) - 40), textPaint);
    }

    private void drawLines(Canvas canvas) {
        float radius = Math.min(centerX, centerY) * 0.8f;
        Path path = new Path();
        canvas.scale(5f, 5f);
        path.moveTo(-radius / 2, -(float) (radius * Math.sin(30)));
        path.lineTo(radius / 2, (float) (radius * Math.sin(30)));
        path.moveTo(radius / 2, -(float) (radius * Math.sin(30)));
        path.lineTo(-radius / 2, (float) (radius * Math.sin(30)));
        path.moveTo(-radius, 0);
        path.lineTo(radius, 0);
        canvas.drawPath(path, cornerPaint);
    }

    private void drawCorners(Canvas canvas) {
        float radius = Math.min(centerX, centerY) * 0.8f;
        Path path = new Path();
        canvas.translate(centerX, centerY);
        path.moveTo(-radius, 0);
        path.lineTo(-radius / 2, -(float) (radius * Math.sin(30)));
        path.lineTo(radius / 2, -(float) (radius * Math.sin(30)));
        path.lineTo(radius, 0);
        path.lineTo(radius / 2, (float) (radius * Math.sin(30)));
        path.lineTo(-radius / 2, (float) (radius * Math.sin(30)));
        path.close();
        canvas.drawPath(path, cornerPaint);
        canvas.scale(0.8f, 0.8f);
        canvas.drawPath(path, cornerPaint);
        canvas.scale(0.75f, 0.75f);
        canvas.drawPath(path, cornerPaint);
        canvas.scale(2 / 3f, 2 / 3f);
        canvas.drawPath(path, cornerPaint);
        canvas.scale(0.5f, 0.5f);
        canvas.drawPath(path, cornerPaint);
    }
}
