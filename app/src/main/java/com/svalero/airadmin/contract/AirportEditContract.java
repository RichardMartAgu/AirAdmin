package com.svalero.airadmin.contract;

import com.svalero.airadmin.domain.Airport;

public interface AirportEditContract {

    interface Model {
        void loadEditOneAirport(long airportId, Airport airport, AirportEditContract.Model.OnLoadEditOneAirportListener listener);
        interface OnLoadEditOneAirportListener {
            void onLoadEditOneAirportSuccess();
            void onLoadEditOneAirportError(String message);
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

