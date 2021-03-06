package in.techware.lataxi.net.WSAsyncTasks;


import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

import in.techware.lataxi.model.CarBean;
import in.techware.lataxi.model.UserBean;
import in.techware.lataxi.net.invokers.CarInfoInvoker;
import in.techware.lataxi.net.invokers.UserInfoInvoker;

public class CarInfoTask extends AsyncTask<String, Integer, CarBean> {

    private CarInfoTask.CarInfoTaskListener carInfoTaskListener;

    private HashMap<String, String> urlParams;

    public CarInfoTask(HashMap<String, String> urlParams) {
        super();
        this.urlParams = urlParams;
    }

    /*public CarInfoTask(JSONObject urlParams) {

    }*/

    @Override
    protected CarBean doInBackground(String... params) {

        System.out.println(">>>>>>>>>doInBackground");
        CarInfoInvoker carInfoInvoker = new CarInfoInvoker(urlParams, null);
        return carInfoInvoker.invokeCarInfoWS();
    }

    @Override
    protected void onPostExecute(CarBean result) {
        if (result != null)
            carInfoTaskListener.dataDownloadedSuccessfully(result);
        else
            carInfoTaskListener.dataDownloadFailed();
    }

    public interface CarInfoTaskListener {
        void dataDownloadedSuccessfully(CarBean carBean);

        void dataDownloadFailed();
    }

    public CarInfoTaskListener getCarInfoTaskListener() {
        return carInfoTaskListener;
    }

    public void setCarInfoTaskListener(CarInfoTaskListener carInfoTaskListener) {
        this.carInfoTaskListener = carInfoTaskListener;
    }
}
