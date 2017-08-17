package com.example.administrator.view_shichatexiao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * 1.解析OnTouch,Action_Down,Action_Move,Action_Up
 * 2.重写ListView的OverScrollBy方法,继承自定义控件ListView,根据用户下拉的距离,动态修改headerView的高度
 * a.拷贝文本资源到项目中,自定义控件继承ListView
 * b.使用自定义控件,并网头部添加布局,设置适配器
 * c.使用视图数,把ImageView传给我们的自定义控件
 */
public class MainActivity extends AppCompatActivity {
    public static final String[] NAMES = new String[]{"宋江", "卢俊义", "吴用",
            "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深",
            "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘",
            "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍",
            " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪",
            "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方",
            "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充",
            "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿",
            "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩",
            "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立",
            "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜",
            "时迁", "段景柱", "小凡哥"};
    private MyListView mLv;
    private ImageView iamge_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        MyAdapter myAdapter = new MyAdapter(this, NAMES);
        View viewHead=View.inflate(this,R.layout.headview,null);
        View viewFoot=View.inflate(this,R.layout.footview,null);
        mLv.addHeaderView(viewHead);
//        mLv.addFooterView(viewFoot);

        iamge_header = viewHead.findViewById(R.id.iamge_Header);
        //等View界面全部绘制完毕的时候,去得到已经绘制完控件的宽和高,查一下这个方法,并做一个笔记
        iamge_header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //宽和高已经测量完毕
                mLv.setIv_Header(iamge_header);
                //释放资源
                mLv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        mLv.setAdapter(myAdapter);


    }

    private void initView() {
        mLv = (MyListView) findViewById(R.id.lv);
    }
}
