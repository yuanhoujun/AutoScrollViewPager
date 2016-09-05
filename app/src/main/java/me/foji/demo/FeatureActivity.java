package me.foji.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author Scott Smith  @Date 2016年09月16/9/5日 11:20
 */
public class FeatureActivity extends BaseActivity {
    public static final String KEY_TYPE = "type";

    // 图片轮播
    public static final int TYPE_IMAGE_SLIDER = 1;
    // 任意轮播
    public static final int TYPE_ANY_SLIDER = 2;
    // 动态设置
    public static final int TYPE_DYNAMIC_SET = 3;
    // 自定义指示器样式
    public static final int TYPE_CUSTOM_INDICTOR_STYLE = 4;
    // 自定义PageControl
    public static final int TYPE_CUSTOM_PAGECONTROL = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.setId(R.id.container);
        setContentView(frameLayout);

        switch (getIntent().getIntExtra(KEY_TYPE,TYPE_IMAGE_SLIDER)) {
            case TYPE_IMAGE_SLIDER: {
                push(FragmentImageSlider.class);
                break;
            }
            case TYPE_ANY_SLIDER: {
                push(FragmentAnySlider.class);
                break;
            }
            case TYPE_DYNAMIC_SET: {
                push(FragmentDynamicSet.class);
                break;
            }
            case TYPE_CUSTOM_INDICTOR_STYLE: {
                push(FragmentCustomStyle.class);
                break;
            }
            case TYPE_CUSTOM_PAGECONTROL: {
                push(FragmentCustomPageControl.class);
                break;
            }
        }
    }

    @Override
    public int containerId() {
        return R.id.container;
    }
}
