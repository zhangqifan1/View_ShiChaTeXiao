package com.example.administrator.view_shichatexiao;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by 张祺钒
 * on2017/8/16.
 * 1.继承ListView     自定义接口
 * 2.重写overScrollBy方法,重点在于deLtay,isTouchEvent
 * 3.暴露一个一个方法,可以让外界调用,并测量ImageView的高度
 * 4重写OnTouchEvent
 */

public class MyListView extends ListView {
    private ImageView iv_Header;
    private int heightRes;
    private int orignalHeight;

    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIv_Header(ImageView iv_Header) {
        this.iv_Header = iv_Header;
        //图片的原始高度
        heightRes = iv_Header.getDrawable().getIntrinsicHeight();
        //获取控件的原始高度,以便回弹时,回弹到原始高度
        orignalHeight = iv_Header.getHeight();
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //首先看一下参数的作用
        // A滑动到ListView俩端时才会调用

//        System.out.println("deltaY:"+deltaY);   //竖直方向滑动的瞬时变化量顶部下拉为负,底部上啦为正
//        System.out.println("scrollY:"+scrollY);
//        System.out.println("scrollRangeY:"+scrollRangeY);
//        System.out.println("maxOverScrollY:"+maxOverScrollY);
//        System.out.println("isTouchEvent:"+isTouchEvent);//是否是用户触摸拉动,true表示用户手指拉动,false是惯性

        //顶部下拉用户触摸的操作才执行视差效果
        if (deltaY < 0 && isTouchEvent) {
            //A.deltay是负值,我们要改为绝对值,累计给iv_Header高度
            int newHeight = iv_Header.getHeight() + Math.abs(deltaY);
            //避免图片的无限放大

            if (newHeight <= heightRes) {
                iv_Header.getLayoutParams().height = newHeight;
                iv_Header.requestLayout();
            }

        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //把当前的头布局高度恢复出事高度
                int currentHeight = iv_Header.getHeight();
                //属性动画,改变高度的值,把我们当前头布局的高度,改为原始时的高度

                ValueAnimator valueAnimator = ValueAnimator.ofInt(currentHeight, orignalHeight);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator alueAnimator) {
                        //获取动画执行过程中的分度值
//                        float fraction = valueAnimator.getAnimatedFraction();
                        //获取中间的值,并赋给控件新高度,可以是控件平稳回弹
                        Integer animatedValue = (Integer) alueAnimator.getAnimatedValue();
                        iv_Header.getLayoutParams().height = animatedValue;
                        iv_Header.requestLayout();
                    }
                });
                valueAnimator.setInterpolator(new OvershootInterpolator(2));
                valueAnimator.setDuration(50);
                valueAnimator.start();

                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
