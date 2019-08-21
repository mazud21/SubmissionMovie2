package com.gmail.hmazud.submissionmovie2.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteView(this.getApplicationContext(),intent);
    }
}
