package in.techware.lataxi.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.List;

import in.techware.lataxi.listeners.EditProfileListener;
import in.techware.lataxi.model.AuthBean;
import in.techware.lataxi.model.UserBean;
import in.techware.lataxi.net.invokers.EditProfileInvoker;
import in.techware.lataxi.net.invokers.LoginInvoker;

public class EditProfileTask extends AsyncTask<String, Integer, UserBean> {

    private final List<String> fileList;
    private EditProfileTaskListener editProfileTaskListener;

    private JSONObject postData;

    public EditProfileTask(JSONObject postData, List<String> fileList) {
        super();
        this.postData = postData;
        this.fileList = fileList;
    }

    @Override
    protected UserBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        EditProfileInvoker editProfileInvoker = new EditProfileInvoker(null, postData);
        return editProfileInvoker.invokeEditProfileWS(fileList);
    }

    @Override
    protected void onPostExecute(UserBean result) {
        super.onPostExecute(result);
        if (result != null)
            editProfileTaskListener.dataDownloadedSuccessfully(result);
        else
            editProfileTaskListener.dataDownloadFailed();
    }

    public static interface EditProfileTaskListener {

        void dataDownloadedSuccessfully(UserBean userBean);

        void dataDownloadFailed();
    }

    public EditProfileTaskListener getEditProfileTaskListener() {
        return editProfileTaskListener;
    }

    public void setEditProfileTaskListener(EditProfileTaskListener editProfileTaskListener) {
        this.editProfileTaskListener = editProfileTaskListener;
    }
}

