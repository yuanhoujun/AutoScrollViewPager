# AutoScrollViewPager
支持自动滑动的ViewPager控件, 可以用于广告图轮播, 但不限于图片轮播, 该控件继承了ViewPager的强大基因
可以定义任意布局. 控件使用经典的Adapter模型对页面及指示器进行样式定制, 可以实现完全自由的定制.

# 基本用法
1) 将library库以module形式导入到你的Android Studio工程中

2) 在布局文件中集成AutoScrollViewPager
```xml
<me.foji.widget.AutoScrollViewPager
    android:layout_width="match_parent"
    android:layout_height="160dp"
    app:autoScrollEnable="true"
    android:id="@+id/viewPager"/\>
```  

3) 设置你的AutoScrollPagerAdapter, 对轮播页面进行定义
<pre>
final int[] images = {R.drawable.cat1,R.drawable.cat2};
mViewPager.setAdapter(new AutoScrollPagerAdapter() {
    @Override
    public void onBindView(View itemView, int position) {
        ((ImageView)itemView).setImageResource(images[position]);
    }
    @Override
    public int getCount() {
        return images.length;
    }
    @Override
    public int onLayoutId() {
        return R.layout.image_view;
    }
});
</pre>
4) 开启自动轮播
<pre>
mViewPager.autoScroll();
</pre>

以上4步操作,就已经完成了该控件的集成,并开启了自动轮播.

# 目前版本已经发布至JitPack，推荐使用如下方式使用:
1）增加以下脚本到你的工程根目录的build.gradle文件中
<pre>
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
</pre>
2) 在你的app工程中，增加如下依赖:
<pre>
dependencies {
	        compile 'com.github.yuanhoujun:AutoScrollViewPager:1.0.0'
	}
</pre>

该控件支持的自定义属性有:
<pre>
app: autoScrollEnable ==> 是否开启自动滚动
app: timeInterval ==> 滚动时间间隔 单位：ms
app: indictorVisibleInSingle ==> 设置单页指示器是否显示(默认不显示)
app: indictorBottomMargin ==> 页面指示器 Bottom Margin
app: indictorSpace ==> 指示器间隔
app: indictorVisible ==> 是否显示页面指示器
</pre>

如需要更多的自定义, 请参考demo代码

![S1](https://github.com/yuanhoujun/AutoScrollViewPager/blob/master/screenshot/s1.png)
![S2](https://github.com/yuanhoujun/AutoScrollViewPager/blob/master/screenshot/s2.png)
![S3](https://github.com/yuanhoujun/AutoScrollViewPager/blob/master/screenshot/s3.png)


###关于作者:
**笔名**: 欧阳锋

**爱好**: 编程,旅游,运动

**擅长**: Android/iOS/Java Web/C++/Kotlin

**简书**: [关注我的简书](http://www.jianshu.com/users/db019edd34b4/latest_articles)

**签名**: 做一个有理想的程序员

**iOS交流群**: 468167089

**Kotlin交流群**: 329673958

**喜欢编程的同学,别忘了关注我的简书哦!**

