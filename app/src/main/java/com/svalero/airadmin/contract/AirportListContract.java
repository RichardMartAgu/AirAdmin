package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

import java.util.List;

public interface AirportListContract {

    interface Model {
        interface OnLoadAllAirportListener {
            void onLoadAllAirportSuccess(List<Airport> airport);

            void onLoadAllAirportError(String message);
        }

        void loadAllAirports(OnLoadAllAirportListener listener);
    }

    interface View {
        void listAirports(List<Airport> airports);
        void showMessage(int stringId);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllAirports();
    }
}

