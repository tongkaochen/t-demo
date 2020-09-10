package com.tifone.demo.opengl;

import androidx.fragment.app.Fragment;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.opengl.base.GLBaseDemoFragment;

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
