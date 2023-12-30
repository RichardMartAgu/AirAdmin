package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

import java.util.List;

public interface AirportDetailsContract {
    interface Model {
        interface OnLoadOneAirportListener {
            void onLoadOneAirportSuccess(Airport airport);
            void onLoadOneAirportError(String message);
        }
        void loadOneAirport(long airportId, OnLoadOneAirportListener listener);
    }
    interface Presenter {
        void loadOneAirport(long airportId);
    }

    interface View {
        void listOneAirport(Airport airports);
        void showMessage(Airport airport);

        void showMessage(String message);
    }


}

