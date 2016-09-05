package me.foji.demo;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Scott Smith  @Date 2016年09月16/9/5日 10:37
 */
public abstract class BaseActivity extends AppCompatActivity {

    public void push(Class<? extends Fragment> cls) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment target = Fragment.instantiate(this,cls.getName());
        transaction.add(containerId(),target,cls.getName());
        transaction.commitAllowingStateLoss();
    }

    public abstract @IdRes int containerId();
}
