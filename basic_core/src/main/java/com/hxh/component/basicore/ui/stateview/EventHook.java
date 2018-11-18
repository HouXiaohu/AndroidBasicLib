package com.hxh.component.basicore.ui.stateview;

import android.view.View;

import java.util.List;

/**
 * Created by houxi on 2018/11/17.
 */

public abstract class EventHook {

    public abstract void onEvent(View view);

    public int[] onBindView(){
        return null;
    }
}
