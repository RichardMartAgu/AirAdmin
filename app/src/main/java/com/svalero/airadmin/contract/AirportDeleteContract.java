package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

public interface AirportDeleteContract {

    interface Model {
        void loadDeleteOneAirport(long airportId, OnLoadDeleteOneAirportListener listener);
        interface OnLoadDeleteOneAirportListener {
            void onLoadDeleteOneAirportSuccess();
            void onLoadDeleteOneAirportError(String message);
        }
    }
    interface Presenter {
        void deleteOneAirport(long airportId);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }
}
