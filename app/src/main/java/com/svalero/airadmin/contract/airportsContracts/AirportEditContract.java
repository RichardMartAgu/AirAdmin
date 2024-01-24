package com.svalero.airadmin.contract.airportsContracts;

import com.svalero.airadmin.domain.Airport;

public interface AirportEditContract {

    interface Model {
        void loadEditOneAirport(long airportId, Airport airport, OnLoadEditOneAirportListener listener);
        interface OnLoadEditOneAirportListener {
            void onLoadEditOneAirportSuccess();
            void onLoadEditOneAirportError(int stringId);
        }
    }
    interface Presenter {
        void editOneAirport(long airportId,Airport airport);
    }

    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
        }
    }

