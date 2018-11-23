package br.com.umcincoum.djme.api.controller.user;

import android.os.AsyncTask;

import com.google.gson.Gson;

import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.model.UserModel;
import br.com.umcincoum.djme.api.service.UserService;
import br.com.umcincoum.djme.ui.event.ConfirmEventActivity;

public class UpdateUserEventController extends AsyncTask<UserModel,Void,Void> {
    private ConfirmEventActivity confirmEventActivity;

    public UpdateUserEventController(ConfirmEventActivity confirmEventActivity) {
        this.confirmEventActivity = confirmEventActivity;
    }

    @Override
    protected Void doInBackground(UserModel... userModels) {
        EventModel eventModel = new EventModel();
        eventModel = userModels[0].getEvents().get(userModels[0].getEvents().size() - 1);

        UserService.updateEvent(eventModel, userModels[0].getEmail());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Gson gson = new Gson();
        this.confirmEventActivity.sp = this.confirmEventActivity.getSharedPreferences("appSp", 0);
        this.confirmEventActivity.editor = this.confirmEventActivity.sp.edit();
        this.confirmEventActivity.editor.putString("event", gson.toJson(this.confirmEventActivity.event));
        this.confirmEventActivity.editor.commit();
    }
}
