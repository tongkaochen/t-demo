package com.tifone.spwp.opengl;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tifone.spwp.common.tab.MyTabActivity;
import com.tifone.spwp.opengl.base.GLBaseDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyTabActivity {

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> result = new ArrayList<>();
        result.add(GLBaseDemoFragment.create("Base demo"));
        return result;
    }

    @Override
    protected String getActionBarTitle() {
        return "OpenGLES";
    }
}
