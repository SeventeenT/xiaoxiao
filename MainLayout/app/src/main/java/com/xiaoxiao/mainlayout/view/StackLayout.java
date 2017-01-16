
package com.xiaoxiao.mainlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LiYing on 2016/9/28 0004.
 */
public class StackLayout extends ViewGroup {
    int gap = 20;

    public StackLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StackLayout(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount;
        childCount = getChildCount();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int viewWidth = width - getPaddingLeft() -getPaddingRight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (childCount == 1) {
            setMeasuredDimension(width,viewWidth + getPaddingTop()+getPaddingBottom());
            return;
        }

       if (childCount == 2 || childCount == 3 || childCount == 4) {
            setMeasuredDimension(width, getLayoutHeight(viewWidth, childCount - 1) + getPaddingTop()+getPaddingBottom());
        }else if (childCount >= 5) {
            setMeasuredDimension(width, getChildWidth(viewWidth,2)+ getChildWidth(viewWidth,3) + gap + getPaddingTop()+getPaddingBottom());
        }
    }

    private int getChildWidth(int totalWidth,int childCount){
        int width = (totalWidth - gap*(childCount-1))/childCount;
        return width;
    }

    /**
     * 左右布局：2张、3张或4张的时候获取容器高度
     * @param totalWidth
     * @param rightCount 右边布局有几个view
     * @return
     */
    private int getLayoutHeight(int totalWidth,int rightCount){
        int h1= (rightCount*totalWidth-gap)/(rightCount+1);
        return h1;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView;
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getMeasuredWidth()-paddingLeft-getPaddingRight();
        int height = getMeasuredHeight();
        int viewHeight = getChildWidth(width,2)+getChildWidth(width,3)+gap;
        Log.e("StackLayout", "width = " + width);
        Log.e("StackLayout", "height = " + height);
        switch (childCount) {
            case 1:
                childView = getChildAt(0);
                childView.layout(paddingLeft, paddingTop, width+paddingLeft, height+paddingTop);
                break;
            case 2:
            case 3:
            case 4:
                for(int i = 0;i<childCount;i++){
                    childView = getChildAt(i);
                    if(i==0){
                        childView.layout(paddingLeft, paddingTop,
                                paddingLeft+getLayoutHeight(width,childCount-1), paddingTop+getLayoutHeight(width,childCount-1));
                    }else{
                       childView.layout(paddingLeft+getLayoutHeight(width,childCount-1)+gap,
                               paddingTop+getChildWidth(getLayoutHeight(width,childCount-1),childCount-1)*(i-1)+gap*(i-1),
                               paddingLeft+width,
                               paddingTop+getChildWidth(getLayoutHeight(width,childCount-1),childCount-1)*i+gap*(i-1));
                    }
                }
                break;
            case 5:
                for (int i = 0; i < 5; i++) {
                    childView = getChildAt(i);
                    switch (i) {
                        case 0:
                            childView.layout(paddingLeft, paddingTop, paddingLeft+getChildWidth(width,2), paddingTop+getChildWidth(width,2));
                            break;
                        case 1:
                            childView.layout(getChildWidth(width,2)+gap+paddingLeft, paddingTop,
                                    paddingLeft+getChildWidth(width,2)*2+gap, paddingTop+getChildWidth(width,2));
                            break;
                        case 2:
                            childView.layout(paddingLeft, paddingTop + getChildWidth(width,2)+gap,
                                    paddingLeft+getChildWidth(width,3), paddingTop + viewHeight);
                            break;
                        case 3:
                            childView.layout(paddingLeft+getChildWidth(width,3)+gap, paddingTop+getChildWidth(width,2)+gap,
                                    paddingLeft+getChildWidth(width,3)*2+gap, paddingTop+viewHeight);
                            break;
                        case 4:
                            childView.layout(paddingLeft+getChildWidth(width,3)*2+gap*2, paddingTop+getChildWidth(width,2)+gap,
                                    paddingLeft+getChildWidth(width,3)*3+gap*2, paddingTop+viewHeight);
                            break;
                        default:
                            break;
                    }
                }
                break;
        }
    }
}
