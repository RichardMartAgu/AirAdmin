package com.svalero.airadmin.contract.airportsContracts;

import com.svalero.airadmin.domain.Airport;

public interface AirportRegisterContract {

    interface Model {
        interface OnRegisterAirportListener {
            void onRegisterAirportSuccess();
            void onRegisterAirportError(int stringId);
        }
        void registerAirport(OnRegisterAirportListener listener, Airport airport);
    }
    interface View {
        void showMessage(int stringId);
        void showMessage(String message);
    }

    interface Presenter {
        void registerAirport(Airport airport);
    }
}