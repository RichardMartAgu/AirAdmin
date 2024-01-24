package com.svalero.airadmin.contract.airportsContracts;

import com.svalero.airadmin.domain.Airport;

public interface AirportDetailsContract {
    interface Model {
        interface OnLoadOneAirportListener {
            void onLoadOneAirportSuccess(Airport airport);
            void onLoadOneAirportError(int stringId);
        }
        void loadOneAirport(long airportId, OnLoadOneAirportListener listener);
    }
    interface Presenter {
        void loadOneAirport(long airportId);
    }

    interface View {
        void listOneAirport(Airport airports);

        void showMessage(String message);
        void showMessage(int StringId);
    }


}

