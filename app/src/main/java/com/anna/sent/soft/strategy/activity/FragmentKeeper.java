package com.anna.sent.soft.strategy.activity;

import android.support.v4.app.Fragment;

interface FragmentKeeper {
    void attach(Fragment f);

    void detach(Fragment f);
}
