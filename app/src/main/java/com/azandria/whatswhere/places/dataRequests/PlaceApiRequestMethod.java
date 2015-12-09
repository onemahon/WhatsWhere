package com.azandria.whatswhere.places.dataRequests;


import com.azandria.datadude.data.DataRequestBuilder;
import com.azandria.datadude.data.IDataRequestFilter;
import com.azandria.datadude.data.IDataRequestMethod;
import com.azandria.whatswhere.places.Place;
import com.azandria.whatswhere.places.PlaceManager;
import com.azandria.whatswhere.utils.BasicWebServiceManager;
import com.raizlabs.webservicemanager.webservicemanager.ResultInfo;
import com.raizlabs.webservicemanager.webservicemanager.WebServiceManager;
import com.raizlabs.webservicemanager.webservicemanager.WebServiceRequestListener;

import java.util.Collection;

public class PlaceApiRequestMethod implements IDataRequestMethod<Place> {

    protected int mId;

    public PlaceApiRequestMethod(int id) {
        mId = id;
    }

    @Override
    public boolean makeRequestDespitePreviousSuccess() {
        return false;
    }

    @Override
    public void doRequest(final DataRequestBuilder.RequestMethodListener<Place> listener, Collection<IDataRequestFilter> filters) {
        BasicWebServiceManager.get().doRequestInBackground(new PlaceApiRequest(mId), new WebServiceRequestListener<Place>() {
            @Override
            public void onRequestComplete(WebServiceManager webServiceManager, ResultInfo<Place> resultInfo) {
                if (resultInfo != null && resultInfo.isStatusOK()) {
                    Place result = resultInfo.getResult();
                    listener.onCompleted(result);
                    PlaceManager.get().store(result.mId, result);
                } else {
                    if (resultInfo != null) {
                        listener.onFailed(resultInfo.getResponseCode());
                    } else {
                        listener.onFailed(-1);
                    }
                }
            }
        });
    }
}
