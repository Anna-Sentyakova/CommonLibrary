package com.anna.sent.soft.strategy.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strategies implements Strategy {
    // TODO implement iterator for subclasses
    protected final List<String> mKeys = new ArrayList<>();
    private final Map<String, BaseStrategy> mStrategies = new HashMap<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onCreate(savedInstanceState);
        }
    }

    public final void addStrategy(BaseStrategy strategy) {
        String key = strategy.getName();

        if (mStrategies.containsKey(key)) {
            getStrategy(key).release();
        }

        mStrategies.put(key, strategy);
        if (!mKeys.contains(key)) {
            mKeys.add(key);
        }
    }

    public final BaseStrategy getStrategy(String name) {
        return mStrategies.get(name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRestart() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onRestart();
        }
    }

    @Override
    public void onStart() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onStart();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onResume();
        }
    }

    @Override
    public void onPause() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onPause();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onSaveInstanceState(outState);
        }
    }

    @Override
    public void onStop() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onDestroy();
            getStrategy(key).release();
        }

        // нельзя удалять, т.к. в таком случае ломается стратегия жизненного
        // цикла, проверяющая isDestroyed
        // mStrategies.clear();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            getStrategy(key).onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            boolean processed = getStrategy(key).onCreateOptionsMenu(menu);
            if (processed) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            boolean processed = getStrategy(key).onPrepareOptionsMenu(menu);
            if (processed) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            boolean processed = getStrategy(key).onOptionsItemSelected(item);
            if (processed) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        for (int i = 0; i < mKeys.size(); ++i) {
            String key = mKeys.get(i);
            boolean processed = getStrategy(key).onKeyDown(keyCode, event);
            if (processed) {
                return true;
            }
        }

        return false;
    }
}
