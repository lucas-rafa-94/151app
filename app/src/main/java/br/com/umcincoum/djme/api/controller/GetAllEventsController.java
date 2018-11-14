package br.com.umcincoum.djme.api.controller;

import android.os.AsyncTask;

import java.util.List;

import br.com.umcincoum.djme.api.model.EventModel;
import br.com.umcincoum.djme.api.service.EventService;
import br.com.umcincoum.djme.ui.event.EventsActivity;


public class GetAllEventsController extends AsyncTask<Void,Void,List<EventModel>>{

    private EventsActivity eventsActivity;

    public GetAllEventsController(EventsActivity eventsActivity) {
        this.eventsActivity = eventsActivity;
    }

    @Override
    protected List<EventModel> doInBackground(Void... voids) {
        return EventService.getAllEvents();
    }

    @Override
    protected void onPostExecute(List<EventModel> eventModels) {
        super.onPostExecute(eventModels);
        this.eventsActivity.listEvents = eventModels;
    }
}
