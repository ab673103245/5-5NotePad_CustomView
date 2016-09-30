package qianfeng.a5_5notepad_customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/9/24 0024.
 */
public class MyNotePadView extends EditText {
    private Paint notePadPaint;

    private int padding = 40;

    public MyNotePadView(Context context) {
        this(context, null);
    }

    public MyNotePadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNotePadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundResource(qianfeng.notepad.R.drawable.background);

        setFocusableInTouchMode(true); // 让MyNotePad(EditText)可以获取到屏幕的焦点

        setTextSize(24); // 设置字体大小

        setTextColor(Color.GRAY); // 设置字体颜色

        // 待会看看这属性
        setLineSpacing(10,1); // 设置行与行之间的？ 设置文字与Line之间的距离为10px(距离line底部的距离)

        // 设置光标处于左上角，默认不设置gravity属性，它是处于中间的
        setGravity(Gravity.TOP);
        // 设置左右内边距
        setPadding(padding, 0, 0, padding);


        notePadPaint = new Paint();
        notePadPaint.setColor(Color.BLACK);
        notePadPaint.setAntiAlias(true);
        notePadPaint.setStrokeWidth(1);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制行数的时候需要注意什么？

        // 获取EditText中每一行的高度
        int lineHeight = getLineHeight();

        // 获取EditText中文本的总行数, 你一共输入了多少行，它都可以获取到
//        int lineCount = getLineCount();
        int height = getHeight(); // 这个height就是控件的总高度

        int lineCount = height / lineHeight; // 计算每一页的行数
        // 计算完每一页的行数就可以绘制啦！
        for (int i = 0; i < lineCount; i++) {
            canvas.drawLine(padding,(i+1)*lineHeight,getWidth()-padding,(i+1)*lineHeight,notePadPaint);
        }

        int lineCount1 = getLineCount();// 获取EditText中文本的总行数

        int extraLineCount = lineCount1 - lineCount;
        if(extraLineCount > 0)  // 如果输入的文本行数超过第一页能显示的横线数量(即处理只有文本，文本下面没有横线的情况)
        {
            for (int i = lineCount; i < lineCount1; i++) {
                canvas.drawLine(padding,(i+1)*lineHeight,getWidth()-padding,(i+1)*lineHeight,notePadPaint);
            }
        }


    }
}
