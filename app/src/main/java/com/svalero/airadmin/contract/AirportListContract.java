package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

import java.util.List;

public interface AirportListContract {

    interface Model {
        interface OnLoadAirportListener {
            void onLoadAirportSuccess(List<Airport> airport);
            void onLoadAirportError(String message);
        }
        void loadAllAirports(OnLoadAirportListener listener);
    }

    interface View {
        void listAirports(List<Airport> airport);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllAirports();
    }
}
