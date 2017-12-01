package hudongchuangxiang.com.seller.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by zhangjiebo on 2017/12/1 0001.
 *
 * @author ZhangJieBo
 */

public class JiaoBiao extends View{
    Rect rect = new Rect();
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int bgColor = Color.RED;
    String text = "NO.3";
    float textSize = 40;
    public JiaoBiao(Context context) {
        super(context);
    }

    public JiaoBiao(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public JiaoBiao(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(bgColor);
        paint.setTextSize(textSize);
        paint.getTextBounds(text,0,text.length(),rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(bgColor);
        canvas.drawRect(0,0,getWidth()/2,getHeight()/2,paint);
        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawOval(rectF,paint);
        paint.setColor(Color.WHITE);
        Log.e("width",""+rect.width());
        canvas.drawText(text,(getWidth()-rect.width())/2,(getHeight()+rect.height())/2,paint);
    }

    public void setBgColorAndTextAndSize(int bgColor,String text,float textSize){
        this.bgColor=bgColor;
        this.text=text;
        this.textSize=textSize;
        invalidate();
    }
}
