package com.github.snowdream.android.app;

import android.content.Context;
import android.os.Handler;

import com.github.snowdream.android.util.concurrent.TaskListener;

/**
 * Created by snowdream on 12/30/13.
 */
public abstract class AbstractUpdateListener extends TaskListener<Integer, UpdateInfo> {
    private Handler handler = null;
    private Context context = null;
    private UpdateOptions updateOptions = null;

    /**
     * Get Handler
     *
     * @return
     */
    protected Handler getHandler() {
        return handler;
    }

    /**
     * Set Handler
     *
     * @param handler
     */
    protected void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * Get the Context
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Set the Context
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Get the update options
     *
     * @return
     */
    public UpdateOptions getUpdateOptions() {
        return updateOptions;
    }

    /**
     * Set the update options
     *
     * @param updateOptions
     */
    public void setUpdateOptions(UpdateOptions updateOptions) {
        this.updateOptions = updateOptions;
    }

    /**
     * show the update dialog
     *
     * @param info the info for the new app
     */
    public abstract void onShowUpdateUI(UpdateInfo info);

    /**
     * It's the latest app,or there is no need to update.
     */
    public abstract void onShowNoUpdateUI();

    /**
     * show the progress when downloading the new app
     */
    public abstract void onShowUpdateProgressUI(DownloadTask task, int progress);

    /**
     * user click to confirm the update
     */
    public final void informUpdate(UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_UPDATE,
                    info);
        }
    }

    /**
     * user click to cancel the update
     */
    public final void informCancel(UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_CANCEL);
        }

        if ((updateOptions != null && updateOptions.shouldForceUpdate())
                || (info != null && info.isForceUpdate())) {
            ExitApp();
        }
    }

    /**
     * user click to skip the update
     */
    public final void informSkip(UpdateInfo info) {
        if (handler != null) {
            handler.obtainMessage(
                    UpdateManager.MSG_INFORM_SKIP, info);
        }

        if ((updateOptions != null && updateOptions.shouldForceUpdate())
                || (info != null && info.isForceUpdate())) {
            ExitApp();
        }
    }

    /**
     * Exit the app here
     */
    public abstract void ExitApp();
}